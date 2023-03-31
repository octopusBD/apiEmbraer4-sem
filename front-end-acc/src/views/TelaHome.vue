<template>
  <HeaderInput />
  <v-card>
  <!-- <img  class="ima" 
            src="@/assets/"> -->
  <Footersrinput/>
</v-card>
</template>

<script setup>
  import HeaderInput from '@/components/HeaderInput.vue'
  import Footersrinput from '@/components/Footersrinput.vue';
  
</script>

<style>
  .ima {
    max-width: 100%;
    height: auto;
    opacity: 70%;
    display: block;
    margin: 0 auto;
  }

  /* Media query for screens between 480px and 767px */
  @media (min-width: 480px) and (max-width: 767px) {
    .ima {
      max-width: 80%;
    }
  }

  /* Media query for screens between 768px and 1023px */
  @media (min-width: 768px) and (max-width: 1023px) {
    .ima {
      max-width: 50%;
    }
  }

  /* Media query for screens larger than 1024px */
  @media (min-width: 1024px) {
    .ima {
      max-width: 30%;
    }
  }
</style>
