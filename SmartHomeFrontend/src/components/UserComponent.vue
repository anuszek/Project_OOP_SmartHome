<template>
  <div class="user-component">
    <p>{{ user.name }}</p>
    <div class="user-controls">
      <button @click="removeUser">Remove</button>
      <!-- <button @click="grantAdmin">Grant Admin</button> -->
    </div>
  </div>
</template>

<script>

export default {
  name: 'UserComponent',
  props: {
    user: {
      type: Object,
      required: true
    }
  },
  methods: {
    removeUser() {     
      this.$emit('remove-user', this.user.name);
    },
    grantAdmin() {
      this.$emit('grant-admin', this.user.name);
    }
  }
}

</script>

<style scoped>
.user-component {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-radius: 5px;
  padding: 16px;
  margin: 16px;
  text-align: center;
  background-color: #ededed;
  color: #000;
  box-shadow: 2px 2px 7px #646cff;
}

.user-controls {
  display: flex;
  gap: 10px;
}
</style>