0<template>
  <div>
    <div class="container">
      <v-btn size="25" height="50" width="25" @click="generatePdf">
      <v-icon>mdi-download</v-icon></v-btn
    >
    <h2 class="Titulo1">Quantity of Itens</h2>
      <canvas ref="chartCanvas"></canvas>
    </div>
  </div>
</template>

<script>
import Chart from 'chart.js/auto';
import axios from 'axios';
import { onMounted, ref } from 'vue';


export default {
  setup() {
    const chartCanvas = ref(null);

    const notIncorporatedPercentage = ref([]);
    const incorporatedPercentage = ref([]);
    const applicablePercentage = ref([]);
    const item = ref([]);

    const getData = async () => {
      try {
        const response = await axios.get("/estatistica/listar/ViewQtdStatus");
        item.value = response.data.map((item) => item.item);
        notIncorporatedPercentage.value = response.data.map((item) => item.notIncorporatedPercentage);
        incorporatedPercentage.value = response.data.map((item) => item.incorporatedPercentage);
        applicablePercentage.value = response.data.map((item) => item.applicablePercentage);

        console.log(response);
      } catch (error) {
        console.log(error);
      }
      createChart();
    };
    const createChart = () => {
      if (!chartCanvas.value) return;

      const data = {
        labels: item.value,
        datasets: [
          {
            label: "Not Incorporated",
            borderColor: "rgba(131,111,255)",
            backgroundColor: "rgba(131,111,255, 0.2)",
            data: notIncorporatedPercentage.value,
            stack: 1 // Adiciona a propriedade "stack" com valor 1
          },

          {
            label: "Incorporated",
            borderColor: "rgb(54, 162, 235)",
            backgroundColor: "rgba(54, 162, 235, 0.2)",
            data: incorporatedPercentage.value,
            stack: 1 // Adiciona a propriedade "stack" com valor 1
          },

          {
            label: "Applicable",
            borderColor: "rgb(64,224,208)",
            backgroundColor: "rgba(64,224,208, 0.2)",
            data: applicablePercentage.value,
            stack: 1 // Adiciona a propriedade "stack" com valor 1
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
                stacked: true ,// Altera a propriedade "stacked" para "true"
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
        doc.setFont("helvetica", "bold");
        doc.setFontSize(20);
        doc.text("Quantity of Itens", 140, 15);
        const dateTime = `${new Date().toLocaleDateString()} ${new Date().toLocaleTimeString()}`;
        const imgData = "https://raw.githubusercontent.com/octopusBD/docs/main/api4sem/logo1png.png";
        doc.addImage(imgData, "JPEG", 10, -12, 80, 50);
        doc.setFont("helvetica", "not bold");
        doc.setFontSize(12);
        doc.text(`${dateTime}`, 35.5, 28);
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

  chartCanvas.value = new Chart(chartCanvas.value, config);
};
    onMounted(() => {
      getData();
    });

    return {
      chartCanvas
    };
  }
};
</script>

<style>
  .container {
    height: 400px;
  }.Titulo1{
  font-size: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}

</style>
