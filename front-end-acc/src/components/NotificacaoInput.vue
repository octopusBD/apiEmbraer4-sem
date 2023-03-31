<template>
  <div>
    <!-- Badge de notificações com número de notificações atual -->
    <v-badge :content="numNotifications" color="error" floating>
    </v-badge>

    <!-- Botão para abrir o popup de notificações -->
    <v-btn class="notification-button" @click="openPopup" prepend-icon="mdi-bell" variant="text"></v-btn>
    <!-- Popup de notificações -->
   <v-dialog v-model="showPopup" max-width="600" >
    <v-card class="notification-card">
        <!-- <div class="text-center ma-2">
          <v-btn @click="openSnackbar">Open Snackbar</v-btn>

          <v-snackbar v-model="snackbar" >
            {{ text }}

            <template v-slot:actions>
              <v-btn color="blue" variant="text" @click="snackbar = false">Close</v-btn>
            </template>
          </v-snackbar>
        </div> -->
        <v-card-title style="text-align: center; size: 30px; margin-top: 10px;">Notificações</v-card-title>
        <v-divider></v-divider>
        <v-card-text>
          <v-list>
            <v-list-item v-for="(notification, index) in notifications" :key="index" @click="selectedNotification = notification; numNotifications--;">
              <v-list-item-action>
                <v-btn icon @click.stop="notifications.splice(index, 1) ,selectedNotification = notification; numNotifications--;">
                  <v-icon>mdi-close</v-icon>
                </v-btn>
              </v-list-item-action>
              <v-list-item-title>{{ notification.mensagem }}</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-card-text>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      notifications: [],
      selectedNotification: null,
      showPopup: false,
      snackbar: false,
      text: '',
      numNotifications: 0
    }
  },
  mounted() {
    this.inicializarDadosNotificacoes();
  },
  methods: {
    async inicializarDadosNotificacoes() {
      try {
        const response = await axios.get('notificar/2');

        const dados = response.data;
        this.notifications = dados;
        this.numNotifications = this.notifications.length;
        this.mensagem ;
      } catch (error) {
        console.error('Erro ao buscar notificações', error);
        this.showSnackbar('Não foi possível buscar as notificações');
      }
    },

    // Abre o popup de notificações
    async openPopup() {
      await this.inicializarDadosNotificacoes();
      this.showPopup = true;
      
    },

    // Abre o snackbar com uma mensagem de exemplo
    openSnackbar() {
      this.showSnackbar('Snackbar aberto!');
    },

    // Exibe um snackbar com a mensagem especificada
    showSnackbar(message) {
      this.text = message;
      this.snackbar = true;
    },
  }
}
</script>

<style scoped>
.text-none{
  height: 100px;
  color: white;
  border-radius: 10rem;
}
notification-card {
  background-color: #fff;
  border-radius: 10px;
  transition: all 0.3s ease-out;
}

.notification-button {
  color: #fff;
  border-radius: 100%;
  height: 30px;
  width: 30px;
  font-size: 30px;
}
</style>
