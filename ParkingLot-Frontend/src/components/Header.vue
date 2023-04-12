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

function handleSelect(index) {
  // Login
  if (index === 'login') {
    window.location.href = '/login';
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
    // Method for displaying not logged in
    function displayNotLoggedIn() {
      $("#item_login").show();
      $("#loggedInOptions").hide();
    }

    // Method for displaying logged in
    function displayLoggedIn() {
      $("#item_login").hide();
      $("#loggedInOptions").show();
    }

    // Check if the user is logged in
    axios.get('http://127.0.0.1:8080/api/auth/isLoggedIn', {
      withCredentials: true,
      headers: {
        "Access-Control-Allow-Origin": 'localhost:8080'
      }
    })
    .then(res => {
      console.log(document.cookie)
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
    })
    // $.ajax({
    //   url: 'http://127.0.0.1:8080/api/auth/isLoggedIn',
    //   type: 'GET',
    //   success: function(data) {
    //     console.log(data);
    //     var loggedIn = data === 'true';
    //     console.log(loggedIn);
    //     if (loggedIn) {
    //       displayLoggedIn();
    //     } else {
    //       displayNotLoggedIn();
    //     }
    //   },
    //   error: function(data) {
    //     console.log(data);
    //   }
    // });
  }
}
</script>

<style scoped>
.image {
  height: 58px;
  margin: 0 20px;
}

.flex-grow {
  flex-grow: 1;
}
</style>
