<template>
  <div :style="{ backgroundColor: color, color: complementaryColor }" class="container">
    <h1>Lights</h1>
    <span class="material-symbols-outlined" style="font-size: 80px;">lightbulb_2</span>
    <h3>Pick light color</h3>
    <input type="color" v-model="color" @input="updateColor" class="color-input">
    <h4>{{ `selected color: ${color}` }}</h4>
    <label>Brightness: {{ brightness }}</label>
    <input type="range" class="slider" v-model="brightness"  min="0" max="100" > 
    <div class="confirm-button">
      <button @click="confirmChanges">Confirm</button>
    </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue';

export default {
  setup() {
    const color = ref('#ffffff');
    const updateColor = (event) => {
      color.value = event.target.value;
    };

    const complementaryColor = computed(() => {
      const hex = color.value.replace('#', '');
      const r = (255 - parseInt(hex.substring(0, 2), 16)).toString(16).padStart(2, '0');
      const g = (255 - parseInt(hex.substring(2, 4), 16)).toString(16).padStart(2, '0');
      const b = (255 - parseInt(hex.substring(4, 6), 16)).toString(16).padStart(2, '0');
      return `#${r}${g}${b}`;
    });

    return {
      color,
      updateColor,
      complementaryColor,
    };
  },
  props: {
    device: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      brightness: this.device.lightLevel,
      color: this.device.lightColor
    };
  },
  methods: {
    confirmChanges() {
      this.$emit("update-device", {
        ...this.device,
        lightLevel: this.brightness,
        lightColor: this.color,
      });
    }
  },
};
</script>

<style scoped>
  .container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 90%;
    width: 100%;
    margin-bottom: 10px;
    border-radius: 5px;
  }
  .color-input {
    height: 8vh;
    width: 32vh; 
    cursor: pointer;
  }
  .confirm-button {
    margin-top: 20px;
  }
</style>
