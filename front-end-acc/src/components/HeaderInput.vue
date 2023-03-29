<template>
  <v-layout style="height: 128px; background-color: #253381;" class="border" >
    <!-- <v-btn size="45" to="/" variant="text"  background-color="transparent" class="btn-home" icon="mdi-home" ></v-btn> -->
  
  <v-btn @click="show = !show" class="btn-home" prepend-icon="mdi-home" variant="text" color="white">
          home
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
      <v-btn>
        <Icon icon="mdi:user-circle-outline" width="25" />  
        <span class="d-none d-sm-inline">PERFIL</span>
      
      </v-btn>
    </v-bottom-navigation>
  </v-layout>
  <button></button>
</template>

<script>
import { Icon } from '@iconify/vue'
import NotificacaoInputVue from '@/components/NotificacaoInput.vue'
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
    margin-top: 0;
    height:50px ;
    width: auto ;
  }
  
  .notificacao{
    margin-top: 15px;
  }

  .btn-home{
    margin-top: 15px;
    margin-left: 10px;
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