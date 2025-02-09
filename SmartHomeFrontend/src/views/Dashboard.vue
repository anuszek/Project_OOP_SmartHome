<template>
  <div v-if="state.isLoggedIn">
    <div class="timetable" >
      <h2>Smart Home Timetable</h2>
      <table>
        <thead>
          <tr>
            <th>Time</th>
            <th>Event</th>
            <th>Device</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(entry, index) in timetable" :key="index">
            <td>{{ entry.time }}</td>
            <td>{{ entry.event }}</td>
            <td>{{ entry.device }}</td>
            <td><button @click="deleteEvent(index)">Delete</button></td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="form">
      <h2>Add New Event</h2>
      <form @submit.prevent="addEvent">
        <div>
          <label for="time">Time:</label>
          <input type="time" v-model="newEvent.time" required>
        </div>
        <div>
          <label for="event">Event:</label>
          <input type="text" v-model="newEvent.event" required>
        </div>
        <div>
          <label for="device">Device:</label>
          <select id="device" v-model="newEvent.device" required>
            <option disabled value="">Please select a device</option>
            <option v-for="deviceType in avaliableDevices" 
              :key="deviceType"
              :value="deviceType">
              {{ deviceType }}
            </option>
          </select>
        </div>
        <button type="submit">Add Event</button>
      </form>
    </div>
  </div>
  <div v-else>
    <h2>Please log in to see the timetable.</h2>
  </div>
</template>

<script>
import axios from 'axios';
import { inject } from 'vue';

export default {
  name: 'Dashboard',
  setup() {
      const state = inject('state');

      return {
        state,
      };
    },
  data() {
    return {
      timetable: [
        { time: '07:00', event: 'Turn on heating', device: 'Heating System' },
        { time: '08:00', event: 'Open blinds', device: 'Blinds' },
        { time: '06:00', event: 'Turn on lights', device: 'Lights' },
        { time: '10:00', event: 'Turn off heating', device: 'Heating System' },
        { time: '11:00', event: 'Close blinds', device: 'Blinds' }
      ],
      newEvent: {
        time: '',
        event: '',
        device: ''
      },
      avaliableDevices: ['Blinds', 'Heating', 'Lights'],
    };
  },
  mounted(){

  },
  methods: {
    async fetchTimetable() {
      try {
        const response = await axios.get('http://localhost:3000/api/timetable');
        this.timetable = response.data;
      } catch (error) {
        console.error(error);
      }
    },
    addEvent() {
      this.timetable.push({ ...this.newEvent });
      this.newEvent.time = '';
      this.newEvent.event = '';
      this.newEvent.device = '';
    },
    deleteEvent(index) {
      this.timetable.splice(index, 1);
    }
  }
}
</script>

<style scoped>
.timetable {
  margin-top: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ccc;
  padding: 8px;
}

th {
  color: black;
  background-color: #ededed;
  text-align: left;
}

.form {
  margin-top: 20px;
  border-radius: 5px;
  padding: 16px;
  margin: 16px;
  text-align: center;
  background-color: #ededed;
  color: #000;
  box-shadow: 2px 2px 7px #646cff;
}

form div {
  margin-bottom: 10px;
}

label {
  display: inline-block;
  width: 100px;
}

input {
  padding: 5px;
  width: 200px;
}

select {
  padding: 5px;
  width: 210px;
}

button {
  padding: 5px 10px;
}
</style>