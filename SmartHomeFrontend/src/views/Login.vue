<template>
  <div v-if="state.showLogin" class="overlay" @click.self="$emit('close-login')">
    <div class="login-popup">
      <button class="close-btn" @click="$emit('close-login')">
        <span class="material-symbols-outlined">close</span>
      </button>
      <h2>Log In</h2>
      <form @submit.prevent="handleLogin">
        <input v-model="username" type="text" placeholder="Username" required />
        <input v-model="password" type="password" placeholder="Password" required />
        <button type="submit">Login</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { inject } from 'vue';

export default {
  setup() {
    const state = inject('state');
    const toggleLogin = inject('toggleLogin');
    const logIn = inject('logIn');

    return {
      state,
      toggleLogin,
      logIn,
      username: '',
      password: '',
    };
  },
  methods: {
    async handleLogin() {
      try {
        const response = await axios.post('http://localhost:8080/api/login', {
          username: this.username,
          password: this.password,
        });

        if (response.data.authenticated) {
          this.toggleLogin();
          this.logIn(response.data);
        } else {
          alert('Login failed: ' + response.data.error);
        }
      } catch (error) {
        alert('Login failed, wrong username or password');       
        // console.error('Login error:', error);
      }
    },
  },
};
</script>

<style scoped>
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.login-popup {
  background: white;
  color: black;
  padding: 20px;
  border-radius: 5px;
  position: relative;
  width: 500px;
  height: 580px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  justify-content: center;
  justify-content: space-around;
  justify-content: space-evenly;
}

.close-btn {
  position: absolute;
  top: 5px;
  right: 5px;
  background: none;
  border: none;
  color: black;
}

form input {
  width: 80%;
  padding: 15px;
  margin-bottom: 30px;
  border-radius: 5px;
  font-size: x-large;
  box-sizing: border-box;
}

form button {
  width: 70%;
  padding: 10px;
  margin-top: 20px;
  background: #4CAF50;
  color: white;
  border: none;
  border-radius: 20px;
}
</style>