<template>
  <div>
    <!-- Badge de notificações com número de notificações atual -->
    <v-badge :content="numNotifications" color="error" floating>
    </v-badge>

    <!-- Botão para abrir o popup de notificações -->
    <v-btn class="text-none" stacked @click="openPopup" prepend-icon="mdi-bell" variant="text">
    </v-btn>

    <!-- Popup de notificações -->
    <v-dialog v-model="showPopup" max-width="600">
      <v-card>
        <div class="text-center ma-2">
          <!-- Botão para abrir o snackbar -->
          <v-btn @click="openSnackbar">Open Snackbar</v-btn>

          <!-- Snackbar para exibir mensagens ao usuário -->
          <v-snackbar v-model="snackbar" >
            {{ text }}

            <!-- Botão para fechar o snackbar -->
            <template v-slot:actions>
              <v-btn color="blue" variant="text" @click="snackbar = false">Close</v-btn>
            </template>
          </v-snackbar>
        </div>
        <v-card-title>Notificações</v-card-title>
        <v-card-text>
          <v-list>
            <v-list-item v-for="(notification, index) in notifications" :key="index" @click="selectedNotification = notification; numNotifications--;">
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
</style>
