<template>
  <div>
    <h1>Rumba</h1>
    <span class="material-symbols-outlined" style="font-size: 100px;">vacuum</span>
    <p>For Rumba control, please use the official mobile app for your device.</p>
    <div :style="{ height: '20px' }"></div>   
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
  
    };
  },
  methods: {
  
  },
};
</script>
