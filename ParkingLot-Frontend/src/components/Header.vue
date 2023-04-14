<template>
  <el-menu
      mode="horizontal"
      :ellipsis="false"
      @select="handleSelect"
  >
    <el-image class="image" fit="scale-down" :src="logo"></el-image>

    <el-menu-item v-for="tab in tabs" :index="tab.index">
      {{ tab.title }}
    </el-menu-item>

    <div class="flex-grow" />

    <el-menu-item v-if="!isLoggedIn" id="item_login" index="login">
      LOGIN
    </el-menu-item>

    <div v-if="isLoggedIn" id="loggedInOptions" style="display: flex; flex-direction: row;">
      <div id="greeting"></div>

      <el-menu-item v-if="isStaff" id="item_adminPage" index="adminPage">
        ADMIN
      </el-menu-item>

      <el-menu-item id="item_logout" index="logout">
        LOGOUT
      </el-menu-item>
    </div>

  </el-menu>
</template>

<script setup>
import logo from '../assets/PLS_logo.png'
import $ from 'jquery'
import router from '@/router'
import {inject} from "vue";

let axios = inject('axios')

function handleSelect(index) {
  // Login
  if (index === 'login') {
    router.push('/login');
    return;
  }

  // Logout
  else if (index === 'logout') {
    axios.post('api/auth/logout')
    .then(res => {
      console.log(res);
      // Clear session storage
      sessionStorage.clear();
      let allCookies = document.cookie.split(';');
      // The "expire" attribute of every cookie is
      // Set to "Thu, 01 Jan 1970 00:00:00 GMT"
      for (let i = 0; i < allCookies.length; i++)
        document.cookie = allCookies[i] + "=;expires="
            + new Date(0).toUTCString();

      // Go to home page or refresh if already on home page
      if (router.currentRoute['value'].path !== '/') {
        router.push('/');
      } else {
        router.go(0);
      }
    })
    .catch(err => {
      console.log(err);
    });
    return;
  }

  // Admin Page
  else if (index === 'adminPage') {
    router.push('/admin');
    return;
  }

  // Set all tab bodies to inactive
  $('.tabBody').removeAttr('active');

  // Set the selected tab body to active
  $('#page_' + index).attr('active', 'true');
}
</script>

<script>
import {inject} from "vue";
import axios from "axios";

export default {
  name: 'Header',
  props: {
    tabs: {
      type: Array,
      default: () => []
    },
    isLoggedIn: {
      type: Boolean,
      default: () => false
    },
    isStaff: {
      type: Boolean,
      default: () => false
    }
  },
  mounted() {
    // Logo to home page
    $('.image').click(() => {
      router.push('/');
    });
  }
}
</script>

<style scoped>
.image {
  height: 58px;
  margin: 0 20px;
  cursor: pointer;
}

.flex-grow {
  flex-grow: 1;
}
</style>
