<template>
  <div>
    <h3>Manage your devices here.</h3>
  </div>
  <div v-if="state.isUserAdmin">
    <button>Add Device</button>
  </div>
  <div v-if="state.isLoggedIn" class="device-list">
    <DeviceComponent
      v-for="device in devices"
      :key="device.deviceId"
      :device="device"
      @toggle-device="handleToggleDevice"
      @delete-device="handleDeleteDevice"
    />
  </div>
</template>

<script>
  import { inject } from 'vue';
  import axios from 'axios';
  import DeviceComponent from '../components/DeviceComponent.vue';

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
      };
    },
    components: {
      DeviceComponent,
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

    },
  };

</script>

<style scoped>
  .device-list {
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    grid-auto-rows: 1fr;
    flex-wrap: wrap;
    justify-content: center;
  }
</style>