<template>
  <div>
    <!-- Badge de notificações com número de notificações atual -->
    <v-badge :content="numNotifications" color="error" floating> </v-badge>
    <!-- Botão para abrir o popup de notificações -->
    <v-btn
      class="notification-button"
      @click="openPopup"
      prepend-icon="mdi-bell"
      size="50"
      height="50"
      width="50"
      variant="text"
    ></v-btn>
    <!-- Popup de notificações -->
    <v-dialog v-model="showPopup" :max-width="isMobile ? 400 : 600">
      <v-card class="notification-card" style="max-height: 400px">
        <v-card-title class="card">Notification</v-card-title>
        <v-divider></v-divider>
        <v-card-text class="scroll-y">
          <v-list>
            <v-list-item
              style="size: 8px"
              v-for="(notification, index) in notifications"
              :key="index"
            >
              <v-list-item-title class="text-wrap"
                ><v-btn
                  class="fechar-popup"
                  icon
                  @click.stop="
                    notifications.splice(index, 1);
                    selectedNotification = notification;
                    numNotifications--;
                  "
                  ><v-icon class="iconclose">mdi-close</v-icon></v-btn
                >
                {{ notification.mensagem }}</v-list-item-title
              >
            </v-list-item>
          </v-list>
        </v-card-text>
      </v-card>
    </v-dialog>
  </div>
</template>

<!-- <v-list-item style="size:8px" v-for="(notification, index) in notifications" :key="index" @click="selectedNotification = notification; numNotifications--;"><v-list-item-title>{{ notification.mensagem }}</v-list-item-title></v-list-item> -->
<!-- <v-list-item-action><v-btn class="fechar-popup" icon @click.stop="notifications.splice(index, 1); selectedNotification = notification; numNotifications--;"><v-icon>mdi-close</v-icon></v-btn></v-list-item-action> -->
<script>
import axios from "axios";
import { Icon } from "@iconify/vue";

export default {
  data() {
    return {
      notifications: [],
      selectedNotification: null,
      showPopup: false,
      snackbar: false,
      idUsuario: "",
      text: "",
      numNotifications: 0,
    };
  },

  component: {
    Icon,
  },
  mounted() {
    this.inicializarDadosNotificacoes();
  },
  methods: {
    async inicializarDadosNotificacoes() {
      try {
        const idUsuario = sessionStorage.getItem("idUsuario");
        const response = await axios.get("notificar/" + idUsuario);
        const dados = response.data;
        this.notifications = dados;
        this.numNotifications = this.notifications.length;
        this.mensagem;
      } catch (error) {
        console.error("Erro ao buscar notificações", error);
        this.showSnackbar("Não foi possível buscar as notificações");
      }
    },

    mounted() {
      this.isMobile = window.innerWidth < 400; // define isMobile como verdadeiro se a largura da tela for menor que 600 pixels
    },
    // Abre o popup de notificações
    async openPopup() {
      await this.inicializarDadosNotificacoes();
      this.showPopup = true;
    },
    // Abre o snackbar com uma mensagem de exemplo
    openSnackbar() {
      this.showSnackbar("Snackbar aberto!");
    },
    // Exibe um snackbar com a mensagem especificada
    showSnackbar(message) {
      this.text = message;
      this.snackbar = true;
    },
  },
};
</script>

<style scoped>
.text-none {
  height: 100px;
  color: white;
  border-radius: 10rem;
}
.card {
  text-align: center;
  size: 30px;
  margin-top: 10px;
}
.notification-list {
  max-height: 350px;
  overflow-y: auto;
  overflow-x: auto;
}
.fechar-popup {
  max-width: 20px;
  max-height: 20px;
  color: white;
  background-color: #d2042d;
  /* position: absolute;
  top: 5px;
  right: 5px;
  margin-left: 500px;
  max-width: 20px;
  max-height: 20px;
  color: white;
  background-color: #D2042D; */
}

.iconclose {
  max-width: 5px;
  max-height: 5px;
}

notification-card {
  background-color: #fff;
  border-radius: 10px;
  transition: all 0.3s ease-out;
}

.notification-button {
  color: #fff;
  border-radius: 100%;
  font-size: 24.5px;
}
@media screen and (max-width: 600px) {
  .notification-card {
    width: 100%;
    height: 100%;
    margin: 0;
    max-lines: 2;
    border-radius: 0;
  }

  .fechar-popup {
    /* top: 10px;
    right: 10px; */
  }
}
</style>
