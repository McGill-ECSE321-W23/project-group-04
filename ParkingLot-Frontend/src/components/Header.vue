<template>
  <el-menu
      :default-active="activeIndex"
      mode="horizontal"
      :ellipsis="false"
      @select="handleSelect"
  >
    <el-image class="image" fit="scale-down" :src="logo"></el-image>

    <el-menu-item v-for="tab in tabs" :index="tab.index">
      {{ tab.title }}
    </el-menu-item>

    <div class="flex-grow" />

    <el-menu-item :index="'login'">
      LOGIN
    </el-menu-item>
  </el-menu>
</template>

<script setup>
import logo from '../assets/PLS_logo.png'
import {ref} from 'vue'

const activeIndex = ref('0');
var tabCount = 0;

function handleSelect(index) {
  // Set all tab bodies to inactive
  $('.tabBody').removeAttr('active');

  // Set the selected tab body to active
  $('#page_' + index).attr('active', 'true');
}
</script>

<script>
import $ from 'jquery'

export default {
  name: 'Header',
  props: {
    tabs: {
      type: Array,
      required: true
    }
  }
}
</script>

<style scoped>
.image {
  width: 56px;
  height: 56px;
  margin: 0 20px;
}

.flex-grow {
  flex-grow: 1;
}
</style>
