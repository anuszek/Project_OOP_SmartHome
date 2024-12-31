<template>
  <div id="app">
    <header class="sticky-header">
      <NavBar />
    </header>
    <main>
      <router-view />
    </main>
    <footer>
      <Footer />
    </footer>
  </div>
  <!-- <APICall /> -->
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
  .sticky-header {
    position: sticky;
    top: 0;
    width: 100%;
    z-index: 1000;
  }
</style>
