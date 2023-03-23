<template>
  <v-layout style="height: 128px; background-color: #333333;" class="border rounded" >
    <div class="mx-auto my-4">
        <button  style= "background-color: #333333;"
        @click="active = !active">
         <img  class="imgs" 
         src="@/assets/logo-dois.png">
        </button>
    </div>
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
      <v-btn >
        <Icon  icon="ph:pen" width="25" />
        <span class="d-none d-sm-inline">EDITOR</span>
      </v-btn>
      <v-btn icon>
        <Icon icon="tabler:bell" width="25" />
        <span class="d-none d-sm-inline">NOTIFICAÇÃO</span>
      </v-btn>
      <v-btn>
        <Icon icon="mdi:user-circle-outline" width="25" />  
        <span class="d-none d-sm-inline">PERFIL</span>
      
      </v-btn>
    </v-bottom-navigation>
  </v-layout>
  <v-breadcrumbs to="/"
      :items="items"
      divider="-"
    >Home</v-breadcrumbs>

</template>

<script>
import { Icon } from '@iconify/vue';
  export default {
    data: () => ({ active: true }),
    components:{
      Icon
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
  height:50px ;
  width: auto ;
 }

 @media only screen and (max-width: 600px) {
  .bottom-nav {
    flex-direction: column;
  }
  .nav-btn {
    margin: 1px 0;
  }
}
</style>