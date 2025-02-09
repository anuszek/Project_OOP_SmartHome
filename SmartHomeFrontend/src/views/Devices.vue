<template>
  <div v-if="state.isLoggedIn">
    <div v-if="selectedDevice" class="device-panel">
      <component :is="selectedDeviceComponent" :device="selectedDevice" @update-device="handleUpdateDevice"/>
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
  <div v-else>
    <h2>Please log in to see the timetable.</h2>
  </div>
</template>

<script>
  import { inject } from 'vue';
  import axios from 'axios';
  import DeviceComponent from '../components/DeviceComponent.vue';
  import Fridge from '../components/devices/Fridge.vue';
  import HeatingSystem from '../components/devices/HeatingSystem.vue';
  import Lights from '../components/devices/Lights.vue';
  import Blinds from '../components/devices/Blinds.vue';
  import Locks from '../components/devices/Locks.vue';
  import Oven from '../components/devices/Oven.vue';
  import Rumba from '../components/devices/Rumba.vue';
  import SoundSystem from '../components/devices/SoundSystem.vue';

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
        scrollPosition: 0,
      };
    },
    components: {
      DeviceComponent,
      Blinds,
      Fridge,
      HeatingSystem,
      Lights,
      Locks,
      Oven,
      Rumba,
      SoundSystem,
    },
    async mounted() {    
      await this.fetchDevices();
    },
    methods: {
      async fetchDevices() {
        this.devices = [];
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
      async handleToggleDevice(device) {
        try {
          const response = await axios.post('http://localhost:8080/api/toggle-device', device);
        
          if (response.data) {
            const dev = this.devices.find((d) => d.deviceId === device.deviceId);
            if (device) {
              device.online = !device.online;
            }
          }
        } catch (error) {
          console.log("Error toggling device: ", error);
        }
      },
      async handleUpdateDevice(device){       
        console.log(device);

        try {
          const response = await axios.post('http://localhost:8080/api/update-device', device);
          
          if (response.data) {
           console.log("Device updated successfully");
          }          
        } catch (error) {
          console.log("Error updating device: ", error);
        }
      },
      async handleDeleteDevice(device) {
        try {
          const response = await axios.post('http://localhost:8080/api/delete-device', device);
        
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
        console.log(device.deviceType);
        this.selectedDevice = device;
        this.scrollPosition = window.scrollY;
        window.scrollTo(0, 0);
        // switch (device.name) {
        //   case "Blinds":
        //     this.selectedDeviceComponent = 'Blinds';
        //     break;
        //   case "Fridge":
        //     this.selectedDeviceComponent = 'Fridge';
        //     break;
        //   case "Heating System":
        //     this.selectedDeviceComponent = 'Heating';
        //     break;
        //   case "Living Room Lights":
        //     this.selectedDeviceComponent = 'Lights';
        //     break;
        //   case "Front Door":
        //     this.selectedDeviceComponent = 'Locks';
        //     break;
        //   case "Oven":
        //     this.selectedDeviceComponent = 'Oven';
        //     break;
        //   case "Rumba":
        //     this.selectedDeviceComponent = 'Rumba';
        //     break;
        //   case "Sound System":
        //     this.selectedDeviceComponent = 'SoundSystem';
        //     break;
        //   default:
        //     break;
        // }
        this.selectedDeviceComponent = device.deviceType;
      },
      clearSelectedDevice() {
        this.selectedDevice = null;
        this.$nextTick(() => {
          window.scrollTo(0, this.scrollPosition);
        });
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
    min-height: 80vh;
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