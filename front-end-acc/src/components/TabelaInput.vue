<template>
  <div class="container">
    <v-card class="mx-auto" max-width="1200" style="height: 80%; text-align: center; margin-top: 70px; margin: 40px; width: 50 ">
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
        :length="Math.ceil(items.length / perPage)"
        prev-icon="mdi-menu-left"
        next-icon="mdi-menu-right"
        style="margin: 20px;"
      ></v-pagination>
    </v-card>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      perPage: 12,
      dadosDaTabela: [],
      items: [],
      page: 1
    };
  },

  async created() {
    const dados = await this.buscarDadosDaTabela();
    this.dadosDaTabela = dados;
    this.items = this.dadosDaTabela.map(dado => {
      return {
        item: dado.item,
        statusSample: dado.statusSample
      }
    });
  },

  methods: {
    getStatusColor(status) {
      if (status === 'INCORPORATED') {
        return 'green';
      } else if (status === 'NOT INCORPORATED') {
        return 'red';
      }
    },
    async buscarDadosDaTabela() {
      const response = await axios.get('sua-url-aqui');
      const dados = response.data;
      return dados;
    }
  },

  computed: {
    paginatedItems() {
      const start = (this.page - 1) * this.perPage;
      const end = start + this.perPage;
      return this.items.slice(start, end);
    },
  },

  watch: {
    page() {
      this.page = parseInt(this.page);
    },
  },
};
</script>


<style scoped>
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