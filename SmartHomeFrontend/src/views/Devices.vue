<template>
  <div>
    <h1>Devices</h1>
    <p>Manage your devices here.</p>
  </div>
  <div class="device-list">
    <DeviceComponent
      v-for="device in testDevices"
      :key="device.id"
      :device="device"
      @toggle-device="toggleDevice"
    />
  </div>
</template>

<script>
  import DeviceComponent from '../components/DeviceComponent.vue';
  import axios from 'axios';

  export default {
    data() {
      return {
        devices: [],
        testDevices: [
          { id: 1, name: 'Light', status: 'OFF' },
          { id: 2, name: 'Fan', status: 'OFF' },
          { id: 3, name: 'TV', status: 'OFF' },
        ],
      };
    },
    components: {
      DeviceComponent,
    },
    async mounted() {
      await axios.get('http://localhost:8080/api/devices')
        .then((response) => {
          this.devices = response.data;
        })
        .catch((error) => {
          console.log("Error fetching data: ", error);
        });
    }
  };

</script>

<style scoped>
  .device-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
  }
</style>