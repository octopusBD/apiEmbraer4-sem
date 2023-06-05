<template>
  <!-- <v-card class="grafico-bar card-size">
    <v-card-title style="text-align: center;">Items by Chassis</v-card-title>
    <v-card-text> -->
  <div>
      <div class="button-group">
        <v-btn class="reset-zoom-button" size="25" height="50" width="25" @click="resetZoom">
            <v-icon>mdi-refresh</v-icon>
        </v-btn>
        <v-btn size="25" height="50" width="25" @click="generatePdf">
          <v-icon>mdi-download</v-icon>
        </v-btn>
      </div>
    <h2 class="Titulo1">Itens by Chassi</h2>
    <canvas ref="chartCanvas"></canvas>
  </div>
  <!-- </v-card-text>
  </v-card> -->
</template>

<script>
  import ChartZoom from 'chartjs-plugin-zoom';
  import Chart from "chart.js/auto";
  import axios from "axios";
  import { onMounted, ref } from "vue";
  import jsPDF from "jspdf";

  export default {
    setup() {
      const chartCanvas = ref(null);
      const chartInstance = ref(null);
      const qtdBoletins = ref([]);
      const chassi = ref([]);

      const getData = async () => {
        try {
          const response = await axios.get("/estatistica/listar/boletim"); // STRING PARA ACESSO A API
          chassi.value = response.data.map((item) => item.chassi); // PUXAR ITENS COMO ESSE EXEMPLO
          qtdBoletins.value = response.data.map((item) => item.qtdBoletins); // PUXAR ITENS COMO ESSE EXEMPLO

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
              borderColor: "#6A83DE",
              backgroundColor: "#6A83DE",
              data: qtdBoletins.value, // EIXO Y
            },
          ],
        };

        const config = {
          type: "bar",
          data: data,
          options: {
            maintainAspectRatio: false,
            scales: {
              yAxes: [
                {
                  stacked: true,
                  ticks: {
                    beginAtZero: true,
                  },
                },
              ],
            },
            plugins: {
              zoom: {
                zoom: {
                  wheel: {
                    enabled: true,
                  },
                  pinch: {
                    enabled: true,
                  },
                  mode: 'xy',
                },
                pan: {
                  enabled: true,
                  mode: 'xy',
                },
              },
            },
          },
          plugins: [ChartZoom],
        };
        chartInstance.value = new Chart(chartCanvas.value, config);
      };

      const resetZoom = () => {
        if (chartInstance.value) {
          chartInstance.value.resetZoom();
        }
      };

      const generatePdf = () => {
        const canvasElement = chartCanvas.value;
        const options = {
          margin: 2.5,
          filename: "Itens By Chassi.pdf",
          image: { type: "png", quality: 1, imageCenter: true },
          html2canvas: {
            dpi: 1200,
            letterRendering: true,
            width: 282,
            height: 157,
            x: 7.5,
            y: 40,
          },
          jsPDF: {
            unit: "mm",
            format: "a4",
            orientation: "landscape",
            compressPdf: true,
            precision: 100,
          },
        };
    
        const doc = new jsPDF(options.jsPDF);
        const title = "Itens By Chassi";
        const titleTextColor = "#FFFFFF";
        const titleColor = "#054f77"; // Cor azul da primeira faixa
        const secondBandColor = "#FFFFFF"; // Cor da segunda faixa

        doc.setFont("helvetica", "bold");
        doc.setFontSize(20);

        const dateTime = `${new Date().toLocaleDateString()} ${new Date().toLocaleTimeString()}`;
        const imgData = "https://cdn.discordapp.com/attachments/1075971608684023814/1111350679022346260/logo-dois.png";

        const titleWidth = doc.getStringUnitWidth(title) * doc.internal.getFontSize() / doc.internal.scaleFactor;
        const titleX = (doc.internal.pageSize.getWidth() - titleWidth) / 2;
        const titleHeight = 11; // Altura da primeira faixa
        const titleFontSize = 20; // Tamanho da fonte do título
        const titleY = 15 + (titleHeight - titleFontSize) / 2 + titleFontSize * 0.35;

        // Desenhar a primeira faixa azul
        doc.setFillColor(titleColor);
        doc.rect(0, 0, doc.internal.pageSize.getWidth(), 30, "F");

        // Adicionar imagem do logo
        doc.addImage(imgData, "JPEG", 10, 4.5, 50, 20);

        // Desenhar a segunda faixa
        const secondBandY = 22.25; // Posição vertical da segunda faixa
        const secondBandHeight = 0.5; // Altura da segunda faixa
        doc.setFillColor(secondBandColor);
        doc.rect(0, secondBandY, doc.internal.pageSize.getWidth(), secondBandHeight, "F");

        // Escrever o título na primeira faixa
        doc.setTextColor(titleTextColor);
        doc.setFontSize(titleFontSize);
        doc.text(title, titleX, titleY);

        doc.setFont("helvetica", "not bold");
        doc.setFontSize(12);
        doc.text(`${dateTime}`, 22, 28);

        const canvasImg = canvasElement.toDataURL("image/png", 1.0);
        doc.addImage(
          canvasImg,
          "PNG",
          options.html2canvas.x,
          options.html2canvas.y,
          options.html2canvas.width,
          options.html2canvas.height
        );

        doc.save(options.filename);
      };

      onMounted(() => {
        getData();
      });

      return {
        chartCanvas,
        generatePdf,
        resetZoom

      };
    },
  };
</script>

<style>
  .Titulo1{
    font-size: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
</style>
