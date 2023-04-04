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

    <el-menu-item id="connection" index="connection">
      LOGIN
    </el-menu-item>
  </el-menu>
</template>

<script setup>
import logo from '../assets/PLS_logo.png'
import {ref} from 'vue'
import $ from 'jquery'

function handleSelect(index) {
  // Login
  if (index === 'connection') {
    window.location.href = '/login'
    return
  }

  // Set all tab bodies to inactive
  $('.tabBody').removeAttr('active');

  // Set the selected tab body to active
  $('#page_' + index).attr('active', 'true');
}
</script>

<script>
export default {
  name: 'Header',
  props: {
    tabs: {
      type: Array,
      default: () => []
    }
  }
}
</script>

<style scoped>
.image {
  width: 58px;
  height: 58px;
  margin: 0 20px;
}

.flex-grow {
  flex-grow: 1;
}
</style>
