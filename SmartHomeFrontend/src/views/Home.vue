<template>
  <div class="home" v-if="state.isLoggedIn">
    <h1>Welcome {{ state.userName }} to Your Smart Home</h1>
    <div class="dashboard">
      <div class="status">
        <h2>Current Status</h2>
        <p>Lights: {{ lightsStatus }}</p>
        <p>Thermostat: {{ thermostatStatus }}°C</p>
        <p>Locks: {{ securityStatus }}</p>
      </div>
      <div class="controls">
        <h2>Quick Controls</h2>
        <div class="controls-buttons">
          <button @click="toggleLights">Toggle Lights</button>
          <button @click="adjustThermostat">Adjust Thermostat</button>
          <button @click="toggleSecurity">Toggle Locks</button>
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
        <span v-if="weather" class="material-symbols-outlined" style="font-size: 80px;">{{ weatherIcon }}</span>
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
      lightsStatus: "",
      thermostatStatus: "",
      securityStatus: "",
      recentActivity: [
        { id: 1, description: 'Lights turned on in the living room' },
        { id: 2, description: 'Thermostat set to 22°C' },
      ],
      weather: null,
      weatherIcon: null,
      energyConsumption: Math.floor(Math.random() * 1000),
      notifications: [
        { id: 1, message: 'Front door left open' },
        { id: 2, message: 'Motion detected in the backyard' },
      ],
    };
  },
  mounted() {
    this.fetchWeather();
    this.fetchDevices();
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
            this.devices = devices;            
          })
          .catch((error) => {
            console.log("Error fetching data: ", error);
          });

          if (!this.devices.find(device => device.deviceType === 'Lights')) {
            this.lightsStatus = 'No lights found';
          }
          else {
            this.lightsStatus = `color: ${this.devices.find(device => device.deviceType === 'Lights').lightColor},
                              brightness: ${this.devices.find(device => device.deviceType === 'Lights').lightLevel}`;
          }
          if (!this.devices.find(device => device.deviceType === 'HeatingSystem')) {
            this.thermostatStatus = 'No thermostat found';
          }
          else {
            this.thermostatStatus = this.devices.find(device => device.deviceType === 'HeatingSystem').temperature;
          }
          if (!this.devices.find(device => device.deviceType === 'Locks')) {
            this.securityStatus = 'No locks found';
          }
          else {
            this.securityStatus = this.devices.find(device => device.deviceType === 'Locks').locked ? 'Locked' : `Lockedn't`;
          }
      },
    toggleLights() {
      const device = this.devices.find(device => device.deviceType === 'Lights');
      if (device == undefined) return;
      device.lightLevel = device.lightLevel === 0 ? 80 : 0;
      this.lightsStatus = `color: ${device.lightColor}, brightness: ${device.lightLevel}`;
      this.handleUpdateDevice(device);
    },
    adjustThermostat() {
      const device = this.devices.find(device => device.deviceType === 'HeatingSystem');
      if (device == undefined) return;
      device.temperature = device.temperature < 20 ? device.temperature + 2 : device.temperature - 2;
      this.thermostatStatus = device.temperature;
      this.handleUpdateDevice(device);
    },
    toggleSecurity() {
      const device = this.devices.find(device => device.deviceType === 'Locks');
      if (device == undefined) return;
      device.locked = !device.locked;
      this.securityStatus = device.locked ? 'Locked' : `Lockedn't`;
      this.handleUpdateDevice(device);
    },
    async handleUpdateDevice(device){       
        try {
          const response = await axios.post('http://localhost:8080/api/update-device', device);
          
          if (response.data) {
          //  console.log("Device updated successfully");
          }          
        } catch (error) {
          console.log("Error updating device: ", error);
        }
      },
    fetchWeather(){
      const apiKey = '451e252e659bf3f308a40bfb80b72ce4';
      const city = 'Krakow';
      const url = `https://api.openweathermap.org/data/2.5/weather?q=${city}&units=metric&appid=${apiKey}`;

      axios.get(url)
        .then(response => {
          const data = response.data;
          this.weather = {
            main: data.weather[0].main,
            description: data.weather[0].description,
            temp: data.main.temp,
          };
          this.assignWeatherIcon();
        })
        .catch(error => {
          console.error('Error fetching weather data:', error);
        });
    },
    assignWeatherIcon(){
      if (this.weather){
        const description = this.weather.main.toLowerCase();
       
        switch (description) {
          case 'clear':
            this.weatherIcon = 'wb_sunny';
            break;
          case 'few clouds':
            this.weatherIcon = 'partly_cloudy_day';
            break;
          case 'clouds':
            this.weatherIcon = 'cloud';
            break;
          case 'drizzle':
            this.weatherIcon = 'rainy_light';
            break;
          case 'rain':
            this.weatherIcon = 'rainy';
            break;
          case 'thunderstorm':
            this.weatherIcon = 'thunderstorm';
            break;
          case 'snow':
            this.weatherIcon = 'weather_snowy';
            break;
          case 'mist':
            this.weatherIcon = 'mist';
            break;
          default:
            break;
        }
      }
    },
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