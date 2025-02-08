<template>
  <div class="heating-control-panel">
    <h1>Heating Control</h1>
    <span class="material-symbols-outlined" style="font-size: 100px;">mode_heat_cool</span>
    <div class="heating-status">
      <h3>Status: {{ device.temperature > temperature ? "COOLING" : "HEATING" }}</h3>
    </div>

    <div class="container">
    <div class="current-temperature">
      <h3>Current Temperature</h3>
      <p class="material-symbols-outlined">{{ device.temperature }}°C</p>
    </div>
    
    <div class="temperature-setpoint">
      <h3>Set Target Temperature</h3>
      <input type="range" v-model="temperature" min="15" max="28" step="1" class="slider">
      <p>Target Temperature: {{ temperature }}°C</p>
      <button @click="confirmChanges">Confirm</button>
    </div>
    
    <!-- <div class="heating-mode">
      <h3>Mode</h3>
      <select v-model="mode">
        <option value="auto">Auto</option>
        <option value="manual">Manual</option>
        <option value="off">Off</option>
      </select>
    </div> -->
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