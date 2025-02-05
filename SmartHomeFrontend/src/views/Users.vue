<template>
  <div>
    <div v-if="state.isLoggedIn">
      <div v-if="state.userName == 'AdamusLech'">
        <img src="../assets/adamus.png" alt="" style="width: 100px; height: 100px;">
      </div>
      <h1>Hello {{ state.userName }}</h1>
      <div v-if="state.isUserAdmin">
        <ul>
          users:
          <!-- <li v-for="user in users" :key="user.id">{{ user.name }}</li> -->
        </ul>
      </div>
      <button @click="logOut">Log Out</button>
    </div>
    <div v-else>
      <p>hello</p>
    </div>
  </div>
</template>

<script>
import { inject } from 'vue';

export default {
  setup() {
    const state = inject('state');
    const logOut = inject('logOut');

    return {
      state,
      logOut,
    };
  },
};
</script>