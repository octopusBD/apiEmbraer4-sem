<template>
    <v-card class="grafico-linha">
      <v-card-title>Chassis Status</v-card-title>
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
    const incorporated = ref([]);
    const notIncorporated = ref([]);
    const notApplicable = ref([]);
    const chassi = ref([]);

    const getData = async () => {
  try {
    const response = await axios.get("/estatistica/listar/statuschassi"); // STRING PARA ACESSO A API
    chassi.value = response.data.map((item) => item.chassi); // PUXAR ITENS COMO ESSE EXEMPLO  
    incorporated.value = response.data.map((item) => item.incorporated);  // PUXAR ITENS COMO ESSE EXEMPLO  
    notIncorporated.value = response.data.map((item) => item.notIncorporated);  // PUXAR ITENS COMO ESSE EXEMPLO  
    notApplicable.value = response.data.map((item) => item.notApplicable);  // PUXAR ITENS COMO ESSE EXEMPLO  


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
        borderColor: 'rgba(131,111,255)',
        backgroundColor: 'rgba(131,111,255, 0.2)',
        data: incorporated.value // EIXO Y
      },


       {
        label: "Not Incorporated",
        borderColor: 'rgb(54, 162, 235)',
        backgroundColor: 'rgba(54, 162, 235, 0.2)',
        data: notIncorporated.value // EIXO Y
      },

       {
        label: "Not Applicable",
        borderColor: 'rgb(64,224,208)',
        backgroundColor: 'rgba(64,224,208, 0.2)',
        data: notApplicable.value // EIXO Y
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

<style>

</style>