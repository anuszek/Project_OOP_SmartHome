<template>
  <div class="sound-system-panel">
    <h1>Smart Sound System</h1>
    <span class="material-symbols-outlined" style="font-size: 80px;">speaker</span>
    <div :class="['controls', { 'playing': isPlaying }]">   
      <h4 class="material-symbols-outlined">Volume: {{ volume == 0 ? "volume_off" : volume }}</h4>
      <input type="range" class="slider" v-model="volume" min="0" max="100" step="5">
   
      <h4>Track: {{  }}</h4>
    
      <div>
        <button>
          <span class="material-symbols-outlined">skip_previous</span>
        </button>
        <button @click="isPlaying = !isPlaying">
          <span class="material-symbols-outlined">{{isPlaying ? "pause" : "play_arrow"}}</span>
        </button>
        <button>
          <span class="material-symbols-outlined">skip_next</span>
        </button>
      </div>
    </div>
    <div class="confirm-button">
      <button @click="confirmChanges">Confirm</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    device: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      isPlaying: false,
      volume: this.device.volume,
      tracks: this.device.playlist,
      // currentTrack: this.tracks[0],
    };
  },
  mounted() {
    console.log(this.device);
    
  },
  methods: {
    togglePower() {
      this.powerState = !this.powerState;
    },
    confirmChanges() {
      this.$emit("update-device", {
        ...this.device,
        playlist: {},
        volume: this.volume,
      });
    }
  }
};
</script>

<style scoped>
.sound-system-panel {
  padding: 20px;
}

.controls {
  margin-top: 20px;
  box-shadow: 2px 2px 7px #646cff, -2px -2px 7px #646cff;
}

.controls.playing {
  animation:  pulse 1.2s infinite;
}

@keyframes pulse {
  0% {
    box-shadow: 2px 2px 7px #646cff, -2px -2px 7px #646cff;
  }
  50% {
    box-shadow: 4px 4px 14px #646cff, -4px -4px 14px #646cff;
  }
  100% {
    box-shadow: 2px 2px 7px #646cff, -2px -2px 7px #646cff;
  }
}

.volume-control, .track-control {
  margin-top: 10px;
}

.confirm-button {
  margin-top: 20px;
}
</style>