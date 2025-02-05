<template>
  <div>
    <h1>Settings</h1>
    <button @click="showOverlay">Light Mode</button>
    <div v-if="isOverlayVisible" class="overlay">
      <button class="close-button" @click="hideOverlay">
        <span class="material-symbols-outlined">close</span>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isOverlayVisible: false
    };
  },
  methods: {
    showOverlay() {
      this.isOverlayVisible = true;
    },
    hideOverlay() {
      this.isOverlayVisible = false;
    }
  }
};
</script>

<style>
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: white;
  z-index: 1000;
}

.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  background: none;
  border: none;
  font-size: 20px;
  cursor: pointer;
  color: black;
}
</style>