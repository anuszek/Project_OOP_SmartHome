<template>
  <div v-if="showLogin" class="overlay" @click.self="$emit('close-login')">
    <div class="login-popup">
      <button class="close-btn" @click="$emit('close-login')">
        <span class="material-symbols-outlined">close</span>
      </button>
      <h2>Log In</h2>
      <form @submit.prevent="handleLogin">
        <div>
            <input type="text" placeholder="Username" id="username" v-model="username" required>
        </div>
        <div>
          <input type="password" placeholder="Password" id="password" v-model="password" required>
        </div>
        <button type="submit">Login</button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      showLogin: true,
      username: '',
      password: '',
      data: {},
    };
  },
  methods: {
    async handleLogin() {
      await axios.post('http://localhost:8080/api/login', {
        username: this.username,
        password: this.password
      });
      
      await axios.get('http://localhost:8080/api/login')
        .then((response) => {
          this.data = response.data;
        })
        .catch((error) => {
          console.log("Error fetching data: ", error);
        });

      if (this.data.authenticated) {
        this.$emit('close-login');
      } else {
        alert('Invalid username or password');
      }
    }
  }
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