<template>
  <div>
    <h1>Smart Blinds</h1>
    <div>
      <span class="material-symbols-outlined" style="font-size: 100px;">blinds</span>
    </div>
    <p>Blinds are {{ closingPercentage }}% closed</p>
    <input type="range" class="slider" v-model="closingPercentage" min="0" max="100" />
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
  data() {
    return {
      closingPercentage: this.device.position
    };
  },
  methods: {
    confirmChanges() {
      this.$emit("update-device", {
        ...this.device,
        position: parseInt(this.closingPercentage),
      });
    }
  },
};
</script>

<style scoped>
  .confirm-button {
    margin: 20px;
  }
</style>