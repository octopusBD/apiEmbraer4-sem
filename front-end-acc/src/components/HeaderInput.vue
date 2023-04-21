<template>
  <div class="container">
    <v-layout style="height: 138px; background-color: #253381;" class="border">
      <!--  Botão página inicial -->
      <v-btn to="home" class="home" variant="text">
        <Icon icon="ic:outline-home" width="35" />
      </v-btn>
      <!-- Define um div com uma imagem e um botão que exibe ou esconde notificações -->
      <div class="mx-auto my-4">
        <button style="background-color: #253381;" @click="active = !active">
          <img class="imgs" src="@/assets/logo-dois.png">
        </button>
      </div>
      <!-- Componente para a entrada de notificações -->
      <notificacao-input-vue class="notificacao"></notificacao-input-vue>
      <!-- Botões que levam a diferentes páginas -->
      <v-bottom-navigation :active="active" color="">
        <v-btn @click="redirect('administrador')">
          <Icon icon="clarity:administrator-line" width="25" />
          <span class="d-none d-sm-inline">ADMINISTRATOR</span>
        </v-btn>
        <v-btn @click="redirect('consulta')">
          <Icon icon="ic:outline-person-search" width="25" />
          <span class="d-none d-sm-inline">CONSULTANT</span>
        </v-btn>
        <v-btn  @click="redirect('editor')"> 
          <Icon icon="ph:pen" width="25" />
          <span class="d-none d-sm-inline">EDITOR</span>
        </v-btn>
      </v-bottom-navigation>
      <v-btn @click="redirect('sair')" class="sair" variant="text">
        <Icon icon="fluent:arrow-exit-20-regular" width="35" />
      </v-btn>
    </v-layout>
  </div>
</template>
<script>
import NotificacaoInputVue from '@/components/NotificacaoInput.vue'
import { Icon } from '@iconify/vue';
import router from '@/router/index.js'
export default {
  // Define um objeto de dados com uma variável booleana para exibir ou esconder notificações
  data: () => ({ active: true }),
  components: {
    Icon,
    NotificacaoInputVue
  },
  // Define uma matriz de itens para a navegação, mas não é usada em nenhum lugar do componente
  items: [{
    title: 'Dashboard',
    disabled: false,
    href: 'breadcrumbs_dashboard',
  }],
  methods: {
    redirect(tela) { 

      const token_header = sessionStorage.getItem('token');
      const loginUsuario_header = sessionStorage.getItem('loginUsuario');
      const autorizacao_header = sessionStorage.getItem('autorizacao');

      if(tela == 'administrador'){
        if(autorizacao_header != 'Administrator'){
          alert("Alert! You do not have permission to access this page.")
        }else{
          router.push('/administrador');
        }
      }

      if(tela == 'editor'){
        if(autorizacao_header == 'Editor' || autorizacao_header == 'Administrator'){
          
        }else{
          alert("Alert! You do not have permission to access this page.")
         //router.push('/home');
        }
      }

      if(tela == 'consulta'){
        router.push('/consulta');
      }

      if(tela == 'sair'){
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('loginUsuario');
        sessionStorage.removeItem('autorizacao');
        router.push('/');
      }
    }
  
  }
}
</script>
<style>
.imgs{
  margin-top: 0.08%;
  height: 55px ;
  width: auto ;
}

.notificacao{
  margin-top: 26px;
  margin-left: 3px;
  border-radius: 100rem;
  color: white;
}

.home{
  margin-top: 26px;
  margin-left: 5px;
  border-radius: 100rem;
  color: white;
}
.sair{
  margin-top: 26px;
  margin-left: 3px;
  border-radius: 100rem;
  color: white;
  margin-right: 5px;
}

@media only screen and (max-width: 600px) {
  .bottom-nav {
    flex-direction: column;
  }
  .nav-btn {
    margin: 1px 0;
  }
  .btn-home{
    size:"small"
  }
}
</style>