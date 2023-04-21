<template>
  <v-card class="grafico-bar card-size">
    <v-card-title>Users by Permission</v-card-title>
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
    const permissao = ref([]);
    const qtdPermissao = ref([]);

    const getData = async () => {
  try {
    const response = await axios.get("/estatistica/listar/permissao"); // STRING PARA ACESSO A API
    permissao.value = response.data.map((item) => item.permissao); // PUXAR ITENS COMO ESSE EXEMPLO  
    qtdPermissao.value = response.data.map((item) => item.qtdPermissao);  // PUXAR ITENS COMO ESSE EXEMPLO  

    console.log(response);
    
  } catch (error) {
    console.log(error);
  }
  createChart();
};
const createChart = () => {
  if (!chartCanvas.value) return;

  const data = {
    labels: permissao.value, //EIXO X
    datasets: [
      
      {
        label: "Permissions",
        borderColor: 'rgb(54, 162, 235)',
        backgroundColor: 'rgba(54, 162, 235, 0.2)',
        data: qtdPermissao.value // EIXO Y
      }
    ]
  };

  const config = {
    type: 'bar', // TIPO GRAFICO
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