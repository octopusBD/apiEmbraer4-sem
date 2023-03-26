// Components
import App from './App.vue'
import axios from 'axios'
// Composables
import { createApp } from 'vue'

axios.defaults.baseURL = 'http://localhost:8080/';
// Plugins
import { registerPlugins } from '@/plugins'

const app = createApp(App)

registerPlugins(app)

app.mount('#app')
