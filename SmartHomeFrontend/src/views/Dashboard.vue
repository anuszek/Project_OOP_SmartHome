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
      timetable: [],
      newEvent: {
        time: '',
        event: '',
        device: ''
      },
      avaliableDevices: ['Blinds', 'HeatingSystem', 'Lights'],
    };
  },
  mounted(){
    this.fetchTimetable();
  },
  methods: {
    async fetchTimetable() {
      try {
        const response = await axios.get('http://localhost:8080/api/timetable');
        response.data.timetable.forEach((entry) => {
          entry = entry.split('ඞ ');
          this.timetable.push({ time: entry[0], event: entry[1], device: entry[2] });
        });            
      } catch (error) {
        console.error(error);
      }
    },
    async addEvent() {
      try {
        const response = await axios.post('http://localhost:8080/api/add-timetable', {
          time: this.newEvent.time,
          event: this.newEvent.event,
          device: this.newEvent.device
        });
        if (response.status) {
          console.log('Event added successfully');
          this.timetable.push({ ...this.newEvent });
          this.newEvent.time = '';
          this.newEvent.event = '';
          this.newEvent.device = '';
        }
      } catch (error) {
        console.error(error);
      }
    },
    async deleteEvent(index) {
      try {
        const response = await axios.post('http://localhost:8080/api/delete-timetable', {
          time: this.timetable[index].time,
          event: this.timetable[index].event,
          device: this.timetable[index].device
        });
        if (response.status) {
          console.log('Event deleted successfully');
          this.timetable.splice(index, 1);
        }
      } catch (error) {
        console.error(error);
      }
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