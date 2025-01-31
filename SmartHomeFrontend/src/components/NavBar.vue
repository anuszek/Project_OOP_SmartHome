<template>
  <nav>
    <button  @click="navigateTo('/')" class="home">
      <span class="material-symbols-outlined">home</span>
    </button>
    <ul>
      <li @click="navigateTo('/dashboard')"><button :class="{ active: isActive('/dashboard') }" class="navigation">Dashboard</button></li>
      <li @click="navigateTo('/devices')"><button :class="{ active: isActive('/devices') }" class="navigation">Devices</button></li>
      <li @click="navigateTo('/users')"><button :class="{ active: isActive('/users') }" class="navigation">Users</button></li>
      <li @click="navigateTo('/settings')"><button :class="{ active: isActive('/settings') }" class="navigation">Settings</button></li>
      <li class="separator"></li>
      <li @click="$emit('toggle-login')"><button id="sign_button">Log in</button></li>
      <li @click="$emit('toggle-register')"><button id="register_button">Register</button></li>
    </ul>
  </nav>
</template>

<script>
  export default {
    props: {
      showLogin: Boolean,
      showRegister: Boolean,
    },
    computed: {
      isActive() {
        return (path) => this.$route.path === path;
      }
    },
    methods: {
      navigateTo(path) {
        this.$router.push(path);
      },
      setButtonWidths() {
        const buttons = this.$el.querySelectorAll('button');
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
      },
    },
    mounted() {
      this.setButtonWidths();
      window.addEventListener('resize', this.setButtonWidths);
    },
    beforeDestroy() {
      window.removeEventListener('resize', this.setButtonWidths);
    }
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

  .nav-icon {
    width: 40px;
    height: 40px;
    fill: white;
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