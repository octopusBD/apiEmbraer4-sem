<template>
  <HeaderInput style="height: 5%"></HeaderInput>
  <Administrador style="height: 90%"></Administrador>
  <Footersrinput style="height: 5%"></Footersrinput>
  <ValidaToken :Tela="'Admin'" />
</template>

<script setup>
import HeaderInput from "@/components/HeaderInput.vue";
import Administrador from "@/components/Administrador.vue";
import Footersrinput from "@/components/Footersrinput.vue";
import ValidaToken from "@/components/ValidaToken.vue";
</script>
