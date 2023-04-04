import {createRouter, createWebHistory} from 'vue-router'
import PublicPage from '@/pages/PublicPage.vue'
import LoginPage from '@/pages/LoginPage.vue'
import AdminPage from '@/pages/AdminPage.vue'
// TODO: This is a temporary import for testing purposes
import StaffSchedule from '@/pages/tabs/StaffSchedule.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: "PublicPage",
      component: PublicPage
    },
    {
      path: '/login',
      name: "LoginPage",
      component: LoginPage
    },
    {
      path: '/admin',
      name: "AdminPage",
      component: AdminPage
    },
    { // TODO: This is a temporary route for testing purposes
      path: '/schedule',
      name: "StaffSchedule",
      component: StaffSchedule
    },
  ]
})

export default router
