<template>
  <div>
    <v-badge :content ="50" color="error" floating >
    </v-badge>
      <v-btn  class="text-none" stacked 
      @click="openPopup" prepend-icon="mdi-bell" variant="text" >
      </v-btn>
    <!-- <v-btn class="text-none" stacked>
      <v-badge content="2" color="error">
        <v-icon>mdi-bell-outline</v-icon>
      </v-badge>
    </v-btn> -->

    <v-dialog v-model="showPopup" max-width="500">
      <v-card>
        <div class="text-center ma-2">
    <v-btn
      @click="snackbar = true"
    >
      Open Snackbar
    </v-btn>
    <v-snackbar
      v-model="snackbar"
    >
      {{ text }}

      <template v-slot:actions>
        <v-btn
          color="blue"
          variant="text"
          @click="snackbar = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </div>
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
      snackbar: false,
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

<style scoped>
.text-none{
  height: 40px;
  color: white;
  border-radius: 10rem;
  }
</style>
