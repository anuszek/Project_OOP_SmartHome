<template>
  <div class="home" v-if="state.isLoggedIn">
    <h1>Welcome {{ state.userName }} to Your Smart Home</h1>
    <div class="dashboard">
      <div class="status">
        <h2>Current Status</h2>
        <p>Lights: {{ lightsStatus }}</p>
        <p>Thermostat: {{ thermostatStatus }}°C</p>
        <p>Security System: {{ securityStatus }}</p>
      </div>
      <div class="controls">
        <h2>Controls</h2>
        <div class="controls-buttons">
          <button @click="toggleLights">Toggle Lights</button>
          <button @click="adjustThermostat">Adjust Thermostat</button>
          <button @click="toggleSecurity">Toggle Security System</button>
        </div>
      </div>
      <div class="activity">
        <h2>Recent Activity</h2>
        <ul>
          <li v-for="activity in recentActivity" :key="activity.id">{{ activity.description }}</li>
        </ul>
      </div>
      <div class="weather">
        <h2>Weather</h2>
        <p v-if="weather">{{ weather.description }}, {{ weather.temp }}°C</p>
        <p v-else>Loading...</p>
      </div>
      <div class="energy">
        <h2>Energy Consumption</h2>
        <p>{{ energyConsumption }} kWh</p>
      </div>
      <div class="notifications">
        <h2>Notifications</h2>
        <ul>
          <li v-for="notification in notifications" :key="notification.id">{{ notification.message }}</li>
        </ul>
      </div>
    </div>
  </div>
  <div v-else>
    <h1>Welcome to Your Smart Home</h1>
    <h2>Please log in to access your smart home.</h2>
  </div>
</template>

<script>
import { inject } from 'vue';
import axios from 'axios';

export default {
  setup() {
    const state = inject('state');

    return {
      state,
    };
  },

  data() {
    return {
      lightsStatus: 'Off',
      thermostatStatus: 22,
      securityStatus: 'Armed',
      recentActivity: [
        { id: 1, description: 'Lights turned on in the living room' },
        { id: 2, description: 'Thermostat set to 22°C' },
      ],
      weather: null,
      energyConsumption: 150,
      notifications: [
        { id: 1, message: 'Front door left open' },
        { id: 2, message: 'Motion detected in the backyard' },
      ],
    };
  },
  methods: {
    toggleLights() {
      this.lightsStatus = this.lightsStatus === 'On' ? 'Off' : 'On';
    },
    adjustThermostat() {
      this.thermostatStatus = this.thermostatStatus === 22 ? 24 : 22;
    },
    toggleSecurity() {
      this.securityStatus = this.securityStatus === 'Armed' ? 'Disarmed' : 'Armed';
    },
    fetchWeather(){
      const apiKey = '451e252e659bf3f308a40bfb80b72ce4';
      const city = 'Krakow';
      const url = `https://api.openweathermap.org/data/2.5/weather?q=${city}&units=metric&appid=${apiKey}`;

      axios.get(url)
        .then(response => {
          const data = response.data;
          this.weather = {
            description: data.weather[0].description,
            temp: data.main.temp,
          };
        })
        .catch(error => {
          console.error('Error fetching weather data:', error);
        });
    }
  },
  mounted() {
    this.fetchWeather();
  },
};
</script>

<style>
.home {
  padding: 20px;
}

.dashboard {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 25px;
  grid-auto-rows: 1fr;
  flex-wrap: wrap;
  justify-content: center;
}

.status, .controls, .activity, .weather, .energy, .notifications {
  border: 1px solid #ccc;
  padding: 10px;
  border-radius: 5px;
  background-color: #ededed;
  color: black;
  box-shadow: 2px 2px 7px #646cff;
}

.controls {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.controls-buttons {
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 10px;
  align-items: center; /* Center the buttons horizontally */
}

.controls-buttons button {
  width: 60%; /* Set a fixed width for the buttons */
}

h2 {
  margin-top: 0;
}
</style>