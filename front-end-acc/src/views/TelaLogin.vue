<template>
  <div class="fundo">
    <div class="div-logs">
      <v-img
        :width="logoWidth"
        style="margin-left: 5%;"
        src="@/assets/logo-dois.png"
      ></v-img>
    </div>

      <v-card class="card">
        <v-text-field
          class="teste"
          dense
          hide-details
          v-model="login"
          label="Login"
        ></v-text-field>
        <v-text-field
          class="teste"
          dense
          hide-details
          v-model="password"
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

        <div class="btn-div">
          <v-btn @click="onClick" class="btn-login" color="#253381">
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
        </div>
      </v-card>
    </div>
</template>

<script>
import axios from "axios";
import router from "@/router/index.js";

export default {
  data() {
    return {
      login: "",
      password: "",
      showPassword: false,
      logoWidth: 200,
    };
  },
  methods: {
    async onClick() {
      if (this.login === "") {
        alert("Login Empty");
        return;
      }

      if (this.password === "") {
        alert("Password Empty");
        return;
      }

      try {
        const response = await axios.post("login", {
          loginUsuario: this.login,
          senhaUsuario: this.password,
        });

        const { idUsuario, senhaUsuario, loginUsuario, autorizacao, token } =
          response.data;

        sessionStorage.setItem("idUsuario", idUsuario);
        sessionStorage.setItem("loginUsuario", loginUsuario);
        sessionStorage.setItem("senhaUsuario", senhaUsuario);
        sessionStorage.setItem("autorizacao", autorizacao);
        sessionStorage.setItem("token", token);

        // Redirect to the home page
        router.push("/home");
      } catch (error) {
        if (error.response && error.response.status === 403) {
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
.fundo {
  background-image: url("https://cdn-cavok.nuneshost.com/wp-content/uploads/2019/07/E195-E2-TechLion.jpg");
  background-size: cover;
  background-position: center;
  width: 100%;
  height: 100vh;
}

.div-logs {
  padding-right: 50%;
  border-style: solid pink;
}

.card {
  margin: 0 auto;
  width: 60%;
  max-width: 400px;
  max-height: 400px;
  background-color: rgba(0, 0, 0, 0.5) !important;
  margin-top: 10%;
}

.teste {
  background-color: transparent !important;
  margin-top: 10%;
  margin-left: 10%;
  width: 80%;
  color: white;
}

.btn-div {
  display: flex;
  justify-content: center;
  margin-bottom: 10%;
  margin-top: 10%;
}

.btn-login {
  color: aliceblue !important;
  height: 40px !important;
  width: 100px !important;
  border-radius: 12px !important;
  background-color: rgba(0, 0, 0, 0.5) !important;
}

.icon {
  margin-left: 4px;
}

.input-formatacao {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

@media (max-width: 1000px) {
  .card {
    width: 80%;
    margin-top: 40%;
  }

  .teste {
    margin-top: 10%;
    width: 80%;
  }

  .btn-div {
    margin-bottom: 5%;
    margin-top: 5%;
  }
  .fundo {
    background-position: left; /* Centralize o fundo */  }
}

</style>
