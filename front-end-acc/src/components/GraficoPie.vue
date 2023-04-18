<template>
  <v-card class="grafico-pie">
    <v-card-title>My Chart</v-card-title>
    <v-card-text>
      <canvas ref="chartCanvas"></canvas>
    </v-card-text>
  </v-card>
</template>

<script>
import Chart from "chart.js/auto";
import { onMounted, ref } from "vue";

const labels = ["January", "February", "abril", "maio"];

const data = {
  labels: labels,
  datasets: [
    {
      label: "My Dataset",
      borderColor: "rgb(255, 99, 132)",
      backgroundColor: "rgba(255, 99, 132, 0.2)",
      data: [0, 1, 2, 10, 20, 14, 15],
    },
  ],
};

const config = {
  type: "line",
  data: data,
  options: {
    plugins: {
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
  },
};

export default {
  setup() {
    const chartCanvas = ref(null);
    const chart = ref(null);
    onMounted(() => {
      if (chartCanvas.value) {
        const ctx = chartCanvas.value.getContext("2d");
        if (ctx) {
          chart.value = new Chart(ctx, config);
        }
      }
    });

    return { chartCanvas };
  },
};
</script>

