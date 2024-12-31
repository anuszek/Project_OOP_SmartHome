<template>
  <APICall />
  <div id="app">
    <NavBar />
    <main>
      <router-view />
    </main>
    <Footer />
  </div>
</template>

<script setup>
import APICall from './components/APICall.vue';
</script>

<script>
import NavBar from './components/NavBar.vue';
import Footer from './components/Footer.vue';

export default {
  components: {
    NavBar,
    Footer,
  },
};
</script>

<style scoped>

</style>
