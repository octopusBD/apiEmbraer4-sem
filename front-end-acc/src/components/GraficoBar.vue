<template>
  <v-card class="grafico-bar card-size">
    <v-card-title>My Chart</v-card-title>
    <v-card-text>
      <canvas ref="chartCanvas"></canvas>
    </v-card-text>
  </v-card>
</template>

<script>
import Chart from 'chart.js/auto';
import axios from 'axios';
import { onMounted, ref } from 'vue';

export default {
  setup() {
    const chartCanvas = ref(null);
    const qtdBoletins = ref([]);
    const chassi = ref([]);
    const labels = ref([]);

    const getData = async () => {
  try {
    const response = await axios.get("/estatistica/listar/boletim");
    chassi.value = response.data.map((item) => item.chassi);
    qtdBoletins.value = response.data.map((item) => item.qtdBoletins);

    console.log(response);
    
  } catch (error) {
    console.log(error);
  }
  createChart();
};
const createChart = () => {
  if (!chartCanvas.value) return;

  const data = {
    labels: chassi.value,
    datasets: [
      
      {
        label: "chassi",
        borderColor: 'rgb(54, 162, 235)',
        backgroundColor: 'rgba(54, 162, 235, 0.2)',
        data: qtdBoletins.value // Alteração aqui
      }
    ]
  };

  const config = {
    type: 'line',
    data: data,
    options: {
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

    return {
      chartCanvas
    };
  }
};
</script>