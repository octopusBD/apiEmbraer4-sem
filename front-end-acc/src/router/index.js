// Composables
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    
    component: () => import('@/layouts/default/Default.vue'),
    children: [
      {
        path: '/',
        name: 'TelaHome',
    
        component: () => import('@/views/TelaHome.vue'),
      },
      {
        path: '/consultor',
        name: 'TelaConsulta',
        component: () => import("@/views/TelaConsulta.vue"),
      },

      {
        path: '/editor',
        name:  'TelaEditor',
        component: () => import("@/views/TelaEditor.vue"),
      },

      {
        path: '/administrador',
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
