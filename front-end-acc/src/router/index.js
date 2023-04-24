// Composables
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    
    component: () => import('@/layouts/default/Default.vue'),
    children: [
      
      {
        path: 'home',
        name: 'TelaHome',
    
        component: () => import('@/views/TelaHome.vue'),
      },

      {
        path: '/',
        name: 'TelaLogin',
    
        component: () => import('@/views/TelaLogin.vue'),
      },
      
      {
        path: 'consulta',
        name: 'TelaConsulta',
        component: () => import("@/views/TelaConsulta.vue"),
      },

      {
        path: 'editor',
        name:  'TelaEditor',
        component: () => import("@/views/TelaEditor.vue"),
      }, 

      {
        path: 'administrador',
        name:  'Tela_Admin',
        component: () => import("@/views/Tela_Admin.vue"),
      },
      
    ],
  },
  
]


const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
