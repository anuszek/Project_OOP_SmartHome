<template>
  <div v-if="state.isLoggedIn">
    <div v-if="selectedDevice" class="device-panel">
      <component :is="selectedDeviceComponent" :device="selectedDevice" />
      <button @click="clearSelectedDevice">Back to Devices</button>
    </div>
    <div v-else>
      <div>
        <h1>Manage your devices here.</h1>
      </div>
        <div v-if="state.isUserAdmin">
          <button @click="navigateTo('/add-device')" >Add Device</button>
        </div>
      <div class="device-list">
        <DeviceComponent
          v-for="device in devices"
          :key="device.deviceId"
          :device="device"
          @toggle-device="handleToggleDevice"
          @delete-device="handleDeleteDevice"
          @display-device="displayDevice"
        />
      </div>
  </div>
  </div>
</template>

<script>
  import { inject } from 'vue';
  import axios from 'axios';
  import DeviceComponent from '../components/DeviceComponent.vue';
  import Fridge from '../components/devices/Fridge.vue';
  import Heating from '../components/devices/Heating.vue';
  import Lights from '../components/devices/Lights.vue';

  export default {
    setup() {
      const state = inject('state');

      return {
        state,
      };
    },

    data() {
      return {
        devices: [],
        selectedDevice: null,
        selectedDeviceComponent: null,
      };
    },
    components: {
      DeviceComponent,
      Fridge,
      Heating,
      Lights,
    },
    async mounted() {
      await this.fetchDevices();
    },
    methods: {
      async fetchDevices() {
        await axios.get('http://localhost:8080/api/devices')
          .then((response) => {
            let devicesString = response.data.devices; 
            let devices = [];
            for(let i = 0; i < devicesString.length; i++) {
              let device = JSON.parse(devicesString[i]);
              devices.push(device);
            }      
            devices.sort((a, b) => a.name.localeCompare(b.name));
            this.devices = devices;
          })
          .catch((error) => {
            console.log("Error fetching data: ", error);
          });
      },
      async handleToggleDevice(deviceId) {
        try {
          const response = await axios.post('http://localhost:8080/api/toggle-device', {
            deviceId: deviceId,
          });
        
          if (response.data) {
            const device = this.devices.find((d) => d.deviceId === deviceId);
            if (device) {
              device.online = !device.online;
            }
          }
        } catch (error) {
          console.log("Error toggling device: ", error);
        }
      },
      async handleDeleteDevice(deviceId) {
        try {
          const response = await axios.post('http://localhost:8080/api/delete-device', {
            deviceId: deviceId,
          });
        
          if (response.data.success) {
            this.fetchDevices();
          }
        } catch (error) {
          console.log("Error deleting device: ", error);
        }
      },
      navigateTo(path) {
        this.$router.push(path);
      },
      displayDevice(device){
        this.selectedDevice = device;
        window.scrollTo(0, 0);
        switch (device.name) {
          case "Blinds":
            break;
          case "Fridge":
            this.selectedDeviceComponent = 'Fridge';
            break;
          case "Heating System":
            console.log(device);
            
            this.selectedDeviceComponent = 'Heating';
            break;
          case "Living Room Lights":
            this.selectedDeviceComponent = 'Lights';
            break;
          case "Locks":
            break;
          case "Oven":
            break;
          case "Rumba":
            break;
          case "Sound System":
            break;
          default:
            break;
        }
      },
      clearSelectedDevice() {
        window.scrollTo(0, 0);
        this.selectedDevice = null;
        this.selectedDeviceComponent = null;
      },
    },
  };

</script>

<style scoped>
  .device-panel {
    padding: 20px;
    border: 5px solid #ccc;
    border-radius: 10px;
    width: 60vw;
    height: 80vh;
    margin: auto;
    box-shadow: 2px 2px 7px #646cff;
  }
  .device-list {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    grid-auto-rows: 1fr;
    flex-wrap: wrap;
    justify-content: center;
  }
</style>