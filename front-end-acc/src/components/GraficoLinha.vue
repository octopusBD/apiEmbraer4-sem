<template>
  <div>
    <button @click="generatePdf"> <v-icon>mdi-download</v-icon></button>
    <canvas ref="chartCanvas"></canvas>
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
      const incorporated = ref([]);
      const notIncorporated = ref([]);
      const notApplicable = ref([]);
      const chassi = ref([]);

      const getData = async () => {
        try {
          const response = await axios.get("/estatistica/listar/statuschassi"); // STRING PARA ACESSO A API
          chassi.value = response.data.map((item) => item.chassi); // PUXAR ITENS COMO ESSE EXEMPLO
          incorporated.value = response.data.map((item) => item.incorporated); // PUXAR ITENS COMO ESSE EXEMPLO
          notIncorporated.value = response.data.map(
            (item) => item.notIncorporated
          ); // PUXAR ITENS COMO ESSE EXEMPLO
          notApplicable.value = response.data.map((item) => item.notApplicable); // PUXAR ITENS COMO ESSE EXEMPLO

          console.log(notApplicable.value);
        } catch (error) {
          console.log(error);
        }
        createChart();
      };

      const createChart = () => {
        if (!chartCanvas.value) return;

        const data = {
          labels: chassi.value, //EIXO X
          datasets: [
            {
              label: "Incorporated",
              borderColor: "rgba(131,111,255)",
              backgroundColor: "rgba(131,111,255, 0.2)",
              data: incorporated.value, // EIXO Y
            },

            {
              label: "Not Incorporated",
              borderColor: "rgb(54, 162, 235)",
              backgroundColor: "rgba(54, 162, 235, 0.2)",
              data: notIncorporated.value, // EIXO Y
            },

            {
              label: "Not Applicable",
              borderColor: "rgb(64,224,208)",
              backgroundColor: "rgba(64,224,208, 0.2)",
              data: notApplicable.value, // EIXO Y
            },
          ],
        };

        const config = {
          type: "bar", // TIPO GRAFICO
          data: data,
          options: {
            maintainAspectRatio: false,
            scales: {
              yAxes: [
                {
                  ticks: {
                    beginAtZero: true,
                  },
                },
              ],
            },
            plugins: {
              zoom: {
                zoom: {
                  drag: {
                    selection: true,
                  },
                  mode: "xy",
                },
                onZoomComplete: function ({ chart }) {
                  console.log("Zoom complete", chart.scales);
                },
              },
            },
          },
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
          filename: "Status by Chassi.pdf",
          image: { type: "png", quality: 1, imageCenter: true },
          html2canvas: { dpi: 600, letterRendering: true, width: -53, height: -50, x: 2.5, y: 40 },
          jsPDF: { unit: "mm", format: "a4", orientation: "landscape", compressPdf: true, precision: 100 },
        };

        const doc = new jsPDF(options.jsPDF);
        doc.setFont("helvetica", "bold");
        doc.setFontSize(25);
        doc.setTextColor("#253381");
        doc.text("Status by Chassi", 140, 15);
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
    },
  };
</script>

<style>
  /* .grafico-linha{

    height: 700px;
  } */
</style>