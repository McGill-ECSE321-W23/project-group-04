<template>
      <el-menu
          :default-active="activeIndex"
          mode="horizontal"
          :ellipsis="false"
          @select="handleSelect"
          :router="true"
      >
        <el-menu-item>
          <el-image class="image" fit="scale-down" :src="logo"></el-image>
        </el-menu-item>
        <el-menu-item v-for="tab in tabs" :index="tab.id">
          {{ tab.title }}
        </el-menu-item>

        <div class="flex-grow" />

        <el-menu-item :index="'login'">
          LOGIN
        </el-menu-item>
      </el-menu>
</template>

<script setup>
import {ref} from 'vue'
import logo from '@/assets/PLS_logo.png'

const tabs = [
  { title: 'Home', id: "/" },
  { title: 'Services', id: "services" },
  { title: 'My Account', id: "/admin" }
]
const activeIndex = ref('home')

function handleSelect(key, value) {
  console.log(key, value)
}

</script>

<style scoped>
  .image {
    width: 56px;
    height: 56px
  }

  .flex-grow {
    flex-grow: 1;
  }
</style>
