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

    const administrador = ref([]);
    const editor = ref([]);
    const consultor = ref([]);

    const getData = async () => {
  try {
    const response = await axios.get("/estatistica/listar/permissao/tipo"); // STRING PARA ACESSO A API
    administrador.value = response.data.map((item) => item.administrador);  // PUXAR ITENS COMO ESSE EXEMPLO  
    editor.value = response.data.map((item) => item.editor);  // PUXAR ITENS COMO ESSE EXEMPLO  
    consultor.value = response.data.map((item) => item.consultor);  // PUXAR ITENS COMO ESSE EXEMPLO  

    console.log(response);
    
  } catch (error) {
    console.log(error);
  }
  createChart();
};
const createChart = () => {
  if (!chartCanvas.value) return;

  const data = {
    labels: ['Users'], //EIXO X
    datasets: [
      
    {
        label: "Administrator",
        borderColor: 'rgba(131,111,255)',
        backgroundColor: 'rgba(131,111,255, 0.2)',
        data: administrador.value // EIXO Y
      },


       {
        label: "Editor",
        borderColor: 'rgb(54, 162, 235)',
        backgroundColor: 'rgba(54, 162, 235, 0.2)',
        data: editor.value // EIXO Y
      },

       {
        label: "Consultant",
        borderColor: 'rgb(64,224,208)',
        backgroundColor: 'rgba(64,224,208, 0.2)',
        data: consultor.value // EIXO Y
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