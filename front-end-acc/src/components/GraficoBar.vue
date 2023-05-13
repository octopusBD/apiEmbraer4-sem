<template>
  <!-- <v-card class="grafico-bar card-size">
    <v-card-title style="text-align: center;">Items by Chassis</v-card-title>
    <v-card-text> -->
      <div>
        <button @click="generatePdf"> <v-icon>mdi-download</v-icon></button>
        <canvas ref="chartCanvas"></canvas>
      </div>
    <!-- </v-card-text>
  </v-card> -->
</template>

<script>
  import Chart from "chart.js/auto";
  import axios from "axios";
  import { onMounted, ref } from "vue";
  import jsPDF from 'jspdf';

  export default {
    setup() {
      const chartCanvas = ref(null);
      const qtdBoletins = ref([]);
      const chassi = ref([]);

      const getData = async () => {
        try {
          const response = await axios.get("/estatistica/listar/boletim"); // STRING PARA ACESSO A API
          chassi.value = response.data.map((item) => item.chassi); // PUXAR ITENS COMO ESSE EXEMPLO  
          qtdBoletins.value = response.data.map((item) => item.qtdBoletins);  // PUXAR ITENS COMO ESSE EXEMPLO  

          console.log(response);
          
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
              label: "chassi",
              borderColor: 'rgb(54, 162, 235)',
              backgroundColor: 'rgba(54, 162, 235, 0.2)',
              data: qtdBoletins.value // EIXO Y
            }
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
          filename: "Quantity of Itens.pdf",
          image: { type: "png", quality: 1, imageCenter: true },
          html2canvas: { dpi: 500, letterRendering: true, width: 794, height: 1123, x: 2.5, y: 40 },
          jsPDF: { unit: "mm", format: "a4", orientation: "landscape", compressPdf: true, precision: 100 },
        };

        const doc = new jsPDF(options.jsPDF);
        doc.setFont("helvetica", "bold");
        doc.setFontSize(25);
        doc.setTextColor("#253381");
        doc.text("Quantity of Itens", 140, 15);
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