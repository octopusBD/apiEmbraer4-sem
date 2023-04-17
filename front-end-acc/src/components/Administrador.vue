<template>
  <v-card>
    <v-tabs
      v-model="tab"
    >
      <v-tab class="PrimeiraOpcao" value="one">Users</v-tab>
      <v-tab class="SegundaOpcao" value="two">Statistic</v-tab>
    </v-tabs>
    <v-card-text>
      <v-window v-model="tab">
        <v-window-item value="one">
          <administrador-pimeiro></administrador-pimeiro>
        </v-window-item>
        <v-window-item value="two">
          <administrador-segunda-tela-vue></administrador-segunda-tela-vue>
          
        </v-window-item>
      </v-window>
    </v-card-text>
  </v-card>
</template>

<script>
import AdministradorPimeiro from './AdministradorPimeiro.vue';
import AdministradorSegundaTelaVue from './AdministradorSegundaTela.vue';

export default {
  data: () => ({
      tab: null,
    }),
  
  components: {
    AdministradorPimeiro,
    AdministradorSegundaTelaVue,
  }
}
</script>

<style>
.PrimeiraOpcao{
  color: #253381;
}
.SegundaOpcao{
  color: #253381;
}
</style>