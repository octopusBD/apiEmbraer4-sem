<template>
  <div class="scroll-container">
    <div class="container">
      <div class="button-group">
        <v-btn class="reset-zoom-button" size="25" height="50" width="25" @click="resetZoom">
          <v-icon>mdi-refresh</v-icon>
        </v-btn>
        <v-btn size="25" height="50" width="25" @click="generatePdf">
          <v-icon>mdi-download</v-icon>
        </v-btn>
      </div>
      <h2 class="Titulo1">Quantity of Items</h2>
      <canvas class="oi" ref="chartCanvas"></canvas>
    </div>
  </div>
</template>

<script>
  import { onMounted, ref, reactive } from "vue";
  import Chart from "chart.js/auto";
  import ChartZoom from "chartjs-plugin-zoom";
  import axios from "axios";
  import jsPDF from "jspdf";

  export default {
    setup() {
      const chartCanvas = ref(null);

      // Reactive object to store the chart instance
      const chartInstance = reactive({ chart: null });

      // Data properties
      const notIncorporatedPercentage = ref([]);
      const incorporatedPercentage = ref([]);
      const applicablePercentage = ref([]);
      const item = ref([]);

      // Fetch data from API
      const getData = async () => {
        try {
          const response = await axios.get("/estatistica/listar/ViewQtdStatus");
          item.value = response.data.map((item) => item.item);
          notIncorporatedPercentage.value = response.data.map(
            (item) => item.notIncorporatedPercentage
          );
          incorporatedPercentage.value = response.data.map(
            (item) => item.incorporatedPercentage
          );
          applicablePercentage.value = response.data.map(
            (item) => item.applicablePercentage
          );

          console.log(response);
        } catch (error) {
          console.log(error);
        }
        createChart();
      };

      const resetZoom = () => {
        if (chartInstance.chart) {
          chartInstance.chart.resetZoom();
        }
      };

      // Create the chart using Chart.js
      const createChart = () => {
        if (!chartCanvas.value) return;

        const data = {
          labels: item.value,
          datasets: [
            {
              label: "Not Incorporated",
              borderColor: "#6A83DE",
              backgroundColor: "#6A83DE",
              data: notIncorporatedPercentage.value,
              stack: 1,
            },
            {
              label: "Incorporated",
              borderColor: "#5265AB",
              backgroundColor: "#5265AB",
              data: incorporatedPercentage.value,
              stack: 1,
            },
            {
              label: "Applicable",
              borderColor: "#B1BCE3",
              backgroundColor: "#B1BCE3",
              data: applicablePercentage.value,
              stack: 1,
            },
          ],
        };

        const config = {
          type: "bar",
          data: data,
          options: {
            maintainAspectRatio: false,
            scales: {
              y: {
                stacked: true,
                beginAtZero: true,
              },
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
                  mode: "xy",
                },
                pan: {
                  enabled: true,
                  mode: "xy",
                },
              },
            },
          },
          plugins: [ChartZoom],
        };

        const canvas = chartCanvas.value;
        const ctx = canvas.getContext("2d");

        chartInstance.chart = new Chart(ctx, config);
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
        const title = "Quantity of Itens";
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
      return {
        chartCanvas,
        generatePdf,
        resetZoom
      };
    },
  };
</script>

<style>
  .container {
    height: 400px;
  }

  .Titulo1 {
    font-size: 25px;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .container {
    height: 400px;
    white-space: nowrap; /* Prevent line breaks */
  }

  .button-group {
    display: flex;
    align-items: center;
    margin-bottom: 10px;
  }

  .reset-zoom-button {
    margin-right: 10px;
  }

  /* Estilo para telas menores */
  @media only screen and (max-width: 600px) {
    .chart-canvas {
      width: 800px; /* Ajuste conforme necessário */
    }
    .Titulo1 {
      font-size: 20px;
    }
  }
</style>
