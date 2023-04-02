<template>
  <HeaderInput />

  <div class="video-container">
    <video src="@/assets/video.mp4" controls autoplay muted loop></video>
  </div>

  <Footersrinput/>
</template>

<script setup>
  import HeaderInput from '@/components/HeaderInput.vue'
  import Footersrinput from '@/components/Footersrinput.vue'
</script>

<style>
  .video-container {
    position: relative;
    padding-bottom: 56.25%; /* 16:9 aspect ratio */
    height: 0;
    overflow: hidden;
    max-width: 100%;
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
    .video-container {
      padding-bottom: 70%;
    }
  }

  .img{
    margin-top:0.3%;
    width: 12%;
  }

  @media only screen and (max-width: 600px) {
    .img {
      width: 20%;
    }
  }
  
  /* adicione outras regras de mídia para outros elementos aqui */

</style>
