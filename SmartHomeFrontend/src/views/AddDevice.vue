<template>
  <div class="add-device">
    <h2>Add Device</h2>

    <form @submit.prevent="handleAddDevice">
      <div class="form-group">
        <label for="device">Device Type: </label>
        <select id="device" v-model="deviceType" required>
          <option disabled value="">Please select a device</option>
          <option v-for="deviceType in devices" :key="deviceType" :value="deviceType">{{ deviceType }}</option>
        </select>
        <input class="input" type="text" v-model="name" placeholder="Name" required>
        <input class="input" type="text" v-model="description" placeholder="Description">
      </div>
      <div class="form-buttons">
        <button @click="navigateToDevices">Cancel</button>
        <button type="submit">Confirm</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      deviceType: '',
      name: '',
      description: '',
      devices: ['Blinds', 'Fridge', 'HeatingSystem', 'Lights', 'Locks', 'Oven', 'Rumba', 'SoundSystem'],
    };
  },
  methods: {
    async handleAddDevice() {
      console.log(this.name, this.deviceType, this.description);
      
      try {
        const response = await axios.post('http://localhost:8080/api/add-device', {
          name: this.name,
          deviceType: this.deviceType,
          description: this.description,
        });

        if (response.data) {
          alert('Device added successfully');
        } else {
          alert('Failed to add device: ' + response.data.error);
        }
        
      } catch (error) {
        console.error('Error:', error);
      }
    },
    navigateToDevices() {
      this.$router.push('/devices');
    },
  },
};
</script>

<style scoped>
.add-device {
  color: black;
  width: 40vw;
  min-height: 60vh; 
  padding: 20px;
  border-radius: 5px;
  background-color: #ededed;
  box-shadow: 2px 2px 7px #646cff;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 10px;
  font-weight: bold;
}

.form-group select,
.form-group input {
  width: 60%;
  margin-bottom: 10px;
  padding: 8px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.form-buttons {
  display: flex;
  justify-content: space-around;
  justify-content: space-evenly;
}

.form-buttons button[type="submit"] {
  background-color: #4CAF50;
  color: white;
}
</style>