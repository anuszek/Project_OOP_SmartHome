<template>
  <div v-if="state.showRegister" class="register-overlay" @click.self="$emit('close-register')">
    <div class="register-popup">
      <button class="close-btn" @click="$emit('close-register')">
        <span class="material-symbols-outlined">close</span>
      </button>
      <h2>Register</h2>
      <form @submit.prevent="handleRegister">
        <div>
            <input type="text" placeholder="Username" id="username" v-model="username" required>
        </div>
        <div>
          <input type="password" placeholder="Password" id="password" v-model="password" required>
        </div>
        <button type="submit">Register</button>
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
    const toggleRegister = inject('toggleRegister');
    const logIn = inject('logIn');

    return {
      state,
      toggleRegister,
      logIn,
      username: '',
      password: '',
    };
  },

  methods: {
    async handleRegister() {
      try {
        const response = await axios.post('http://localhost:8080/api/register', {
          username: this.username,
          password: this.password,
        });
        
        if (response.data.authenticated) {
          this.toggleRegister();
          this.logIn(response.data);
        }
        else {
          alert('User already exists.');
        }
      } catch (error) {
        console.log("Error creating user: ", error);
        alert('Creating user failed. Please try again later.');
      }
    }
  }
};
</script>

<style scoped>
.register-overlay {
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

.register-popup {
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

form div {
  margin-bottom: 30px;
}

form input {
  width: 80%;
  padding: 15px;
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