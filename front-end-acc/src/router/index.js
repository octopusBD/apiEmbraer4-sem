// Composables
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',

    
    component: () => import('@/layouts/default/Default.vue'),
    children: [
      {
        path: '/',
        name: 'Home',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "home" */ '@/views/TelaHome.vue'),
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
      
    ],
  },
  
]


const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
