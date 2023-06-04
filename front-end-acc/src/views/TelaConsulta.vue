<template class="container">
    <HeaderInput />
    <TabelaInput />
    <Footersrinput />
    <ValidaToken :Tela="'Consultor'" />
  </template>
  
  <script setup>
    import HeaderInput from '@/components/HeaderInput.vue'
    import TabelaInput from '@/components/TabelaInput.vue'
    import Footersrinput from '@/components/Footersrinput.vue'
    import ValidaToken from '@/components/ValidaToken.vue'
    
  </script>
