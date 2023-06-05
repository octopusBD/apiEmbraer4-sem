<template>
  <v-tabs v-model="tab">
    <v-tab class="PrimeiraOpcao" value="one">Users</v-tab>
    <v-tab class="SegundaOpcao" value="two">Itens</v-tab>
    <v-tab class="TerceiraOpcao" value="three">Statistic</v-tab>
    <v-tab class="QuartaOpcao" value="four">Control</v-tab>
  </v-tabs>
  <v-window style="height: 95%" v-model="tab">
    <v-window-item value="one">
      <administrador-primeiro style="height: 100%;"></administrador-primeiro>
    </v-window-item>
    <v-window-item style="height: 100%;" value="two">
      <administrador-segunda-tela-vue style="height: 100%;"></administrador-segunda-tela-vue>

    </v-window-item>
    <v-window-item style="height: 100%;" value="three">
      <v-row>
        <v-col cols="12" md="6">
          <v-card style="margin-left: 2%; margin-bottom: 2% ;margin-right:2%; margin-top:2%" >
            <grafico-linha-vue
              style="height: 400px; padding: 65px; margin: 5px;padding-bottom: 10%; "
            ></grafico-linha-vue>
          </v-card>
        </v-col>
        <v-col cols="12" md="6">
          <v-card style="margin-right: 2%; margin-bottom: 2%; margin-left: 2%; margin-top:2%">
            <grafico-bar-vue
              class="grafico"
              style="height: 400px; padding: 65px; margin: 5px;padding-bottom: 10%;"
            ></grafico-bar-vue>
          </v-card>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12" md="6">
          <v-card style="margin-left: 2%; margin-bottom: 2%; margin-right: 2%; margin-top:2%">
            <grafico-pie
              class="grafico"
              style="height: 400px; padding: 65px; margin: 5px;padding-bottom: 10%;"
            ></grafico-pie>
          </v-card>
        </v-col>
        <v-col cols="12" md="6">
          <v-card style="margin-right: 2%; margin-left: 2%; margin-bottom: 2%; margin-top:2%">
            <grafico-vue
              style="height: 400px; padding: 65px; margin: 5px;padding-bottom: 10%;"
            ></grafico-vue>
          </v-card>
        </v-col>
      </v-row>
    </v-window-item>
    <v-window-item style="height: 85%" value="four" >
      <v-card style="margin-left: 5%;  margin-bottom: 2%; margin-right: 5%; margin-top: 5%;" >
        <grafico-itens
        style="height: 50%; padding: 65px; margin: 5px;padding-bottom: 10%;"
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
.QuartaOpcao {
  color: #253381;
}




.QuartaOpcao {
  color: #253381;
}
.titulo1 {
  font-size: 25px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #253381;
}
.graphs{
  height: 350px;
  padding: 10px;
  margin-bottom: 20px;
}
.titulo2 {
  font-size: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: rgb(55, 53, 53);
}
.titulo3 {
  font-size: 25px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #253381;
}
.titulo4 {
  font-size: 25px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #253381;
}

.titulo5 {
  font-size: 25px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #253381;
}
.grafico_control {
  margin-left: 2%;
  margin-right: 2%;
  margin-top: 2%;
  font-family: Arial, sans-serif;
  color: #253381;
}

</style>
