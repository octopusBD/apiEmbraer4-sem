<template>
  <div class="container">
    <!-- Barra de ferramentas com filtros -->
    <v-toolbar class="card-select" prominent>
      <v-spacer></v-spacer>
      <!-- Primeiro filtro -->
      <div class="filtro1">
        <v-select
          label="User"
          :items="nomeUsuarioOptions"
          background-color="white"
          v-model="filtros.nomeUsuario"
          @input="filtrarTabela"
          @update:model-value="filtrarStatus"
          variant="underlined"
          class="filtro"
        ></v-select>
      </div>

      <!-- Segundo filtro -->
      <div class="filtro2">
        <v-select
          label="Status"
          :items="statusOptions"
          background-color="white"
          v-model="filtros.status"
          @input="filtrarTabela"
          variant="underlined"
          class="filtro"
          :disabled="!filtros.nomeUsuario"
        ></v-select>
      </div>
      <div>
        <v-col cols="auto">
          <v-btn
            class="limpar"
            v-show="!isMobile"
            density="comfortable"
            @click="limparFiltro"
            icon="mdi-eraser"
            size="50"
            height="50"
            width="50"
          ></v-btn>
        </v-col>
      </div>

      <v-spacer></v-spacer>
    </v-toolbar>
    <!-- Tabela de dados -->
    <v-card
      class="mx-auto"
      max-width="1200"
      style="
        height: 80%;
        text-align: center;
        margin-top: 70px;
        margin: 40px;
        width: 50;
      "
    >
      <!-- Botão de exportação -->
      <div>
        <v-btn
          @click="onClick()"
          class="pdf"
          variant="text"
          style="margin-right: 94%"
        >
          Export - <Icon icon="carbon:document-export" width="35" />
        </v-btn>
        <hr />
      </div>

      <!-- Tabela em si -->
      <v-table
        width="800"
        height="450"
        style="margin: 60 auto; border-spacing: 10px; margin: 30px"
      >
        <thead>
          <tr class="cabecalho" style="background-color: #333333">
            <th style="color: white; text-align: center">Chassi</th>
            <th style="color: white; text-align: center">Item</th>
            <th style="color: white; text-align: center">Status</th>
          </tr>
        </thead>

        <tbody style="align-items: center">
          <!-- Linhas da tabela, renderizadas com um loop -->
          <tr v-for="(item, index) in paginatedItems" :key="index">
            <td style="border-bottom: 1px solid black">{{ item.chassi }}</td>
            <td style="border-bottom: 1px solid black">{{ item.item }}</td>
            
            <td style="border-bottom: 1px solid black"><v-chip :color="getStatusColor(item.status)" >{{ item.status }}</v-chip></td>
            <!-- <td style="border-bottom: 1px solid black">{{ item.status }}</td> -->
          </tr>
        </tbody>
      </v-table>
      <v-pagination
        v-model="page"
        :length="Math.ceil(filteredItems.length / perPage)"
        prev-icon="mdi-menu-left"
        next-icon="mdi-menu-right"
        style="margin: 20px"
        :total-visible="2"
      ></v-pagination>
    </v-card>
  </div>
</template>
    <script>
import axios from "axios";
import { Icon } from "@iconify/vue";

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
        nomeUsuario: "",
        chassi: "",
        status: "",
        item: "",
      },
      nomeUsuarioOptions: [],
      itemOptions: [],
      statusOptions: [],
      chassiOptions: [],
    };
  },
  components: {
    Icon,
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
        const response = await axios.get("/formula/listarLogica");
        const dados = response.data;
        this.dadosDaTabela = dados;
        this.items = this.dadosDaTabela.map((dado) => {
          return {
            nomeUsuario: dado.nomeUsuario,
            status: dado.statusSample,
            chassi: dado.chassi,
            item: dado.itemNome,
          };
        });
        this.obterOpcoesUnicas();
        this.obterOpcoesStatus();
      } catch (error) {
        console.log(error);
      }
      this.page = 1; // define a página atual como 1
    },

    async filtrarTabela() {
      const { nomeUsuario, status, chassi, item } = this.filtros;
      try {
        const response = await axios.get("/formula/listarLogica", {
          params: { 
            itemNome: item,
            chassi: chassi,
            statusSample: status,
            nomeUsuario: nomeUsuario,
          },
        });
        const dadosFiltrados = response.data;
        this.items = dadosFiltrados.map((dado) => {
          return {
            nomeUsuario: dado.nomeUsuario,
            status: dado.statusSample,
            chassi: dado.chassi,
            item: dado.itemNome,
          };
        });
      } catch (error) {
        console.log(error);
        this.items = [];
      }
      this.page = 1;
    },

    async filtrarStatus() {
      const { loginUsuario, permissao } = this.filtros;
      this.obterOpcoesStatus(loginUsuario);
      this.filtros.status = "";
    },


    checkMobile() {
      this.isMobile = window.innerWidth < 768;
    },
    limparFiltro() {
      this.filtros.nomeUsuario = "";
      this.filtros.status = "";
      this.inicializarDadosTabela();
    },
    // TRAZENDO EM ARRAY LISTA DE ITENS/STATUS/CHASSIS
    obterOpcoesUnicas() {
      const { dadosDaTabela } = this;
      const nomeUsuarioOptions = new Set(
        dadosDaTabela.map((dado) => dado.nomeUsuario)
      );
      const chassiOptions = new Set(dadosDaTabela.map((dado) => dado.chassi));
      const itemOptions = new Set(dadosDaTabela.map((dado) => dado.item));

      this.nomeUsuarioOptions = Array.from(nomeUsuarioOptions).sort();
      this.chassiOptions = Array.from(chassiOptions).sort();
      this.itemOptions = Array.from(itemOptions).sort();
    },

    obterOpcoesStatus(loginUsuario) {
      const { dadosDaTabela } = this;
      
      const statusOptions = new Set(
        dadosDaTabela
        .filter((dado) => dado.loginUsuario === loginUsuario)
        .map((dado) => dado.statusSample)
      );
      this.statusOptions = Array.from(statusOptions).sort();
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
      const selecao_nomeUsuario = this.filtros.nomeUsuario;
      const selecao_status = this.filtros.status;
      if (selecao_nomeUsuario == "") {
        alert("Please select a user");
        return;
      }
      axios({
        url: "pdf/estatistica/" + selecao_nomeUsuario + "/" + selecao_status,
        method: "GET",
        responseType: "blob",
      }).then((response) => {
        var fileURL = window.URL.createObjectURL(new Blob([response.data]));
        var fileLink = document.createElement("a");
        fileLink.href = fileURL;
        fileLink.setAttribute("download", "relatório.pdf");
        document.body.appendChild(fileLink);
        fileLink.click();
      });
    },
  },
  // filtrar os itens de uma tabela com base nos valores dos filtros de pesquisa aplicados pelo usuário.
  computed: {
    filteredItems() {
      const { nomeUsuario, status, chassi, item } = this.filtros;
      const filterByChassi = chassi !== "";
      const filterByStatusSample = status !== "";
      const filterBynomeUsuario = nomeUsuario !== "";
      const filterByitem = item !== "";
      return this.items.filter((item) => {
        let matches = true;
        if (filterByChassi) {
          matches = matches && item.chassi === chassi;
        }
        if (filterByStatusSample) {
          matches = matches && item.status === status;
        }
        if (filterBynomeUsuario) {
          matches = matches && item.nomeUsuario === nomeUsuario;
        }
        if (filterByitem) {
          matches = matches && item.item === status;
        }
        return matches;
      });
    },
    // PAGINACAO
    paginatedItems() {
      const startIndex = (this.page - 1) * this.perPage;
      const endIndex = startIndex + this.perPage;
      return this.filteredItems.slice(startIndex, endIndex);
    },
  },
};
</script>
    
    <style scoped>
.card-select {
  margin-top: 0px;
  max-width: 100%;
  padding: 20px;
}
.filtro1 {
  width: 280px;
  display: flex;
  margin-top: 15px;
  margin-right: 20px;
  margin-left: 20px;
}

.filtro2 {
  width: 280px;
  display: flex;
  margin-top: 15px;
  margin-right: 20px;
  margin-left: 20px;
}

thead {
  text-align: center;
}
.v-table td {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
.pdf {
  margin-right: 500px;
}

.editar {
  background-color: transparent;
  border: none;
}

@media only screen and (max-width: 600px) {
  .table {
    font-size: 14px;
  }
  .filtro1,
  .filtro2 {
    width: 200px;
    margin-right: 10px;
    margin-top: 20px;
  }
  .filtro1,
  .filtro2 {
    margin-top: 20px;
  }
  .v-card {
    width: 90%;
  }
  .container {
    font-size: 14px;
  }
  .v-table td {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  .v-table td,
  .v-table th {
    padding: 5px;
  }
}
</style>