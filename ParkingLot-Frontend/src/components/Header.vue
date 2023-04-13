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

    <el-menu-item id="item_login" index="login">
      LOGIN
    </el-menu-item>

    <div id="loggedInOptions" style="display: flex; flex-direction: row;">
      <div id="greeting"></div>

      <el-menu-item id="item_adminPage" index="adminPage">
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

function handleSelect(index) {
  // Login
  if (index === 'login') {
    router.push('/login');
    return;
  }

  // Logout
  else if (index === 'logout') {
    axios.post('http://localhost:8080/api/auth/logout', {}, {
      withCredentials: true,
      headers: {
        "Access-Control-Allow-Origin": 'localhost:8080'
      }
    })
    .then(res => {
      console.log(res);
      // Clear session storage
      sessionStorage.clear();
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
import axios from "axios";

export default {
  name: 'Header',
  props: {
    tabs: {
      type: Array,
      default: () => []
    }
  },
  mounted() {
    // Logo to home page
    $('.image').click(() => {
      router.push('/');
    });

    // Method for displaying not logged in
    function displayNotLoggedIn() {
      $("#item_login").show();
      $("#greeting").hide();
      $("#item_adminPage").hide();
      $("#item_logout").hide();
    }

    // Method for displaying logged in
    function displayLoggedIn() {
      $("#item_login").hide();
      $("#greeting").show();
      $("#item_logout").show();
      axios.get('http://localhost:8080/api/auth/isStaff', {
        withCredentials: true,
        headers: {
          "Access-Control-Allow-Origin": 'localhost:8080'
        }
      })
      .then(res => {
        console.log(res);
        if (res.data === true) {
          $("#item_adminPage").show();
        } else {
          $("#item_adminPage").hide();
        }
      })
      .catch(err => {
        console.log(err);
      });
    }

    // Check if the user is logged in
    axios.get('http://localhost:8080/api/auth/isLoggedIn', {
      withCredentials: true,
      headers: {
        "Access-Control-Allow-Origin": 'localhost:8080'
      }
    })
    .then(res => {
      console.log(res);
      var loggedIn = res.data === true;
      console.log(loggedIn);
      if (loggedIn) {
        displayLoggedIn();
      } else {
        displayNotLoggedIn();
      }
    })
    .catch(err => {
      console.log(err);
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
