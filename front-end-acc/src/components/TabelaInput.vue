<template>
  <div class="container">
    <v-toolbar class="card-select" prominent>
      <v-spacer></v-spacer>
      <div class="filtro1">
        <v-select
          label="Chassis"
          :items="chassiOptions"
          dense
          background-color="white"
          hide-selected
          v-model="filtros.chassi"
          @input="filtrarTabela"
        ></v-select>
      </div>
      <div class="filtro2">
        <v-select
          label="Status Sample"
          :items="statusSampleOptions"
          dense
          background-color="white"
          hide-selected
          v-model="filtros.statusSample"
          @input="filtrarTabela"
        ></v-select>
      </div>
      <v-spacer></v-spacer>
    </v-toolbar>  
    <v-card class="mx-auto" max-width="1200" style="height: 80%; text-align: center; margin-top: 70px; margin: 40px; width: 50 ">
      <v-btn  class="pdf" variant="text" style="margin-left: 94%;">
        <Icon icon="carbon:document-pdf" width="35" />
      </v-btn>
      <v-table width="800" height="450" style="margin: 60 auto; border-spacing: 10px; margin:30px;">
        <thead>
          <tr class="cabecalho" style="background-color: #333333; ">
            <th style="color: white;">Itens</th>
            <th style="color: white;">Status</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in paginatedItems" :key="index">
            <td style="border-bottom: 1px solid black;">{{ item.item }}</td>
            <td style="border-bottom: 1px solid black;">
              <v-chip :color="getStatusColor(item.statusSample)">{{ item.statusSample }}</v-chip>
            </td>
          </tr>
        </tbody>
      </v-table>
      <v-pagination
        v-model="page"
        :length="Math.ceil(filteredItems.length / perPage)"
        prev-icon="mdi-menu-left"
        next-icon="mdi-menu-right"
        style="margin: 20px;"
      ></v-pagination>
    </v-card>
  </div>
</template>


<script>
import axios from 'axios';
import { Icon } from '@iconify/vue';

export default {
  data() {
    return {
      // TABELA
      perPage: 8,
      dadosDaTabela: [],
      items: [],
      page: 1,
      // FILTROS
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
  components:{
    Icon
  },
  async created() {
    await this.inicializarDadosTabela();
  },
  methods: {
    async inicializarDadosTabela() {
      try {
        const response = await axios.get('consultor/2');

        const dados = response.data;
        this.dadosDaTabela = dados;
        this.items = this.dadosDaTabela.map(dado => {
          return {
            item: dado.item,
            statusSample: dado.statusSample,
            chassi: dado.chassi,
          }
        });
        this.obterOpcoesUnicas();
      } catch (error) {
        console.log(error);
      }
    },

    async filtrarTabela() {
      const { chassi, item, statusSample } = this.filtros;
      try {
        const response = await axios.get('consultor/2', {
          params: { chassi, item, statusSample }
        });
        const dadosFiltrados = response.data;
        this.items = dadosFiltrados.map(dado => {
          return {
            item: dado.item,
            statusSample: dado.statusSample,
            chassi: dado.chassi,
          }
        });
      } catch (error) {
        console.log(error);
        this.items = [];
      }

      this.page = 1;
    },
    // TRAZENDO EM ARRAY LISTA DE ITENS/STATUS/CHASSIS
    obterOpcoesUnicas() {
      const { dadosDaTabela } = this;
      const chassiOptions = new Set(dadosDaTabela.map(dado => dado.chassi));
      // const itemOptions = new Set(dadosDaTabela.map(dado => dado.item));
      const statusSampleOptions = new Set(dadosDaTabela.map(dado => dado.statusSample));
      this.chassiOptions = Array.from(chassiOptions).sort();
      // this.itemOptions = Array.from(itemOptions).sort();
      this.statusSampleOptions = Array.from(statusSampleOptions).sort();
    },
    // SETANDO CORES DOS STATUS DA TABELA
    getStatusColor(status) {
      switch (status) {
        case "INCORPORATED":
          return "success";
        case "NOT INCORPORATED":
          return "error";
        // case "Em Análise":
        //   return "warning";
        // default:
        //   return "";
      }
    },
  },
  // filtrar os itens de uma tabela com base nos valores dos filtros de pesquisa aplicados pelo usuário.
  computed: {
    filteredItems() {
  const { chassi, item, statusSample } = this.filtros;
  const filterByChassi = chassi !== "";
  // const filterByItem = item !== "";
  const filterByStatusSample = statusSample !== "";

  return this.items.filter(item => {
    let matches = true;
    if (filterByChassi) {
      matches = matches && item.chassi === chassi;
    }
    // if (filterByItem) {
    //   matches = matches && item.item === this.filtros.item;
    // }
    if (filterByStatusSample) {
      matches = matches && item.statusSample === statusSample;
    }
    return matches;
    });
  },

    // PAGINACAO
    paginatedItems() {
      const startIndex = (this.page - 1) * this.perPage;
      const endIndex = startIndex + this.perPage;
      return this.filteredItems.slice(startIndex, endIndex);
    }
  }
};
</script>

<style scoped>
.card-select{
margin-top: 0px;
max-width: 100%;
padding: 20px;
}
.filtro1{
width: 200px;
display: flex;
margin-top: 15px;
margin-right: 20px;
margin-left: 20px
}
.filtro2{
width: 200px;
display: flex;
margin-top: 15px;
margin-right: 20px;
margin-left: 20px;
}


@media only screen and (max-width: 600px) {
  .table {
    width: 100%;
  }
  .col-1,
  .col-2,
  .col-3 {
    display: block;
    width: 100%;
    text-align: center;
  }
  .v-card {
  width: 90%;
}
  .container {
    font-size: 14px;
  }
  .v-table td, .v-table th {
    padding: 5px;
  }
}
</style>