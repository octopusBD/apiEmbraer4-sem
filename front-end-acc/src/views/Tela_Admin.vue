<template>
  <HeaderInput />
  <Administrador style="height: 100%"></Administrador>
  <Footersrinput />
  <ValidaToken :Tela="'Admin'" />
</template>

<script setup>
import HeaderInput from "@/components/HeaderInput.vue";
import Administrador from "@/components/Administrador.vue";
import Footersrinput from "@/components/Footersrinput.vue";
import ValidaToken from "@/components/ValidaToken.vue";
</script>
