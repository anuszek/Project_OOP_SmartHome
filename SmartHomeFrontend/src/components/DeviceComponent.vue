<template>
  <div class="device-component">
    <h2>{{ device.name }}</h2>
    <p>{{ device.description }}</p>
    <div class="device-controls">
      <div v-if="device.hasOwnProperty('online')" class="device-status">
        <p>Device status:</p>
        <p v-if="device.online" style="color: green;">Online</p>
        <p v-else style="color: red;">Offline</p>      
      </div>
      <button @click="handleDevice">Properties</button>   
      <button v-if="state.isUserAdmin" @click="toggleDevice">Toggle Device</button>
      <button v-if="state.isUserAdmin"  @click="deleteDevice" >Delete Device</button>
    </div>
  </div>
</template>

<script>
import { inject } from 'vue';

export default {
  setup() {
    const state = inject('state');
    
    return {
      state,
    };
  },

  name: 'DeviceComponent',
  props: {
    device: {
      type: Object,
      required: true
    }
  },
  methods: {
    toggleDevice() {
      this.$emit('toggle-device', this.device);    
    },
    deleteDevice() {
      this.$emit('delete-device', this.device);
    },
    handleDevice(){
      if(!this.device.online){
       alert("Device is offline");
       return;
      }
      this.$emit('display-device', this.device);
    },  
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
  box-shadow: 2px 2px 7px #646cff;
}

.device-status {
  font-weight: bold;
}

.device-controls {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
</style>