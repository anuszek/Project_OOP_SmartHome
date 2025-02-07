<template>
  <div>
    <h1>Smart Locks</h1>
    <span class="material-symbols-outlined" style="font-size: 100px;">door_front</span>
    <h4>Doors are {{ isLocked ? "LOCKED" : "UNLOCKED" }}</h4>
    <button @click="isLocked = !isLocked" :style="{ backgroundColor: isLocked ? 'green' : 'red' }" class="lock-button">
      {{ isLocked ? 'Unlock' : 'Lock' }}</button>
    <div class="confirm-button">
      <button @click="confirmChanges">Confirm</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    device: {
      type: Object,
      required: true
    }
  },
  mounted() {
    console.log(this.device);
  },
  data() {
    return {
      isLocked: this.device.locked
    };
  },
  methods: {
    confirmChanges() {
      this.$emit("update-device", {
        ...this.device,
        locked: this.isLocked,
      });
    }
  },
};
</script>

<style scoped>
  .confirm-button {
    margin: 20px;
  }
  .lock-button {
   border: none;
   border-radius: 3px;
   width: 100px;
  }
</style>