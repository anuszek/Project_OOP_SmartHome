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
    <Register v-if="state.showRegister" @close-register="toggleRegister" />
    <Login v-if="state.showLogin" @close-login="toggleLogin" @login-success="logIn" />
  </div>
</template>

<script setup>
import { reactive, provide } from 'vue';
import NavBar from './components/NavBar.vue';
import Footer from './components/Footer.vue';
import Login from './views/Login.vue';
import Register from './views/Register.vue';

const state = reactive({
  showLogin: false,
  showRegister: false,
  isLoggedIn: false,
  userName: '',
  isUserAdmin: false,
});

function toggleLogin() {
  state.showLogin = !state.showLogin;
}

function toggleRegister() {
  state.showRegister = !state.showRegister;
}

function logIn(data) {
  state.isLoggedIn = true;
  state.userName = data.userData;
  state.isUserAdmin = data.isAdmin;
}

function logOut() {
  state.isLoggedIn = false;
  state.userName = '';
  state.isUserAdmin = false;
}

provide('state', state);
provide('toggleLogin', toggleLogin);
provide('toggleRegister', toggleRegister);
provide('logIn', logIn);
provide('logOut', logOut);
</script>

<style scoped>
  .sticky-header {
    position: sticky;
    top: 0;
    width: 100%;
    z-index: 1000;
  }
</style>
