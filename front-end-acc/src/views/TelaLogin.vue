<template>
  <div class="fundo">
    <div class="input-formatacao">
      <v-card
        class="card"
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
  :type="showPassword ? 'text' : 'password'"
  @click:append="showPassword = !showPassword"
>
  <template #append>
    <v-icon @click="showPassword = !showPassword">
      {{ showPassword ? 'mdi-eye' : 'mdi-eye-off' }}
    </v-icon>
  </template>
</v-text-field>


    
          </v-form>
        </v-sheet>

        <!-- <a  class='referencia' href="/">Forgot your password?</a>   -->
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
  margin-left: 37%;
  width: 27.5%;
  height: 100%;
}
.mx-auto{
  margin-top: 100px;
}
.btn-login{
  color: aliceblue !important;
  margin-left: 37%;
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
  margin-left:100%;
}
.v-text-field--enclosed:not(.v-text-field--readonly):not(.v-text-field--disabled) .v-input__control--is-focused {
  background-color: transparent !important;
}
.teste{
  width: 100%;
}
.logo-embraer{
  margin-left: 118px;
  margin-top: 60px;
}
.fundo{
  background-color: #253381;
  width: 100%;
  height: 100%;
}
.input-formatacao{
  height: 100%;
}
@media only screen and (max-width: 600px) {
  .card {
    margin: 0 auto;
    width: 100%;
  }
  .logo-embraer{
    margin-left: 85.5px;
  }
  .teste{
    width:290px ;
  }
  .btn-login{
  color: aliceblue !important;
  align-items: 30px;
  height: 45px !important ;
  width: 110px !important;
  margin-top: 25px;
  border-radius: 10px !important;
}
}


</style>

