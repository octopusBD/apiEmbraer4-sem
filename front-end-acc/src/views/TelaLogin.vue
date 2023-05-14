<template>
  <div class="fundo">
    <div class="input-formatacao">
      <v-card class="card">
        <!-- LOGO -->
        <v-img
          class="logo_embraer"
          :width="200"
          src="@/assets/embraer-3.png"
        ></v-img>

        <!-- CAMPOS -->
        <div class="borderr">
          <v-sheet width="300" class="mx-auto">
            <v-form fast-fail @submit.prevent>
              <v-text-field
                density="compact"
                bg-color="white"
                hide-selected
                v-model="Login"
                label="Login"
              ></v-text-field>
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
                    {{ showPassword ? "mdi-eye" : "mdi-eye-off" }}
                  </v-icon>
                </template>
              </v-text-field>
            </v-form>
          </v-sheet>
        </div>

        <!-- <a  class='referencia' href="/">Forgot your password?</a>   -->
        <v-btn @click="onClick()" class="btn-login" color="#253381">
          <span>Login</span>
          <v-icon class="icon" right>
            <svg
              focusable="false"
              preserveAspectRatio="xMidYMid meet"
              xmlns="http://www.w3.org/2000/svg"
              fill="currentColor"
              aria-label="Login"
              aria-hidden="true"
              width="24"
              height="24"
              viewBox="0 0 32 32"
              role="img"
            >
              <path d="M22 16L12 26 10.6 24.6 19.2 16 10.6 7.4 12 6z"></path>
            </svg>
          </v-icon>
        </v-btn>
        <div class="card-footer">OctopusBD &copy;</div>
      </v-card>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router/index.js";

export default {
  data() {
    return {
      Login: "",
      Password: "",
      showPassword: false,
    };
  },
  methods: {
    async onClick() {
      if (this.Login == "") {
        alert("Login Empty");
        return;
      }

      if (this.Password == "") {
        alert("Password Empty");
        return;
      }

      try {
        const response = await axios.post("login", {
          loginUsuario: this.Login,
          senhaUsuario: this.Password,
        });

        const idUsuario = response.data.idUsuario;
        const senhaUsuario = response.data.senhaUsuario;
        const loginUsuario = response.data.loginUsuario;
        const autorizacao = response.data.autorizacao;
        const token = response.data.token;

        sessionStorage.setItem("idUsuario", idUsuario);
        sessionStorage.setItem("loginUsuario", loginUsuario);
        sessionStorage.setItem("senhaUsuario", senhaUsuario);
        sessionStorage.setItem("autorizacao", autorizacao);
        sessionStorage.setItem("token", token);

        // Redirect to the home page
        router.push("/home");
        //alert("Login successful");
      } catch (error) {
        if (error.response.status === 403) {
          alert("Login failed. Please check your credentials and try again.");
          return;
        }
        console.error(error);
      }
    },
  },
};
</script>

<style>

.card {
  margin: 0 auto;
  width: 25%;
  height: 100%;
}


.card-footer {
  text-align: center;
  margin-top: 50%;
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
}

.mx-auto {
  margin-top: 30%;
}

.btn-login {
  color: aliceblue !important;
  margin-left: 36%;
  height: 5% !important;
  max-width: 25%;
  margin-top: 15%;
  border-radius: 15px !important;
  display: flex;
  justify-content: center;
  align-items: center;

}

.transition {
  background-color: 70ms cubic-bezier(0.2, 0, 0.38, 0.9),
    outline 70ms cubic-bezier(0.2, 0, 0.38, 0.9);
}

.referencia {
  text-decoration: none;
  color: #253381;
  font-size: 12px;
}

.imgs {
  max-width: 100%;
}

.v-text-field--enclosed:not(.v-text-field--readonly):not(
    .v-text-field--disabled
  )
  .v-input__control--is-focused {
  background-color: transparent !important;
}

.logo_embraer {
  padding: 10%;
  margin: 35% auto 0;
  width: 50%;
  height: 10%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.fundo {
  background-color: #253381;
  width: 100%;
  height: 100%;
}

.input-formatacao {
  height: 100%;
}

@media only screen and (max-width: 600px) {
  .btn-login {
    color: aliceblue !important;
    height: 45px !important;
    width: 110px !important;
    margin: 25px auto 0 auto;
    border-radius: 10px !important;
    background-color: white;
    display: block;
  }

  .mx-auto {
    margin-top: 3%;
  }

  .card-footer {
    margin-top: 5%;
  }

  .logo-embraer {
    margin-left: 30px;
  }

  .card {
    width: 100%;
  }

  

  /* .mx-auto {
    margin-top: 10%;
  }

  .card-footer {
    margin-top: 20%;
  }

  .logo-embraer {
    margin-left: 110px;
  } */

/* Estilos para telas médias */
  @media only screen and (min-width: 601px) and (max-width: 1024px) {
  .btn-login {
    color: aliceblue !important;
    height: 40px !important;
    width: 100px !important;
    margin: 15px auto 0 auto;
    border-radius: 8px !important;
    background-color: white;
    display: block;
  }

  .mx-auto {
    margin-top: 5%;
  }

  .card-footer {
    margin-top: 10%;
  }

  .logo-embraer {
    margin-left: 60px;
  }

  .card {
    width: 50%;
  }
}

  /* Estilos para telas grandes */
@media only screen and (min-width: 1025px) {
  .btn-login {
    color: aliceblue !important;
    height: 50px !important;
    width: 120px !important;
    margin: 30px auto 0 auto;
    border-radius: 12px !important;
    background-color: white;
    display: block;
  }

  .mx-auto {
    margin-top: 15%;
  }

  .card-footer {
    margin-top: 25%;
  }

  .logo-embraer {
    margin-left: 150px;
  }
  .card {
    width: 40%;
  }
}

}
</style>


