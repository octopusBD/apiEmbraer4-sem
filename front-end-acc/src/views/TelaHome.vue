<template>
 
  <HeaderInput />
  

  <div class="video-container">
    <video src="@/assets/video2.mp4" controls autoplay muted loop></video>
  </div>
  <ValidaToken :Tela="'Home'" />

  <Footersrinput/>
</template>

<script setup>
  import HeaderInput from '@/components/HeaderInput.vue'
  import Footersrinput from '@/components/Footersrinput.vue'
  import ValidaToken from '@/components/ValidaToken.vue'

</script>

<style>
  .video-container {
    position: relative;
    padding-bottom: 56.25%;
    background-size: cover;
  background-position: center;
  width: 100%;
  height: 100vh;
  }

  .video-container video {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    object-fit: cover;
  }


  @media only screen and (max-width: 600px) {
    .img {
  background-size: cover;
  background-position: center;
  width: 100%;
  height: 100vh;    }
  }
  

</style>
