<template>
  <div>
    <button @click="generatePdf">Generate PDF</button>
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
      const administrador = ref([]);
      const editor = ref([]);
      const consultor = ref([]);

      const getData = async () => {
        try {
          const response = await axios.get("/estatistica/listar/permissao/tipo");
          administrador.value = response.data.map((item) => item.administrador);
          editor.value = response.data.map((item) => item.editor);
          consultor.value = response.data.map((item) => item.consultor);
          createChart();
        } catch (error) {
          console.log(error);
        }
      };

      const createChart = () => {
        if (!chartCanvas.value) return;

        const data = {
          labels: ["Users"],
          datasets: [
            {
              label: "Administrator",
              borderColor: "rgba(131,111,255)",
              backgroundColor: "rgba(131,111,255, 0.2)",
              data: administrador.value,
            },
            {
              label: "Editor",
              borderColor: "rgb(54, 162, 235)",
              backgroundColor: "rgba(54, 162, 235, 0.2)",
              data: editor.value,
            },
            {
              label: "Consultant",
              borderColor: "rgb(64,224,208)",
              backgroundColor: "rgba(64,224,208, 0.2)",
              data: consultor.value,
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

      const generatePdf = () => {
        const canvasElement = chartCanvas.value;
        const options = {
          margin: 2.5,
          filename: "Users.pdf",
          image: { type: "png", quality: 1, imageCenter: true },
          html2canvas: { dpi: 600, letterRendering: true, width: -53, height: -50, x: 2.5, y: 40 },
          jsPDF: { unit: "mm", format: "a4", orientation: "landscape", compressPdf: true, precision: 100 },
        };

        const doc = new jsPDF(options.jsPDF);
        doc.setFont("helvetica", "bold");
        doc.setFontSize(25);
        doc.setTextColor("#253381");
        doc.text("Users", 150, 15);
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

      onMounted(() => {
        getData();
      });

      return {
        chartCanvas,
        generatePdf
      };
    },
  };
</script>


