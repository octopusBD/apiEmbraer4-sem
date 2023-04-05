<template>
  <div class="fundo">
    <div class="input-formatacao">
      <v-card
        class="card"
        width="400"
        height="739px"
        >
        <v-img
        class="logo-embraer"
        :width="200"
        src="@/assets/embraer-3.png"
        ></v-img>
        <v-sheet width="300" class="mx-auto">
          <v-form fast-fail @submit.prevent>
            <v-text-field
            density="compact"
            bg-color="white"
            hide-selected
            v-model="email"
            label="email"
            :rules="emailRules"
            ></v-text-field>
            <v-text-field
            class="teste"
            v-model="password"
            density="compact"
            bg-color="white"
            hide-selected
            label="password"
            :rules="passwordRules"
            :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
            :type="showPassword ? 'text' : 'password'"
            @click:append="showPassword = !showPassword"
            ></v-text-field>
          </v-form>
        </v-sheet>
          <a  class='referencia' href="/">Forgot your password?</a>  
          <v-btn
            class="btn-login"
            color="#253381"
            >
            <span>Login</span>
            <v-icon  class="icon" right>
                <svg focusable="false" preserveAspectRatio="xMidYMid meet" xmlns="http://www.w3.org/2000/svg" fill="currentColor" aria-label="Login" aria-hidden="true" width="24" height="24" viewBox="0 0 32 32" role="img">
                <path d="M22 16L12 26 10.6 24.6 19.2 16 10.6 7.4 12 6z"></path></svg>
            </v-icon>
          </v-btn>
          <!-- <div class="imgs">
            <v-img
            class="grupo"
            :width="200"
            src="@/assets/grupo.png"
            ></v-img> 
          </div> -->
        </v-card>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      email: '',
      password: '',
      showPassword: false
    }
    
  },
  computed: {
    passwordRules() {
      return [
        value => !!value || 'Required.',
        value => (value && value.length >= 8) || 'Password must be at least 8 characters.'
      ]
    },
    emailRules() {
      return [
        value => !!value || 'Required.',
        value => /.+@.+\..+/.test(value) || 'Invalid email.'
      ]
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