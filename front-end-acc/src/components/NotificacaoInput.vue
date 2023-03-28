<template>
  <div>
    <v-btn @click="openPopup" prepend-icon="mdi-bell" variant="text" color="white"></v-btn>
    <v-dialog v-model="showPopup" max-width="500">
      <v-card>
        <v-card-title>Notificações</v-card-title>
        <v-card-text>
          <v-list>
            <v-list-item v-for="notification in notifications" :key="notification.id">
              <v-list-item-title>{{ notification.message }}</v-list-item-title>
            </v-list-item>
          </v-list>
        </v-card-text>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      notifications: [
        { id: 1, message: "Notificação 1" },
        { id: 2, message: "Notificação 2" },
        { id: 3, message: "Notificação 3" },
      ],
      showPopup: false,
    };
  },
  methods: {
    openPopup() {
      this.showPopup = true;
    },
  },
};
</script>
