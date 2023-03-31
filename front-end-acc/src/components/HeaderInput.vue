<template>
  <v-layout style="height: 138px; background-color: #253381;" class="border" >

    
    <v-btn to="/h" class="home" variant="text" >
      <Icon icon="material-symbols:assignment-return-outline-rounded" width="35" />
      </v-btn>

  <div class="mx-auto my-4">
      <button  style= "background-color: #253381;"
        @click="active = !active">
          <img  class="imgs" 
          src="@/assets/logo-dois.png">
      </button>
    </div>
    <notificacao-input-vue class="notificacao"></notificacao-input-vue>

    <v-bottom-navigation 
      :active="active"
      color="#253381"
    >
      <v-btn>
        <Icon icon="clarity:administrator-line" width="25" />
        <span class="d-none d-sm-inline">ADMINISTRADOR</span>
      </v-btn>
      <v-btn to="/consultor">
        <Icon icon="ic:outline-person-search" width="25" />
        <span   class="d-none d-sm-inline">CONSULTOR</span>
      </v-btn>
      <v-btn to="/editor">
        <Icon  icon="ph:pen" width="25" />
        <span class="d-none d-sm-inline">EDITOR</span>
      </v-btn>   
      <!-- <v-btn>
        <Icon icon="mdi:user-circle-outline" width="25" />  
        <span class="d-none d-sm-inline">PERFIL</span>
      
      </v-btn> -->
    </v-bottom-navigation>
  </v-layout>
</template>

<script>
import NotificacaoInputVue from '@/components/NotificacaoInput.vue'
import { Icon } from '@iconify/vue';
  export default {
    data: () => ({ active: true }),
    components:{
      Icon,
      NotificacaoInputVue
    },
    items: [
        {
          title: 'Dashboard',
          disabled: false,
          href: 'breadcrumbs_dashboard',
        },]

  }
  </script>
  <style>
  .imgs{
    margin-top: 0.08%;
    height: 55px ;
    width: auto ;
  }
  
  .notificacao{
    margin-top: 25px;
    
  }

  .home{
    margin-top: 26px;
    margin-left: 5px;
    border-radius: 10rem;
    color: white;

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