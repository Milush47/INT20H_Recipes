<template>
  <Header/>
  <RouterView/>
  <Footer/>
</template>

<script>
import { RouterView } from 'vue-router'
import Footer from './components/view/Footer.vue'
import Header from './components/view/Header.vue';

export default {
  name: "App",
  components: {
    Header, 
    Footer
  }

}
</script>

