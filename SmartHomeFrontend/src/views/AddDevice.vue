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
      <button type="submit">Confirm</button>
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
      devices: ['Blinds', 'Fridge', 'Heating', 'Lights', 'Locks', 'Oven', 'Rumba', 'SoundSystem'],
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
  /* display: flex; */
  align-items: center;
  margin-bottom: 15px;
  width: 50%;
}

.form-group label {
  flex: 1.2;
  margin-bottom: 20px;
  font-weight: bold;
}

.input {
 height: 3vh;
 margin-bottom: 15px;
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