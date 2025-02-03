<template>
  <div>
    <div v-if="state.isLoggedIn">
      <h1>Hello {{ state.userName }}</h1>
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