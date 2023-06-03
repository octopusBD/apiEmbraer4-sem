<template>
  <div class="container">
    <!-- Barra de ferramentas com filtros -->
    <v-toolbar class="card-select" prominent>
      <v-spacer></v-spacer>
      <!-- Primeiro filtro -->
      <div class="campo1">
        <v-text-field
          class="oi"
          v-model="formula"
          label="Type rule"
        ></v-text-field>
      </div>
      <div>
        <Icon
          class="equals"
          icon="typcn:equals"
          size="25"
          height="25"
          width="25"
        />
      </div>
      <!-- Segundo filtro -->
      <div class="campo2">
        <v-select
          v-model="selectedItemId"
          :items="itemOptions.map((item) => item.itemNome)"
          label="Select item"
          outlined
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

      <div>
        <v-col cols="auto">
          <v-btn
            class="enviar"
            v-show="!isMobile"
            @click="enviarFormulario"
            icon="mdi-send"
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
      <!-- Tabela em si -->
      <v-table
        width="800"
        height="450"
        style="margin: 60 auto; border-spacing: 10px; margin: 30px"
      >
        <thead>
          <tr class="cabecalho" style="background-color: #333333">
            <th style="color: white; text-align: center">Rules</th>
            <th style="color: white; text-align: center">Item</th>
            <th style="color: white; text-align: center">Date</th>
            <th style="color: white; text-align: center">Update</th>
            <th style="color: white; text-align: center">Delete</th>
          </tr>
        </thead>

        <tbody style="align-items: center">
          <!-- Linhas da tabela, renderizadas com um loop -->
          <tr v-for="(item, index) in paginatedItems" :key="index">
            <td style="border-bottom: 1px solid black">{{ item.formula }}</td>
            <td style="border-bottom: 1px solid black">{{ item.itemNome }}</td>
            <td style="border-bottom: 1px solid black">
              {{ item.dtCadastro }}
            </td>
            <td style="border-bottom: 1px solid black">
              <v-btn flat icon small @click="editarItem(item.idFormula)">
                <v-icon>mdi-pencil</v-icon>
              </v-btn>
              <v-dialog
                class="dialog"
                v-model="editModalOpen"
                max-width="500px"
              >
                <v-card>
                  <v-card-title>Edit Rules</v-card-title>
                  <v-card-text>
                    <v-form ref="form">
                      <v-text-field
                        label="Rules"
                        v-model="usuarioEditado.formula"
                        required
                      ></v-text-field>
                    </v-form>
                  </v-card-text>
                  <v-card-actions>
                    <v-btn @click="editModalOpen = false">Cancel</v-btn>
                    <v-btn @click="salvarEdicao">Save</v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </td>

            <td style="border-bottom: 1px solid black">
              <v-btn class="deletar" flat @click="deleteItem(item)">
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
      editModalOpen: false,
      usuarioEditado: [],
      // FILTROS
      formula: "",
      selectedItemId: null,
      itemOptions: [],
    };
  },
  components: {
    Icon,
  },
  async mounted() {
    window.addEventListener("resize", this.checkMobile);
    this.checkMobile();
    await this.fetchItemNames(); // Adicione essa linha para buscar os nomes dos itens novamente ao montar ou atualizar o componente
  },
  async created() {
    await this.inicializarDadosTabela();
    await this.fetchItemNames();
  },
  methods: {
    async inicializarDadosTabela() {
      try {
        const response = await axios.get("formula/listarFormula");
        const dados = response.data;
        this.dadosDaTabela = dados;
        this.items = this.dadosDaTabela.map((dado) => {
          return {
            formula: dado.formula,
            itemNome: dado.itemNome,
            idItem: dado.idItem,
            dtCadastro: dado.dtCadastro,
            idFormula: dado.idFormula,
          };
        });
      } catch (error) {
        console.log(error);
      }
    },
    async fetchItemNames() {
      try {
        const response = await axios.get("formula/listarItem");
        this.itemOptions = response.data.map((item) => {
          return {
            id: item.id,
            itemNome: item.itemNome,
          };
        });
      } catch (error) {
        console.error(error);
        // Handle errors
      }
    },
    async enviarFormulario() {
      try {
        if (!this.selectedItemId || this.selectedItemId === "") {
          alert("Please select an item.");
          return;
        }

        const selectedItem = this.itemOptions.find(
          (item) => item.itemNome === this.selectedItemId
        );
        console.log(this.selectedItemId);

        if (this.formula === "") {
          alert("Please add a rule.");
          return;
        }

        // Verificar se a fórmula já existe
        const existingFormula = this.items.find(
          (item) => item.formula === this.formula
        );
        if (existingFormula) {
          alert("Rule already exists. Please enter a different rule.");
          return;
        }

        const response = await axios.post("formula/save", {
          formula: this.formula,
          item: selectedItem.id,
        });
        console.log(response.data);
        await this.inicializarDadosTabela();
        await this.fetchItemNames();
        this.formula = "";
        this.selectedItemId = null;
      } catch (error) {
        console.error(error);
        // Lidar com erros
      }
    },

    editarItem(idFormula) {
      this.usuarioEditado = this.paginatedItems.find(
        (u) => u.idFormula === idFormula
      );
      this.editModalOpen = true;
    },
    async salvarEdicao() {
      try {
        const response = await axios.put(
          "/formula/update/" + this.usuarioEditado.idFormula,
          {
            idFormula: this.usuarioEditado.idFormula,
            formula: this.usuarioEditado.formula,
            dtCadastro: this.usuarioEditado.dtCadastro,
            item: this.usuarioEditado.idItem,
          }
        );

        // Atualizar o objeto usuarioEditado nos dados locais
        const updatedItemIndex = this.items.findIndex(
          (item) => item.idFormula === this.usuarioEditado.idFormula
        );
        // if (updatedItemIndex !== -1) {
        //   this.items.splice(updatedItemIndex, 1, { ...this.usuarioEditado });
        // }

        alert("Updated successfully.");
        await this.inicializarDadosTabela();
        await this.fetchItemNames();
      } catch (error) {
        console.error(error);
      } finally {
        this.editModalOpen = false;
      }
    },

    async deleteItem(item) {
      try {
        const confirmed = confirm("Are you sure you want to delete this item?");
        if (!confirmed) {
          return;
        }

        const response = await axios.delete(`formula/delete/${item.idFormula}`);
        console.log(response.data);
        await this.inicializarDadosTabela();
        await this.fetchItemNames();
      } catch (error) {
        console.error(error);
        // Handle errors
      }
    },
    checkMobile() {
      this.isMobile = window.innerWidth < 768;
    },
    limparFiltro() {
      this.formula = "";
      this.selectedItemId = null;
    },
  },
  computed: {
    filteredItems() {
      let filteredItems = this.items;
      if (this.formula) {
        filteredItems = filteredItems.filter((item) =>
          item.formula.toLowerCase().includes(this.formula.toLowerCase())
        );
      }
      return filteredItems;
    },
    paginatedItems() {
      const start = (this.page - 1) * this.perPage;
      const end = start + this.perPage;
      return this.filteredItems.slice(start, end);
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
.campo1 {
  width: 250px;
  display: flex;
  margin-top: 15px;
  margin-right: 5px;
  margin-left: 150px;
}
.campo2 {
  width: 250px;
  display: flex;
  margin-top: 15px;
  margin-right: 10px;
  margin-left: 10px;
}
.v-table td {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

thead {
  text-align: center;
}
.pdf {
  margin-right: 500px;
}
.editar {
  background-color: transparent;
  border: none;
}
.limpar {
  margin-top: 0.1%;
  margin-left: 0.1%;
}
.enviar {
  margin-top: 0.1%;
  margin-left: 0.1%;
}

@media only screen and (max-width: 600px) {
  .table {
    font-size: 14px;
  }
 .equals{
  width: 20px;
 }
  .v-card {
    width: 90%;
  }
  .campo1 {
    margin-left: auto;

    padding-left: 15%;
  }
  .campo2 {
    margin-left: auto;
    margin-right: auto;
  }
  .container {
    font-size: 14px;
  }

  .v-table td {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  
}
</style>