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
      <div>
        <button @click="toggleDevice">Toggle Device</button>
      </div>
      <div>
        <button @click="handleDevice">Properties</button>
      </div>
      <div v-if="state.isUserAdmin" >
        <button @click="deleteDevice" >Delete Device</button>
      </div>
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
      this.$emit('toggle-device', this.device.deviceId);    
    },
    deleteDevice() {
      this.$emit('delete-device', this.device.deviceId);
    },
    handleDevice(){
      console.log(this.device.name);
      
      switch (this.device.name) {
        case "Blinds":
          if(this.device.online){
            alert("Blinds are online");
          }
          else{
            alert("Blinds are offline");
          }
          break;
        case "Fridge":
          this.$router.push('/fridge');
          break;
        case "Heating System":
          this.$router.push('/heating');
          break;
        case "Living Room Lights":
          this.$router.push('/lights');
          break;
        case "Locks":
          
          break;
        case "Oven":
          this.$router.push('/oven');
          break;
        case "Rumba":
          this.$router.push('/rumba');
          break;
        case "Sound System":
          this.$router.push('/sound-system');
          break;
        default:
          break;
      }
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