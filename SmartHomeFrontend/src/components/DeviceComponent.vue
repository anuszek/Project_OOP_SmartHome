<template>
  <div class="device-component">
    <h2>{{ device.name }}</h2>
    <p>Status: {{ device.status }}</p>
    <button @click="toggleDevice">Toggle Device</button>
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
      this.$emit('toggle-device', this.device.id);
    }
  }
}
</script>

<style scoped>
.device-component {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 16px;
  margin: 16px;
  text-align: center;
}
</style>