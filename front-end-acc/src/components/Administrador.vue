<template>
  <v-card >
    <v-tabs v-model="tab">
      <v-tab class="PrimeiraOpcao" value="one">Users</v-tab>
      <v-tab class="SegundaOpcao" value="two">Statistic</v-tab>
      <v-tab class="TerceiraOpcao" value="three">Graphics</v-tab>
    </v-tabs>
    <v-card >
      <v-window v-model="tab">
        <v-window-item value="one">
          <administrador-primeiro></administrador-primeiro>
        </v-window-item>
        <v-window-item value="two">
          <administrador-segunda-tela-vue></administrador-segunda-tela-vue>
        </v-window-item>
        <v-window-item value="three">
          <v-row>
            <v-col style="padding: 10px;" cols="12" sm="6">
              <grafico-linha-vue class="grafico"></grafico-linha-vue>
            </v-col>
            <v-col style="padding: 10px;" cols="12" sm="6">
              <grafico-bar-vue class="grafico"></grafico-bar-vue>
            </v-col>
          </v-row>
          <v-row>
            <v-col style="padding: 10px;" cols="12" sm="6">
              <grafico-vue class="grafico"></grafico-vue>
            </v-col>
            <v-col style="padding: 10px;" cols="12" sm="6">
              <grafico-pie class="grafico"></grafico-pie>
            </v-col>
          </v-row>
        </v-window-item>
      </v-window>
    </v-card>
  </v-card>
</template>

<script>
import AdministradorPrimeiro from "./AdministradorPrimeiro.vue";
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
    AdministradorPrimeiro,
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
.TerceiraOpcao{
  color: #253381;
}
.graficos-container {
  display: flex;
  justify-content: space-between;
}
.grafico {
  min-height: 300px;
  width: 100%;
  border-top-color: #253381;
  padding: 20px;
  padding-bottom: 40px;
}

@media only screen and (max-width: 720px) {
  .PrimeiraOpcao,
  .SegundaOpcao,
  .TerceiraOpcao {
  margin:10px; 
  }
  

  @media only screen and (max-width: 400px) {
  .PrimeiraOpcao,
  .SegundaOpcao,
  .TerceiraOpcao {
  margin:11px;
  }

  
}

@media only screen and (max-width: 300px) {
  .PrimeiraOpcao,
  .SegundaOpcao,
  .TerceiraOpcao {
  margin:10px;
  }
}

}
</style>
