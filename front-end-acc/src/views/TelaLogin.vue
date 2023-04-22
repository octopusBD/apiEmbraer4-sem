<template>
  <div class="fundo">
    <div class="input-formatacao">
      <v-card
        class="card"
        width="400"
        height="739px"
      >
        <!-- LOGO -->
        <v-img class="logo-embraer" :width="200" src="@/assets/embraer-3.png"></v-img>
        
        <!-- CAMPOS -->
        <v-sheet width="300" class="mx-auto">
          <v-form fast-fail @submit.prevent>
            <v-text-field
              density="compact"
              bg-color="white"
              hide-selected
              v-model="Login"
              label="Login"
            ></v-text-field
            >
            <v-text-field
              class="teste"
              density="compact"
              bg-color="white"
              hide-selected
              v-model="Password"
              label="Password"
              :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
              :type="showPassword ? 'text' : 'password'"
              @click:append="showPassword = !showPassword"
            ></v-text-field>

    
          </v-form>
        </v-sheet>

        <a  class='referencia' href="/">Forgot your password?</a>  
        <v-btn  @click="onClick()"  class="btn-login" color="#253381">
            <span>Login</span>
            <v-icon class="icon" right> <svg focusable="false" preserveAspectRatio="xMidYMid meet" xmlns="http://www.w3.org/2000/svg" fill="currentColor" aria-label="Login" aria-hidden="true" width="24" height="24" viewBox="0 0 32 32" role="img"> <path d="M22 16L12 26 10.6 24.6 19.2 16 10.6 7.4 12 6z"></path></svg> </v-icon>
          </v-btn>
      </v-card>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import router from '@/router/index.js'

export default {
  data() {
    return {
    
      Login: '',
      Password: '',
      showPassword: false
    }
    
  },
  methods: {
    async onClick() { 
        if (this.Login == ''){
          alert("Login Empty");
          return
        }

        if (this.Password == ''){
            alert("Password Empty");
            return
          }

      try {
          const response = await axios.post('login', {
            loginUsuario: this.Login,
            senhaUsuario: this.Password
          });
          

          const idUsuario = response.data.idUsuario;
          const senhaUsuario = response.data.senhaUsuario;
          const loginUsuario = response.data.loginUsuario;
          const autorizacao = response.data.autorizacao;
          const token = response.data.token;
          
          sessionStorage.setItem('idUsuario', idUsuario);
          sessionStorage.setItem('loginUsuario', loginUsuario);
          sessionStorage.setItem('senhaUsuario', senhaUsuario);
          sessionStorage.setItem('autorizacao', autorizacao);
          sessionStorage.setItem('token', token);
      
          // Redirect to the home page
          router.push('/home');
          //alert("Login successful");
          


      } catch (error) {
        if (error.response.status === 403) {
          alert("Login failed. Please check your credentials and try again.");
          return;
        }
        console.error(error);
      }
    }

  }
}
</script>

<style>
.card{
  margin-left: 555px;
}
.mx-auto{
  margin-top: 100px;
}
.btn-login{
  color: aliceblue !important;
  margin-left: 150px;
  height: 45px !important ;
  width: 110px !important;
  margin-top: 25px;
  border-radius: 10px !important;
}
.transition{
  background-color: 70ms cubic-bezier(.2,0,.38,.9),outline 70ms cubic-bezier(.2,0,.38,.9)
}
.referencia{
  margin-left: 145px;
  text-decoration: none;
  color:#253381;
  font-size: 12px;  
}
.imgs{
  margin-left:25%;
}
.v-text-field--enclosed:not(.v-text-field--readonly):not(.v-text-field--disabled) .v-input__control--is-focused {
  background-color: transparent !important;
}
.teste{
  width: 335px;
}
.logo-embraer{
  margin-left: 90px;
  margin-top: 60px;
}
.fundo{
  background-color: #253381;
}
</style>

