<template>
  <div :style="{ backgroundColor: color }" class="container">
    <h1 :style="{ color: complementaryColor }">Lights</h1>
    <h3 :style="{ color: complementaryColor }">Pick light color</h3>
    <input type="color" v-model="color" @input="updateColor" class="color-input">
    <div>
      <h4 :style="{ color: complementaryColor, fontWeight: bold }">{{ `selected color: ${color}` }}</h4>
      <div :style="{ backgroundColor: color, width: '100px', height: '100px' }"></div>
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
    height: 20vh;
    width: 20vh; 
    cursor: pointer;
  }

</style>
