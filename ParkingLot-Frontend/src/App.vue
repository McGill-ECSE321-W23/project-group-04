<template>
  <div>
    <custom-header></custom-header>
    <router-view></router-view>
  </div>
</template>

<script>
import CustomHeader from "@/components/Header.vue";

export default {
  name: 'app',
  components: {CustomHeader}
}
</script>

<style scoped>
#body {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
</style>