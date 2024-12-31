<template>
  <div id="app">
    <header class="sticky-header">
      <NavBar :showLogin="showLogin" @toggle-login="toggleLogin" />
    </header>
    <main>
      <router-view />
    </main>
    <footer>
      <Footer />
    </footer>
    <Login v-if="showLogin" @close-login="toggleLogin" />
  </div>
  <!-- <APICall /> -->
</template>

<script setup>
import APICall from './components/APICall.vue';
</script>

<script>
import NavBar from './components/NavBar.vue';
import Footer from './components/Footer.vue';
import Login from './views/Login.vue';

export default {
  components: {
    NavBar,
    Footer,
    Login,
  },
  data() {
    return {
      showLogin: false,
    };
  },
  methods: {
    toggleLogin() {
      this.showLogin = !this.showLogin;
    },
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
