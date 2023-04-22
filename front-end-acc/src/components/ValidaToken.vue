<template>
    <div></div>
  </template>
  
  <script setup>
  import { onMounted } from 'vue'
  import router from '@/router/index.js'
  
  // Definindo a prop Tela
  const props = defineProps({
    Tela: String
  })

  // EVENTO QUE REALIZA A VALIDAÇÃO //
  onMounted(() => {
    const idUsuario =  sessionStorage.getItem('idUsuario');
    const loginUsuario = sessionStorage.getItem('loginUsuario');
    const senhaUsuario = sessionStorage.getItem('senhaUsuario');
    const autorizacao = sessionStorage.getItem('autorizacao');
    const token = sessionStorage.getItem('token');
    //alert("idUsuario:" + idUsuario + " " + "loginUsuario:" + loginUsuario + " " + "senhaUsuario:" + senhaUsuario + " " + "autorizacao:" + autorizacao + " " + "token:" + token);
    //alert(props.Tela);
    
    if (!token) {
      alert("Alert! You need to log into the application first.")
      setTimeout(() => {
        router.push('/');
      }, 100);
    } else {
      console.log('Token found');

      if(props.Tela == 'Admin'){
        if(autorizacao != 'Administrator'){
          //alert("Alert! You do not have permission to access this page.")
          router.push('/home');
        }
      }

      if(props.Tela == 'Editor'){
        if(autorizacao == 'Editor' || autorizacao == 'Administrator' ){
          //alert("Alert! You do not have permission to access this page.")
          router.push('/home');
        }
      }

      if(props.Tela != 'Consultant'){
        //Todos tem acesso ao Consultor
      }

    }
  })
</script>