<template>
  <div class="container">
    <!-- Barra de ferramentas com filtros -->
    <v-toolbar class="card-select" prominent>
      <v-spacer></v-spacer>
      <!-- Primeiro filtro -->
      <div class="campo1">
        <v-text-field v-model="formula" label="Type it rule"></v-text-field>
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
        label="Select the item"
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
      <!-- Botão de exportação -->
      <div>
        <v-btn
          @click="onClick()"
          class="pdf"
          variant="text"
          style="margin-right: 94%"
        >
          Export - <Icon icon="carbon:document-export" width="35" />
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
            <td style="border-bottom: 1px solid black">{{ item.dtCadastro }}</td>
            <td style="border-bottom: 1px solid black">
              <v-btn flat icon small @click="editarItem(item.idUsuario)">
                <v-icon>mdi-pencil</v-icon>
              </v-btn>
            </td>

            <v-dialog class="dialog" v-model="editModalOpen" max-width="500px">
              <v-card>
                <v-card-title>Edit Rules</v-card-title>
                <v-card-text>
                  <v-form ref="form">
                    <v-text-field
                      label="Rules"
                      v-model="usuarioEditado.loginUsuario"
                      required
                    ></v-text-field>
                    <!-- <v-select
                      label="Permission"
                      :items="['Administrator','Editor', 'Consultant']"
                      v-model="usuarioEditado.permissao"
                      required
                    ></v-select> -->
                  </v-form>
                </v-card-text>
                <v-card-actions>
                  <v-btn @click="editModalOpen = false">Cancel</v-btn>
                  <v-btn @click="salvarEdicao">Save</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>

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

      // FILTROS
      formula: "",
      selectedItemId: null,
      itemOptions: [],
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
    };

    // Verificar se a fórmula já existe
    const existingFormula = this.items.find((item) => item.formula === this.formula);
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

    editarItem(idUsuario) {
      // Busca o usuário pelo ID e seta na variável usuarioEditado
      this.usuarioEditado = this.paginatedItems.find(
        (u) => u.idUsuario === idUsuario
      );
      this.editModalOpen = true;
    },
    async salvarEdicao() {
      try {
        //alert(this.usuarioEditado);

        if (
          this.usuarioEditado.permissao == "Administrator" ||
          this.usuarioEditado.permissao == "Editor" ||
          this.usuarioEditado.permissao == "Consultant"
        ) {
          const response = await axios.put(
            "/editor/update/" + this.usuarioEditado.idUsuario,
            { ...this.usuarioEditado }
          );
          alert("Updated successfully.");
        } else {
          alert("Alert! Please provide a valid permission.");
        }

        //console.log(this.usuarioEditado);
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
      } catch (error) {
        console.error(error);
        // Handle errors
      }
    },
    checkMobile() {
      this.isMobile = window.innerWidth < 768;
    },
    onClick() {
      const selecao = this.selectedItemId;
      console.log(selecao);
      if (!selecao) {
        alert("Please select an item");
        return;
      }
      axios({
        url: "pdf/2/" + selecao,
        method: "GET",
        responseType: "blob",
      }).then((response) => {
        var fileURL = window.URL.createObjectURL(new Blob([response.data]));
        var fileLink = document.createElement("a");

        fileLink.href = fileURL;
        fileLink.setAttribute("download", "export.pdf");
        document.body.appendChild(fileLink);

        fileLink.click();
      });
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
  .campo1,
  .campo2 {
    width: 200px;
    margin-right: 10px;
    margin-top: 20px;
  }
  .filtro1,
  .filtro2,
  .filtro3,
  .filtro4,
  .filtro5 {
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