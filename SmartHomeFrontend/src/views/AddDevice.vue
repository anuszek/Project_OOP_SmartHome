<template>
  <div class="add-device">
    <h2>Add Device</h2>

    <form @submit.prevent="handleAddDevice">
      <div class="form-group">
        <label for="device">Device Type: </label>
        <select id="device" v-model="device" required>
          <option disabled value="">Please select a device</option>
          <option v-for="device in devices" :key="device" :value="device">{{ device }}</option>
        </select>
      </div>
      <button type="submit">Confirm</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      device: '',
      devices: ['Blinds', 'Fridge', 'Heating', 'Lights', 'Locks', 'Oven', 'Rumba', 'Sound System'],
    };
  },
  methods: {
    async handleAddDevice() {
      try {
        const response = await axios.post('http://localhost:8080/api/add-device', {
          device: this.device,
        });

        if (response.data.success) {
          alert('Device added successfully');
        } else {
          alert('Failed to add device: ' + response.data.error);
        }
        
      } catch (error) {
        console.error('Error:', error);
      }
    },
  },
};
</script>

<style>
.add-device {
  color: black;
  width: 40vw;
  height: 60vh;
  margin: 0 auto;
  padding: 30px;
  border-radius: 5px;
  background-color: #ededed;
  box-shadow: 2px 2px 7px #646cff;
}

.add-device h2 {
  text-align: center;
  margin-bottom: 20px;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.form-group {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
  width: 50%;
}

.form-group label {
  flex: 1.2;
  margin-bottom: 20px;
  font-weight: bold;
}

.form-group select {
  flex: 1;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-sizing: border-box;
  margin-bottom: 20px;
}
</style>