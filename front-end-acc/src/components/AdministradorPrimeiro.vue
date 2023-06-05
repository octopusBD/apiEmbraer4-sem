<template>
  <div class="container">
    <!-- Barra de ferramentas com filtros -->
    <v-toolbar class="card-select" prominent>
      <v-spacer></v-spacer>
      <!-- Segundo filtro -->
      <div class="filtro2">
        <v-select
          label="Permission"
          :items="permissaoOptions"
          background-color="white"
          v-model="filtros.permissao"
          @input="filtrarTabela"
          @update:model-value="filtrarUser"
          variant="underlined"
        ></v-select>
      </div>

      <!-- Primeiro filtro -->
      <div class="filtro1">
        <v-select
          label="User"
          :items="nomeUsuarioOptions"
          background-color="white"
          v-model="filtros.loginUsuario"
          @input="filtrarTabela"
          variant="underlined"
          :disabled="!filtros.permissao"
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
      <!-- Tabela em si -->
      <v-table
        width="800"
        height="450"
        style="margin: 60 auto; border-spacing: 10px; margin: 30px"
      >
        <thead>
          <tr class="cabecalho" style="background-color: #333333">
            <th style="color: white; text-align: center">User</th>
            <th style="color: white; text-align: center">Permission</th>
            <th style="color: white; text-align: center">Update</th>
            <th style="color: white; text-align: center">Delete</th>
          </tr>
        </thead>

        <tbody style="align-items: center">
          <!-- Linhas da tabela, renderizadas com um loop -->
          <tr v-for="item in paginatedItems" :key="item.idUsuario">
            <td style="border-bottom: 1px solid black">
              {{ item.loginUsuario }}
            </td>
            <td style="border-bottom: 1px solid black">{{ item.permissao }}</td>

            <td style="border-bottom: 1px solid black">
              <v-btn flat icon small @click="editarItem(item.idUsuario)">
                <v-icon>mdi-pencil</v-icon>
              </v-btn>
            </td>

            <v-dialog class="dialog" v-model="editModalOpen" max-width="500px">
              <v-card>
                <v-card-title>Edit User</v-card-title>
                <v-card-text>
                  <v-form ref="form">
                    <v-text-field
                      label="User"
                      v-model="usuarioEditado.loginUsuario"
                      required
                    ></v-text-field>
                    <v-select
                      label="Permission"
                      :items="['Administrator','Editor', 'Consultant']"
                      v-model="usuarioEditado.permissao"
                      required
                    ></v-select>
                    <!-- <v-checkbox label="Ativo" v-model="usuarioEditado.ativo"></v-checkbox> -->
                  </v-form>
                </v-card-text>
                <v-card-actions>
                  <v-btn @click="editModalOpen = false">Cancel</v-btn>
                  <v-btn @click="salvarEdicao">Save</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>

            <td style="border-bottom: 1px solid black">
              <v-btn flat icon small @click="deleteItemConfirm(item.idUsuario)">
                <v-icon>mdi-delete</v-icon></v-btn
              >
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
        :total-visible="2"
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
      showModal: false,
      // TABELA
      perPage: 8,
      dadosDaTabela: [],
      items: [],
      page: 1,
      isMobile: false,
      // FILTROS
      filtros: {
        loginUsuario: "",
        permissao: "",
        idUsuario: "",
      },
      nomeUsuarioOptions: [],
      permissaoOptions: [],
      idUsuarioOptions: [],
      itens: [],
      editModalOpen: false,
      usuarioEditado: {
        loginUsuario: "",
        permissao: "",
      },
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
        const response = await axios.get("usuario/listar");
        const dados = response.data;
        this.dadosDaTabela = dados;
        this.items = this.dadosDaTabela.map((dado) => {
          return {
            loginUsuario: dado.loginUsuario,
            permissao: dado.permissao,
            idUsuario: dado.idUsuario,
          };
        });
        this.obterOpcoesUnicas();
        this.obterOpcoesNomes();
      } catch (error) {
        console.log(error);
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
            "/usuario/update/" + this.usuarioEditado.idUsuario,
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

    async filtrarTabela() {
      const { loginUsuario, permissao } = this.filtros;
      try {
        const response = await axios.get("usuario/listar", {
          params: { loginUsuario, permissao },
        });
        const dadosFiltrados = response.data;
        this.items = dadosFiltrados.map((dado) => {
          return {
            loginUsuario: dado.loginUsuario,
            permissao: dado.permissao,
          };
        });
      } catch (error) {
        console.log(error);
        this.items = [];
      }
      this.page = 1;
    },
    deleteItemConfirm(itemId) {
      if (confirm("Are you sure you want to delete this item?")) {
        axios.delete("/usuario/delete/" + itemId).then((response) => {
          this.inicializarDadosTabela();
          console.log(response);
        });
      }
    },

    checkMobile() {
      this.isMobile = window.innerWidth < 768;
    },
    limparFiltro() {
      this.filtros.permissao = "";
      this.filtros.loginUsuario = "";
      this.filtrarTabela();
    },
    async filtrarUser() {
      const { loginUsuario, permissao } = this.filtros;
      this.obterOpcoesNomes(permissao);
      this.filtros.loginUsuario = "";
    },
    // TRAZENDO EM ARRAY LISTA DE ITENS/STATUS/loginUsuarioS
    obterOpcoesUnicas() {
      const { dadosDaTabela } = this;   
      const permissaoOptions = new Set(
        dadosDaTabela.map((dado) => dado.permissao)
      );
      this.permissaoOptions = Array.from(permissaoOptions).sort();
    },
    obterOpcoesNomes(permissao) {
      const { dadosDaTabela } = this;
      
      const nomeUsuarioOptions = new Set(
        dadosDaTabela
        .filter((dado) => dado.permissao === permissao)
        .map((dado) => dado.loginUsuario)
      );
      this.nomeUsuarioOptions = Array.from(nomeUsuarioOptions).sort();
    },
  },
  // filtrar os itens de uma tabela com base nos valores dos filtros de pesquisa aplicados pelo usuário.
  computed: {
    filteredItems() {
      const { loginUsuario, permissao } = this.filtros;
      const filterByloginUsuario = loginUsuario !== "";
      // const filterByItem = item !== "";
      const filterBypermissao = permissao !== "";
      return this.items.filter((item) => {
        let matches = true;
        if (filterByloginUsuario) {
          matches = matches && item.loginUsuario === loginUsuario;
        }
        // if (filterByItem) {
        //   matches = matches && item.item === this.filtros.item;
        // }
        if (filterBypermissao) {
          matches = matches && item.permissao === permissao;
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
    font-size: 14px; /* diminui o tamanho da fonte para melhor legibilidade em telas pequenas */
  }
  .filtro1,
  .filtro2 {
    width: 200px;
    margin-right: 8px;
    margin-top: 20px;
  }
  .v-table td {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
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
  .v-table td,
  .v-table th {
    padding: 5px;
  }
  .dialog {
    margin-left: 30px;
  }
}
</style>