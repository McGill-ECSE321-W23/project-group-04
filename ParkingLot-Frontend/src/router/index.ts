import {createRouter, createWebHistory} from 'vue-router'
import PublicPage from '@/pages/PublicPage.vue'
import LoginPage from '@/pages/LoginPage.vue'
import AdminPage from '@/pages/AdminPage.vue'
import StaffSchedule from '@/pages/tabs/StaffSchedule.vue'
import BookReservation from  '@/pages/tabs/BookReservation.vue'
import AdminUsers from  '@/pages/tabs/AdminUsers.vue'

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
    {
      path: '/schedule',
      name: "StaffSchedule",
      component: StaffSchedule
    },
    {
      path: '/reservation',
      name: "BookReservation",
      component: BookReservation
    },
    {
      path: '/users',
      name: "AdminUsers",
      component: AdminUsers
    }
  ]
})

export default router
