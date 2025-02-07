<template>
  <div class="heating-control-panel">
    <h1>Oven</h1>
    <span class="material-symbols-outlined" style="font-size: 100px;">oven_gen</span>
    <div class="heating-status">
      <h3>Status: {{ device.temperature < temperature ? "ON" : "OFF" }}</h3>
    </div>

    <div class="container">
    <div class="current-temperature">
      <h3>Current Temperature</h3>
      <p class="material-symbols-outlined">{{ device.temperature }}°C</p>
    </div>
    
    <div class="temperature-setpoint">
      <h3>Set Target Temperature</h3>
      <input type="range" v-model="temperature" min="0" max="230" step="1" class="slider">
      <p>Target Temperature: {{ temperature }}°C</p>
      <button @click="confirmChanges">Confirm</button>
    </div>
 
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
      temperature: this.device.temperature,
      mode: "auto"
    };
  },
  methods: {
    confirmChanges() {
      this.$emit("update-device", {
        ...this.device,
        temperature: parseInt(this.temperature),
      });
    }
  },
};
</script>

<style scoped>
.heating-control-panel {
  border: none;
  /* padding: 20px;
  border: 1px solid #ccc;
  border-radius: 10px;
  width: 60vw;
  height: 80vh;
  margin: auto; */
}

.container {
  display: flex;
  justify-content: center;
  gap: 50px;
}

.container > * {
  flex: 0.2;
  border: 1px solid #ccc;
  border-radius: 10px;
}

.current-temperature, .temperature-setpoint, .heating-mode, .heating-status, .temperature-history {
  margin-bottom: 20px;
  padding: 5px;
}

</style>