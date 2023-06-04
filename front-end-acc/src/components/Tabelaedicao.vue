<template>
  <div class="container">
    <!-- Barra de ferramentas com filtros -->
    <v-toolbar class="card-select" prominent>
      <v-spacer></v-spacer>
      <!-- filtro ITENS -->
      <div class="filtro1">
        <v-select
          label="Chassis"
          :items="chassiOptions"
          background-color="white"
          v-model="filtros.chassi"
          @input="filtrarTabela"
          @update:model-value="filtrar"
          variant="underlined"
        ></v-select>
      </div>

      <!-- filtro STATUS -->
      <div class="filtro2">
        <v-select
          label="Itens"
          :items="itemOptions"
          background-color="white"
          v-model="filtros.item"
          @input="filtrarTabela"
          variant="underlined"
          :disabled="!filtros.chassi"
        ></v-select>
      </div>

      <!-- filtro STATUS -->
      <div class="filtro3">
        <v-select
          label="Status Sample"
          :items="statusSampleOptions"
          background-color="white"
          v-model="filtros.statusSample"
          @input="filtrarTabela"
          variant="underlined"
          :disabled="!filtros.chassi"
        ></v-select>
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
      <!-- Tabela em si -->
      <v-table
        width="800"
        height="450"
        style="margin: 60 auto; border-spacing: 10px; margin: 30px"
      >
        <thead>
          <tr class="cabecalho" style="background-color: #333333">
            <th style="color: white; text-align: center">Chassi</th>
            <th style="color: white; text-align: center">Itens</th>
            <th style="color: white; text-align: center">Status</th>
            <th style="color: white; text-align: center">Update</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in paginatedItems" :key="index">
            <td style="border-bottom: 1px solid black">{{ item.chassi }}</td>
            <td style="border-bottom: 1px solid black">{{ item.item }}</td>

            <td style="border-bottom: 1px solid black">
              <v-chip :color="getStatusColor(item.statusSample)">{{
                item.statusSample
              }}</v-chip>
            </td>

            <td style="border-bottom: 1px solid black">
              <v-btn flat icon small @click="editarItem(item.idSample)">
                <v-icon>mdi-pencil</v-icon>
              </v-btn>
            </td>

            <v-dialog class="dialog" v-model="editModalOpen" max-width="500px">
              <v-card>
                <v-card-title>Edit Status</v-card-title>
                <v-card-text>
                  <v-form ref="form">
                    <v-select
                      label="Status Sample"
                      :items="['INCORPORATED', 'NOT INCORPORATED']"
                      v-model="statusEditado.statusSample"
                    ></v-select>

                    <v-text-field
                      v-model="statusEditado.idSample"
                      type="hidden"
                    ></v-text-field>
                  </v-form>
                </v-card-text>
                <v-card-actions>
                  <v-btn @click="editModalOpen = false">Cancel</v-btn>
                  <v-btn @click="salvarEdicao">Save</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </tr>
        </tbody>
      </v-table>
      <!-- Paginação da tabela -->
      <v-pagination
        v-model="page"
        :length="Math.ceil(filteredItems.length / perPage)"
        prev-icon="mdi-menu-left"
        next-icon="mdi-menu-right"
        style="margin: 20px"
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
        chassi: "",
        item: "",
        statusSample: "",
      },
      statusEditado: {
        statusSample: "",
      },
      chassiOptions: [],
      f: [],
      editModalOpen: false,
      statusSampleOptions: [],
      itens: [],
    };
  },
  component: {
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
        const response = await axios.get("consultor/2");
        const dados = response.data;
        this.dadosDaTabela = dados;
        this.items = this.dadosDaTabela.map((dado) => {
          return {
            item: dado.item,
            statusSample: dado.statusSample,
            idSample: dado.idSample,
            chassi: dado.chassi,
            idChassi: dado.idChassi,
          };
        });

        this.obterOpcoesUnicas();
        this.obterOpcoesItens();
        this.obterOpcoesStatus();
      } catch (error) {
        console.log(error);
      }
    },
    editarItem(idSample) {
      if (this.filtros.chassi) {
        this.statusEditado = this.paginatedItems.find(
          (u) => u.idSample === idSample
        );
        this.editModalOpen = true;
      } else {
        alert("Please select a chassis before editing.");
      }
    },

    async salvarEdicao() {
      try {
        if (
          this.statusEditado.statusSample == "NOT INCORPORATED" ||
          this.statusEditado.statusSample == "INCORPORATED"
        ) {
          const response = await axios.post(
            "/editar/save/" + this.statusEditado.idSample,
            { ...this.statusEditado }
          );
          alert("Updated successfully.");
        } else {
          alert("Alert! Please provide a valid permission.");
        }
      } catch (error) {
        console.error(error);
      } finally {
        this.editModalOpen = false;
      }
    },
    onItemSelected() {
      console.log("Item selecionado:");
    },

    async filtrarTabela() {
      const { chassi, item, statusSample } = this.filtros;
      try {
        const response = await axios.get("consultor/2", {
          params: { chassi, item, statusSample },
        });
        const dadosFiltrados = response.data;
        this.items = dadosFiltrados.map((dado) => {
          return {
            item: dado.item,
            statusSample: dado.statusSample,
            chassi: dado.chassi,
          };
        });
      } catch (error) {
        console.log(error);
        this.items = [];
      }
      this.page = 1;
    },
    async filtrar() {
      const { chassi } = this.filtros;
      this.obterOpcoesItens(chassi);
      this.obterOpcoesStatus(chassi);
      this.filtros.item = "";
      this.filtros.statusSample = "";
    },
    checkMobile() {
      this.isMobile = window.innerWidth < 768;
    },
    limparFiltro() {
      this.filtros.chassi = "";
      this.filtros.item = "";
      this.filtros.statusSample = "";
    },
    // TRAZENDO EM ARRAY LISTA DE ITENS/STATUS/CHASSIS
    obterOpcoesUnicas() {
      const { dadosDaTabela } = this;
      const chassiOptions = new Set(dadosDaTabela.map((dado) => dado.chassi));
      this.chassiOptions = Array.from(chassiOptions).sort();
    },
    obterOpcoesItens(chassi) {
      const { dadosDaTabela } = this;

      const itemOptions = new Set(
        dadosDaTabela
          .filter((dado) => dado.chassi === chassi)
          .map((dado) => dado.item)
      );
      this.itemOptions = Array.from(itemOptions).sort();
    },
    obterOpcoesStatus(chassi) {
      const { dadosDaTabela } = this;

      const statusSampleOptions = new Set(
        dadosDaTabela
          .filter((dado) => dado.chassi === chassi)
          .map((dado) => dado.statusSample)
      );
      this.statusSampleOptions = Array.from(statusSampleOptions).sort();
    },
    // SETANDO CORES DOS STATUS DA TABELA
    getStatusColor(status) {
      switch (status) {
        case "INCORPORATED":
          return "success";
        case "NOT INCORPORATED":
          return "error";
      }
    },
  },
  // filtrar os itens de uma tabela com base nos valores dos filtros de pesquisa aplicados pelo usuário.
  computed: {
    filteredItems() {
      const { chassi, statusSample, item } = this.filtros;
      const filterByChassi = chassi !== "";
      const filterByItem = item !== "";
      const filterByStatusSample = statusSample !== "";
      return this.items.filter((item) => {
        let matches = true;
        if (filterByChassi) {
          matches = matches && item.chassi === chassi;
        }
        if (filterByItem) {
          matches = matches && item.item === this.filtros.item;
        }
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

.filtro3 {
  width: 280px;
  display: flex;
  margin-top: 15px;
  margin-right: 20px;
  margin-left: 20px;
}
.v-table td {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.pdf {
  margin-right: 500px;
}

@media only screen and (max-width: 600px) {
  .table {
    font-size: 14px;
  }
  .filtro1,
  .filtro2,
  .filtro3 {
    width: 200px;
    margin-right: 10px;
    margin-top: 20px;
  }
  .v-table td {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .filtro1,
  .filtro2,
  .filtro3 {
    margin-top: 20px;
  }
  .v-card {
    width: 90%;
  }
  .container {
    font-size: 14px;
  }
  .v-table td,
  .v-table th {
    padding: 5px;
  }
}
</style>