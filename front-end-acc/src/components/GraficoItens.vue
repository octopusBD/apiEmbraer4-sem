<template>
  <div>
    <div class="container">
      <button @click="generatePdf">Generate PDF</button>
      <canvas ref="chartCanvas"></canvas>
    </div>
  </div>
</template>

<script>
  import Chart from "chart.js/auto";
  import axios from "axios";
  import { onMounted, ref } from "vue";
  import jsPDF from 'jspdf';

  export default {
    setup() {
      const chartCanvas = ref(null);
      const item = ref([]);
      const quantidade = ref([]);

      const getData = async () => {
        try {
          const response = await axios.get("/estatistica/listar/itemchassi");
          console.log(response)
          item.value = response.data.map((item) => item.item);
          quantidade.value = response.data.map((item) => item.quantidade);
        } catch (error) {
          console.log(error);
        }
        createChart();
      };
      const createChart = () => {
        if (!chartCanvas.value) return;

        const data = {
          labels: item.value, //EIXO X
          datasets: [
            {
              label: "Incorporated",
              borderColor: 'rgba(131,111,255)',
              backgroundColor: 'rgba(131,111,255, 0.2)',
              data: quantidade.value 
            },
          ]
        };

        const config = {
          type: 'bar', // TIPO GRAFICO
          data: data,
          options: {
            maintainAspectRatio: false,
            scales: {
              yAxes: [{
                ticks: {
                  beginAtZero: true
                }
              }]
            },
            plugins: {
              zoom: {
                zoom: {
                  drag: {
                    selection: true
                  },
                  mode: 'xy'
                },
                onZoomComplete: function ({ chart }) {
                  console.log('Zoom complete', chart.scales);
                }
              }
            }
          }
        };

        chartCanvas.value = new Chart(chartCanvas.value, config);
      };

      onMounted(() => {
        getData();
      });

      const generatePdf = () => {
        const canvasElement = chartCanvas.value;
        const options = {
          margin: 2.5,
          filename: "Quantidade de Itens Incorporados.pdf",
          image: { type: "png", quality: 1, imageCenter: true },
          html2canvas: { dpi: 600, letterRendering: true, width: -53, height: -50, x: 2.5, y: 40 },
          jsPDF: { unit: "mm", format: "a4", orientation: "landscape", compressPdf: true, precision: 100 },
        };

        const doc = new jsPDF(options.jsPDF);
        doc.setFont("helvetica", "bold");
        doc.setFontSize(18);
        doc.text("Quantidade de Itens Incorporados", 70, 15);
        const dateTime = `${new Date().toLocaleDateString()} ${new Date().toLocaleTimeString()}`;
        const imgData = "https://raw.githubusercontent.com/octopusBD/docs/main/api4sem/logo1png.png";
        doc.addImage(imgData, "JPEG", 10, -12, 50, 50);
        doc.setFont("helvetica", "not bold");
        doc.setFontSize(12);
        doc.text(`${dateTime}`, 23, 27);
        const canvasImg = canvasElement.toDataURL("image/png", 1.0);
        doc.addImage(canvasImg, "PNG", options.html2canvas.x, options.html2canvas.y, options.html2canvas.width, options.html2canvas.height);
        doc.save(options.filename);
      };

      return {
        chartCanvas,
        generatePdf
      };
    }
  };
</script>

<style>
  .container {
    height: 400px;
  }
</style>