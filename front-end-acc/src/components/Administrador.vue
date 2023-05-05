<template>
  <v-tabs v-model="tab">
    <v-tab class="PrimeiraOpcao" value="one">Users</v-tab>
    <v-tab class="SegundaOpcao" value="two">Itens</v-tab>
    <v-tab class="TerceiraOpcao" value="three">Statistic</v-tab>
    <v-tab class="QuartaOpcao" value="four">Control</v-tab>
  </v-tabs>
  <v-window style="height: 85%" v-model="tab">
    <v-window-item value="one">
      <administrador-primeiro></administrador-primeiro>
    </v-window-item>
    <v-window-item style="height: 85%" value="two">
      <administrador-segunda-tela-vue></administrador-segunda-tela-vue>
    </v-window-item>
    <v-window-item style="height: 85%" value="three">
      <v-row>
        <v-col cols="12" md="6">
          <v-card style="margin-left: 2%; margin-bottom: 2% ;margin-right:2%">
            <grafico-linha-vue
              class="grafico"
              style="height: 350px; padding: 10px; margin: 5px"
            ></grafico-linha-vue>
          </v-card>
        </v-col>
        <v-col cols="12" md="6">
          <v-card style="margin-right: 2%; margin-bottom: 2%; margin-left: 2%;">
            <grafico-bar-vue
              class="grafico"
              style="height: 350px; padding: 10px; margin: 5px"
            ></grafico-bar-vue>
          </v-card>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="6">
          <v-card style="margin-left: 2%; margin-bottom: 2%; margin-right: 2%;">
            <grafico-pie
              class="grafico"
              style="height: 350px; padding: 10px; margin: 5px"
            ></grafico-pie>
          </v-card>
        </v-col>
        <v-col cols="12" md="6">
          <v-card style="margin-right: 2%; margin-left: 2%; margin-bottom: 2%">
            <grafico-vue
              style="height: 350px; padding: 10px; margin: 5px"
            ></grafico-vue>
          </v-card>
        </v-col>
      </v-row>
    </v-window-item>
    <v-window-item style="height: 85%" value="four" >
      <v-card style="margin-left: 5%;  margin-bottom: 2%; margin-right: 5%; margin-top: 10%;" >
        <grafico-itens
        style="height: 50%; padding: 10px; margin: 5px"
        ></grafico-itens>
      </v-card>
    </v-window-item>
  </v-window>
</template>

<script>
import AdministradorPrimeiro from "./AdministradorPrimeiro.vue";
import AdministradorSegundaTelaVue from "./AdministradorSegundaTela.vue";
import GraficoLinhaVue from "./GraficoLinha.vue";
import GraficoBarVue from "./GraficoBar.vue";
import GraficoPie from "./GraficoPie.vue";
import GraficoVue from "./Grafico.vue";
import GraficoItens from "./GraficoItens.vue";
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
    GraficoVue,
    GraficoItens,
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
.TerceiraOpcao {
  color: #253381;
}

/* .graficos-container {
  display: flex;

  /* justify-content: space-between; */

/* .grafico {

  width: 100%;
  border-top-color: #253381;
  border-style: solid red;
} */

/* @media only screen and (max-width: 720px) {
  .PrimeiraOpcao,
  .SegundaOpcao,
  .TerceiraOpcao {
  margin:10px; 
  }
  

  @media only screen and (max-width: 350px) {
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

} */
</style>
