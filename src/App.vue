<script setup>
import { RouterView } from 'vue-router'
import Footer from './components/view/Footer.vue'
import Header from './components/view/Header.vue';
</script>

<template>
  <div class="wrap">
    <Header></Header>
    <RouterView class="main"/>
    <Footer class="footer"></Footer>
  </div>
</template>

<style scoped>
.main{
  flex: 1 1 auto;
  margin-top: 20px;
  margin-bottom: 20px;
}
.wrap{
  display: flex;
  flex-direction: column;
  min-height: 100%;
  width: 80%;
}
</style>
