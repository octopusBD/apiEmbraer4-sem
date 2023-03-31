<template>
  <div id="footer">
    <div class="nav">
      <!-- Inicio do link para o repositório do OctopusBD no GitHub -->
      <a href="https://github.com/octopusBD/apiEmbraer4-sem">OctopusBD &copy;{{ new Date().getFullYear() }} | v.0.4 version</a>
      <!-- Fim do link para o repositório do OctopusBD no GitHub -->
    </div>
  </div>
</template>
<script>
export default {
  data: () => ({
    icons: [
      'mdi-github', // Define um ícone específico para a exibição no rodapé
    ],
  }),
}
</script>

<style scoped>
@media only screen and (max-width: 600px) {
  .nav {
    font-size: 15px;
  }

  #footer {
    height: 30px;
    background-color: #333333;
    color: white;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    padding: 0;
    position: fixed;
    bottom: 0;
  }

  a {
    color: white;
    text-decoration: none;
    font-size: 13px;
  }
}

@media only screen and (min-width: 601px) {
  .nav {
    font-size: 18px;
  }

  #footer {
    height: 50px;
    background-color: #333333;
    color: white;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    padding: 0;
    position: fixed;
    bottom: 0;
  }

  a {
    color: white;
    text-decoration: none;
    font-size: 16px;
  }
}
</style>
