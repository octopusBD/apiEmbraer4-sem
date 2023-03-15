<template>
  <header class="header">
      <div class="logo-container">
          <img :src="require('@/assets/logo-dois.png')" alt="Minha Imagem">
      </div>
      <div class="button-container">
          <button class="btn" v-on:click="say('Adm')">ADMINISTRADOR</button>
          <button class="btn" v-on:click="say('Edit')">EDITOR</button>
          <button class="btn" v-on:click="say('Consulta')">CONSULTOR</button>
      </div>
      <div class="icons-buttons">
          <button class="btn" v-on:click="showNotificaticao">
              <i class="fas fa-bell"></i>
          </button>
          <button class="btn" v-on:click="showUserMenu">
              <i class="fas fa-user"></i>
          </button>
      </div>
  </header>
</template>

<script>
import '@fortawesome/fontawesome-free/css/all.css'
export default {
  name: "HeaderInput",
  props: {
    title: String,
    hideToggle: Boolean
  },
  methods: {
    say: function (message) {
        alert(message)
        }
    },
    showNotificacao() {
    },

    showUserMenu() {

    }
  }

</script>

<style>
.header {
grid-area: header;
background-color: #333333;
color: white;
display: flex;
justify-content: space-between;
align-items: center;
padding: 0 1rem;
}

.logo-container {
display: flex;
justify-content: flex-start;
align-items: center;
}

img {
height: 50px;
width: auto;
margin-top: 3px;
margin-right: 1rem;
}

.button-container {
display: flex;
align-items: center;
margin-top: 5px;
}

.btn {
color: aliceblue;
background-color: transparent;
border: transparent;
font-size: 16px;
padding: 0 1rem;
font-family: Helvetica, Arial, sans-serif;
}

.icons-buttons {
display: flex;
align-items: center;
}

@media (max-width: 768px) {
  .header {
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .button-container {
    margin-top: 1rem;
    
  }
  .btn {
    font-size: 14px;
    padding: 0.5rem 0.4rem;
    margin-top: -15px;
    
  }
  img {
    display: none;
 

  }
  .icons-buttons{
    margin-top: 20px;
    margin-bottom: 2px;
    
  }
}

</style>
