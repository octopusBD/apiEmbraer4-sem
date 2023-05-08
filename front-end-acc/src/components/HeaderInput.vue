<template>
  <v-app-bar class="bar-um" style="background-color: #253381;  border-radius: 0;
  border-color: none;" rounded>
    <v-btn
      to="home"
      class="home"
      size="50"
      height="50"
      width="50"
      variant="text"
      
    >
      <Icon icon="ic:outline-home" width="35" />
    </v-btn>
    <div class="mx-auto my-4 text-center">
      <button>
        <img class="imgs" src="@/assets/logo-dois.png" />
      </button>
    </div>
    <notificacao-input-vue class="notificacao"></notificacao-input-vue>
    <v-btn
      @click="redirect('sair')"
      class="sair"
      size="50"
      height="50"
      width="50"
      variant="text"
    >
      <Icon icon="fluent:arrow-exit-20-regular" width="35" />
    </v-btn>
  </v-app-bar>
  <v-layout class="overflow-visible" style="height: 56px;">
    <v-bottom-navigation :active="active">
      <div style="margin-right: 3%;">
        <v-btn @click="redirect('administrador')" class="administrador">
          <Icon icon="clarity:administrator-line" width="25" />
          <span class="d-none d-sm-inline">ADMINISTRATOR</span>
        </v-btn>
        <v-btn @click="redirect('consulta')" class="consulta">
          <Icon icon="ic:outline-person-search" width="25" />
          <span class="d-none d-sm-inline">CONSULTANT</span>
        </v-btn>
        <v-btn @click="redirect('editor')" class="editar">
          <Icon icon="ph:pen" width="25" />
          <span class="d-none d-sm-inline">EDITOR</span>
        </v-btn>
      </div>
    </v-bottom-navigation>
  </v-layout >
</template>
<script>
import NotificacaoInputVue from "@/components/NotificacaoInput.vue";
import { Icon } from "@iconify/vue";
import router from "@/router/index.js";
export default {
  // Define um objeto de dados com uma variável booleana para exibir ou esconder notificações
  data: () => ({ active: true }),
  components: {
    Icon,
    NotificacaoInputVue,
  },
  // Define uma matriz de itens para a navegação, mas não é usada em nenhum lugar do componente
  items: [
    {
      title: "Dashboard",
      disabled: false,
      href: "breadcrumbs_dashboard",
    },
  ],
  methods: {
    redirect(tela) {
      const token_header = sessionStorage.getItem("token");
      const loginUsuario_header = sessionStorage.getItem("loginUsuario");
      const autorizacao_header = sessionStorage.getItem("autorizacao");

      if (tela == "administrador") {
        if (autorizacao_header != "Administrator") {
          alert("Alert! You do not have permission to access this page.");
        } else {
          router.push("/administrador");
        }
      }

      if (tela == "editor") {
        if (autorizacao_header == "Editor" || autorizacao_header == "Administrator") {
          router.push('/editor');
        } else {
          alert("Alert! You do not have permission to access this page.");
          //router.push('/home');
        }
      }

      if (tela == "consulta") {
        router.push("/consulta");
      }

      if (tela == "sair") {
        sessionStorage.removeItem("token");
        sessionStorage.removeItem("loginUsuario");
        sessionStorage.removeItem("autorizacao");
        router.push("/");
      }
    },
  },
};
</script>
<style>
.imgs {
  margin-top: 5%;
  height: 55px;
}

.notificacao {
  margin-top: 0.1%;
  /* margin-left: 1%; */
  border-radius: 100rem;
  color: white;
}

.home {
  margin-top: 0.1%;
  margin-left: 0.5%;
  border-radius: 100rem;
  color: white;
}
.sair {
  margin-top: 0.1%;
  margin-left: 0.1%;
  border-radius: 100rem;
  color: white;
}

@media only screen and (max-width: 600px) {


  .imgs {
    margin-left: 5%;
    margin-right: 5%;
    width: 150px;
  
  }
}
</style>