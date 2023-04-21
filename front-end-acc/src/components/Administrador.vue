<template>
  <v-card >
    <v-tabs v-model="tab">
      <v-tab class="PrimeiraOpcao" value="one">Users</v-tab>
      <v-tab class="SegundaOpcao" value="two">Statistic</v-tab>
      <v-tab class="TerceiraOpcao" value="three">Graphics</v-tab>
    </v-tabs>
    <v-card  style="padding: 50px;">
      <v-window v-model="tab">
        <v-window-item value="one">
          <administrador-pimeiro></administrador-pimeiro>
        </v-window-item>
        <v-window-item value="two">
          <administrador-segunda-tela-vue></administrador-segunda-tela-vue>
        </v-window-item>
        <v-window-item value="three">
          <v-row>
            <v-col style="padding: 30px;" cols="6">
              <grafico-linha-vue></grafico-linha-vue>
            </v-col>
            <v-col style="padding: 30px;" cols="6">
              <grafico-bar-vue></grafico-bar-vue>
            </v-col>
          </v-row>
          <v-row>
            <v-col style="padding: 30px;" cols="6">
              <grafico-vue></grafico-vue>
            </v-col>
            <v-col style="padding: 30px;" cols="6">
              <grafico-pie></grafico-pie>
            </v-col>
          </v-row>
        </v-window-item>
      </v-window>
    </v-card>
  </v-card>
</template>

<script>
import AdministradorPimeiro from "./AdministradorPimeiro.vue";
import AdministradorSegundaTelaVue from "./AdministradorSegundaTela.vue";
import GraficoLinhaVue from "./GraficoLinha.vue";
import GraficoBarVue from "./GraficoBar.vue";
import GraficoPie from "./GraficoPie.vue";
import GraficoVue from './Grafico.vue';
export default {
  data: () => ({
    tab: null,
  }),

  components: {
    AdministradorPimeiro,
    AdministradorSegundaTelaVue,
    GraficoLinhaVue,
    GraficoBarVue,
    GraficoPie,
    GraficoVue
  },
};
</script>

<style>
.PrimeiraOpcao {
  color: #253381;
}
.SegundaOpcao {
  color: #253381;
}
.graficos-container {
  display: flex;
  justify-content: space-between;
}
.grafico {
  width: 100%;
  border-top-color: #253381;
}
</style>
