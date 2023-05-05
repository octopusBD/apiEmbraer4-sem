<template>
      <canvas style=" height: 100%;" ref="chartCanvas"></canvas>

</template>

<script>
import Chart from 'chart.js/auto';
import axios from 'axios';
import { onMounted, ref } from 'vue';

export default {
  setup() {
    const chartCanvas = ref(null);
    const item = ref([]);
    const quantidade = ref([]);
    
    const getData = async () => {
  try {
    const response = await axios.get("/estatistica/listar/itemchassi");
    console.log(response)
    item.value = response.data.map((item) => item.item);  
    quantidade.value = response.data.map((item) => item.quantidade);  
    
  } catch (error) {
    console.log(error);
  }
  createChart();
};
const createChart = () => {
  if (!chartCanvas.value) return;

  const data = {
    labels: item.value, //EIXO X
    datasets: [
      
      {
        label: "Incorporated",
        borderColor: 'rgba(131,111,255)',
        backgroundColor: 'rgba(131,111,255, 0.2)',
        data: quantidade.value 
      },
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

    return {
      chartCanvas
    };
  }
};
</script>

