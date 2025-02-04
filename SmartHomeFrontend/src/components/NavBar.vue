<template>
  <nav>
    <button @click="navigateTo('/')" class="home">
      <span class="material-symbols-outlined">home</span>
    </button>
    <ul>
      <li @click="navigateTo('/dashboard')">
        <button :class="{ active: isActive('/dashboard') }" class="navigation">Dashboard</button>
      </li>
      <li @click="navigateTo('/devices')">
        <button :class="{ active: isActive('/devices') }" class="navigation">Devices</button>
      </li>
      <li @click="navigateTo('/users')">
        <button :class="{ active: isActive('/users') }" class="navigation">Users</button>
      </li>
      <li @click="navigateTo('/settings')">
        <button :class="{ active: isActive('/settings') }" class="navigation">Settings</button>
      </li>
      <li class="separator"></li>
      <li class="sign-in">
        <template v-if="!state.isLoggedIn">
          <button id="sign_button" @click="toggleLogin">Log in</button>
          <button id="register_button" @click="toggleRegister">Register</button>
        </template>
        <template v-else>
          <span class="material-symbols-outlined nav-icon">account_circle</span>
        </template>
      </li>
    </ul>
  </nav>
</template>

<script>
import { inject, onMounted, onBeforeUnmount } from 'vue';

export default {
  setup() {
    const state = inject('state');
    const toggleLogin = inject('toggleLogin');
    const toggleRegister = inject('toggleRegister');

    const setButtonWidths = () => {
      const buttons = document.querySelectorAll('button');
      let maxWidth = 0;
      buttons.forEach(button => {
        if (button.id !== 'sign_button' && button.id !== 'register_button') {
          button.style.width = 'auto'; // Reset width to auto to get natural width
          const buttonWidth = button.offsetWidth;
          if (buttonWidth > maxWidth) {
            maxWidth = buttonWidth;
          }
        }
      });
      buttons.forEach(button => {
        if (button.id !== 'sign_button' && button.id !== 'register_button') {
          button.style.width = `${maxWidth}px`;
        }
      });
    };

    onMounted(() => {
      setButtonWidths();
      window.addEventListener('resize', setButtonWidths);
    });

    onBeforeUnmount(() => {
      window.removeEventListener('resize', setButtonWidths);
    });

    return {
      state,
      toggleLogin,
      toggleRegister,
    };
  },
  computed: {
    isActive() {
      return (path) => this.$route.path === path;
    },
  },
  methods: {
    navigateTo(path) {
      this.$router.push(path);
    },    
  },
};
</script>

<style scoped>
  .home {
    color: white;
    display: flex;  
    place-content: center;
  }

  .home span {
    font-size: 3rem;
  }  

  .sing-in {
    /* background-color: aquamarine; */
    display: flex;
    align-items: center;
  }

  .nav-icon {
    font-size: 3rem;
    fill: white;
    margin-left: 1rem;
    user-select: none;
  }

  .navigation {
    /* margin-left: 1rem; */
    margin-right: 1rem;
  }

  #sign_button {
    background-color: #4CAF50;
    color: white;
    border-radius: 5px;
    margin-left: 1rem;
   }

  #register_button {
    background-color: #242424;
    color: white;
    border-radius: 5px;
    margin-left: 1rem;  
  }

  .active {
    background-color: white;
    color: #000;
  }

  nav {
    color: white;
    padding: 10px;
    border-radius: 5px;
  }

  ul {
    list-style-type: none;
    margin-right: 20px;
    padding: 0;
    display: flex;
  }

  .separator::before {
    content: '';
    display: inline-block;
    width: 1px;
    height: 100%;
    background-color: #ccc;
    margin-left: 10px;
    margin-right: 10px;
  }
</style>