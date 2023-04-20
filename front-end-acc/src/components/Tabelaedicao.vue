<template>
  <div class="container">
    <!-- Barra de ferramentas com filtros -->
    <v-toolbar class="card-select" prominent>
      <v-spacer></v-spacer>
      <!-- Primeiro filtro -->
      <div class="filtro1">
        <v-select
          label="Chassis"
          :items="chassiOptions"
          background-color="white"
          v-model="filtros.chassi"
          @input="filtrarTabela"
          variant="underlined"
        ></v-select>
      </div>
      <!-- Segundo filtro -->
      <div class="filtro2">
        <v-select
          label="Status Sample"
          :items="statusSampleOptions"
          background-color="white"
          v-model="filtros.statusSample"
          @input="filtrarTabela"
          variant="underlined"
        ></v-select>
        <div>
          <v-col cols="auto">
            <v-btn class="limpar" v-show="!isMobile" density="comfortable"  @click="limparFiltro" icon="mdi-eraser" size="50" height="50" width="50"></v-btn>
          </v-col>
        </div>
      </div>
      <v-spacer></v-spacer>
    </v-toolbar>
    <!-- Tabela de dados -->
    <v-card class="mx-auto" max-width="1200" style="height: 80%; text-align: center; margin-top: 70px; margin: 40px; width: 50 ">
      <!-- Botão de exportação -->
      <div>
      <v-btn  @click="onClick()" class="pdf" variant="text" style="margin-right: 94%;">
        Export - <Icon icon="carbon:document-export" width="35"/> 
      </v-btn>
      <hr>
    </div>
      <!-- Tabela em si -->
      <v-table width="800" height="450" style="margin: 60 auto; border-spacing: 10px; margin:30px;">
        <thead>
          <tr class="cabecalho" style="background-color: #333333;">
          <th style="color: white; text-align: center;">Itens</th>
          <th style="color: white; text-align: center;">Status</th>
          <th style="color: white; text-align: center;">Update</th>
          <th style="color: white; text-align: center;">Delete</th>
              

        </tr>
        </thead>
        <tbody>
          <!-- Linhas da tabela, renderizadas com um loop -->
          <tr v-for="(item, index) in paginatedItems" :key="index">
            <td style="border-bottom: 1px solid black;">{{ item.item }}</td>
            <td style="border-bottom: 1px solid black;">
              <!-- Chips coloridos com o status da amostra -->
              <v-chip :color="getStatusColor(item.statusSample)">{{ item.statusSample }}</v-chip>
            </td>
            <td style="border-bottom: 1px solid black">
            <v-btn class="editar" flat @click="editItem(index)">
              <v-icon class="mdi mdi-pencil"></v-icon>
            </v-btn>
          </td>
          <td style="border-bottom: 1px solid black">
            <v-btn class="deletar" flat @click="deleteItem(index)">
              <v-icon>mdi-delete</v-icon>
            </v-btn>
          </td>
          </tr>
        </tbody>
      </v-table>
      <!-- Paginação da tabela -->
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
      isMobile: false,
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
   mounted() {
    window.addEventListener("resize", this.checkMobile);
    this.checkMobile();
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
    checkMobile() {
      this.isMobile = window.innerWidth < 768;
    },
  limparFiltro() {
    this.filtros.statusSample = "";
    this.filtrarTabela();
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
      onClick() { 
        const selecao = this.filtros.chassi; // obter a seleção
        console.log(selecao); // exibir a seleção no console
        if (selecao == ''){
          alert("Please select a chassi");
          return
        }
        axios({
        url: 'pdf/2/' + selecao,
        method: 'GET',
        responseType: 'blob',
      }).then((response) => {
        var fileURL = window.URL.createObjectURL(new Blob([response.data]));
        var fileLink = document.createElement('a');
        fileLink.href = fileURL;
        fileLink.setAttribute('download', 'relatório.pdf');
        document.body.appendChild(fileLink);
        fileLink.click();
      });
    }
  },
  // filtrar os itens de uma tabela com base nos valores dos filtros de pesquisa aplicados pelo usuário.
  computed: {
    filteredItems() {
      const { chassi, statusSample } = this.filtros;
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
  width: 280px;
  display: flex;
  margin-top: 15px;
  margin-right: 20px;
  margin-left: 20px
  }
  .filtro2{
  width: 280px;
  display: flex;
  margin-top: 15px;
  margin-right: 20px;
  margin-left: 20px;
  }

  .pdf{
    margin-right: 500px;
  }

  @media only screen and (max-width: 600px) {
    .table {
    font-size: 14px; /* diminui o tamanho da fonte para melhor legibilidade em telas pequenas */
  } 
  .filtro1, .filtro2 {
    width: 200px;
    margin-right: 10px;
    margin-top: 20px;
  }
  /* .limpar {
    margin-left: auto;
    margin-right: 0;
    margin-top: 20px;
  } */
  .filtro1, .filtro2 {
    margin-top: 20px;
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