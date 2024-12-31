<template>
  <div id="app">
    <header>
      <NavBar />
    </header>
    <main>
      <router-view />
    </main>
    <div id="footer">
      <Footer />
    </div>
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

</style>
