<template>
  <v-toolbar class="card-select" prominent>
    <v-spacer></v-spacer>
    <div class="filtro1">
      <v-select
        label="Chassis"
        :items="chassiOptions"
        density="compact"
        bg-color="white"
        hide-selected
        v-model="filtros.chassi"
      ></v-select>
    </div>
    <div class="filtro2">
      <v-select
        label="Itens"
        :items="itemOptions"
        density="compact"
        bg-color="white"
        hide-selected
        v-model="filtros.item"
      ></v-select>
    </div>
    <div class="filtro3">
      <v-select
        label="Status Sample"
        :items="statusSampleOptions"
        density="compact"
        bg-color="white"
        hide-selected
        v-model="filtros.statusSample"
      ></v-select>
    </div>
    <v-spacer></v-spacer>
  </v-toolbar>
</template>


<script>
import axios from "axios";

export default {
  data() {
    return {
      filtros: {
        chassi: "",
        item: "",
        statusSample: "",
      },
      chassiOptions: [],
      itemOptions: [],
      statusSampleOptions: [],
      itens: [],
    };
  },

  methods: {    
    buscarDados() {
      Promise.all([
        axios.get("consultor/2"),
      ]).then(([chassiResponse, itemResponse, statusResponse]) => {
        this.chassiOptions = chassiResponse.data;
        this.itemOptions = itemResponse.data;
        this.statusSampleOptions = statusResponse.data;
      }).catch((error) => {
        console.error(error);
      });
    },
  },

  watch: {
    "filtros.chassi": function () {
      this.buscarDados();
    },
    "filtros.item": function () {
      this.buscarDados();
    },
    "filtros.statusSample": function () {
      this.buscarDados();
    },
  },

async created() {
  await this.buscarDados();
  await this.buscarStatus();
  await this.buscarChassis();
  await this.buscarItens();
},
};
</script>

<style>
.card-select {
display: flex;
justify-content: center;
align-items: center;
}

.filtro1,
.filtro2,
.filtro3 {
width: 120px;
display: flex;
justify-content: center;
margin-top: 20px;
margin-right: 20px;
margin-left: 20px;
}

.v-toolbar {
background-color: black;
}
</style>