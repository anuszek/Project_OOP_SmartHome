<template>
  <div class="device-component">
    <h2>{{ device.name }}</h2>
    <p>{{ device.description }}</p>
    <div v-if="device.hasOwnProperty('online')">
     <p>Device status: {{ device.online ? "Online" : "Offline" }}</p>
      <button @click="toggleDevice">Toggle Device</button>
    </div>
    <p v-else></p>
  </div>
</template>

<script>
export default {
  name: 'DeviceComponent',
  props: {
    device: {
      type: Object,
      required: true
    }
  },
  methods: {
    toggleDevice() {
      this.$emit('toggle-device', this.device.deviceId);    
    }
  }
}
</script>

<style scoped>
.device-component {
  border-radius: 5px;
  padding: 16px;
  margin: 16px;
  text-align: center;
  background-color: #ededed;
  color: #000;
  box-shadow: 2px 2px 7px #4CAF50;
}
</style>