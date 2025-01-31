<template>
  <div id="app">
    <header class="sticky-header">
      <NavBar :showLogin="showLogin" @toggle-login="toggleLogin" 
              :showRegister="showRegister" @toggle-register="toggleRegister"
      />
    </header>
    <main>
      <router-view />
    </main>
    <footer>
      <Footer />
    </footer>
    <Register v-if="showRegister" @close-register="toggleRegister" />
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
import Register from './views/Register.vue';

export default {
  components: {
    NavBar,
    Footer,
    Login,
    Register,
  },
  data() {
    return {
      showLogin: false,
      showRegister: false,
    };
  },
  methods: {
    toggleLogin() {
      this.showLogin = !this.showLogin;
    },
    toggleRegister() {
      this.showRegister = !this.showRegister;
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
