<template>
  <div>
    <h2>Heating Control</h2>
    <knob-control v-model="temperature" :min="0" :max="30" :step="1"></knob-control>

    <p>Current Temperature: {{ temperature }}°C</p>
  </div>
</template>

<script>
import { KnobControl } from 'vue-knob-control';
console.log(KnobControl);


export default {
  components: {
    KnobControl
  },
  data() {
    return {
      temperature: 20 // Default temperature
    };
  }
};
</script>