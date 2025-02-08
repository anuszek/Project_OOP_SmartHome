<template>
  <div class="fridge">
    <h1>Smart Fridge</h1>
    <span class="material-symbols-outlined" style="font-size: 100px;">kitchen</span>
    <div>
      <h2>Fridge temperature</h2>
      <div class="temperature">
        <h4>{{ fridgeTemp }}°C</h4>
        <div class="temperature-buttons">
          <button @click="increaseFridgeTemp"><span class="material-symbols-outlined" style="font-size: 1rem;">add</span></button>
          <button @click="decreaseFridgeTemp"><span class="material-symbols-outlined" style="font-size: 1rem;">remove</span></button>
        </div>
      </div>
    </div>
    <div>
      <h2>Freezer temperature</h2>
      <div class="temperature">
        <h4>{{ freezerTemp }}°C</h4>
        <div class="temperature-buttons">
          <button @click="increaseFreezerTemp"><span class="material-symbols-outlined" style="font-size: 1rem;">add</span></button>
          <button @click="decreaseFreezerTemp"><span class="material-symbols-outlined" style="font-size: 1rem;">remove</span></button>
        </div>
      </div>
    </div>
    <div class="inventory">
      <h2>Inventory</h2>
      <div class="inventory-list">
        <li v-for="item in inventory" :key="item">{{ item }}</li>
      </div>
      <div class="add-item">
        <input v-model="newItem" placeholder="Add new item" />
        <button @click="addItem">Add</button>
      </div>
    </div>
    <button @click="confirmChanges">Confirm</button>
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
      fridgeTemp: this.device.fridgeTemp,
      freezerTemp: this.device.freezerTemp,
      inventory: this.device.inventory,
      newItem: ""
    };
  },
  mounted() {
    if (typeof this.device.inventory === 'string') {
      this.inventory = this.device.inventory.replace(/[{}']/g, "").split(',');
    }
  },
  methods: {
    confirmChanges() {
      this.$emit("update-device", {
        ...this.device,
        fridgeTemp: this.fridgeTemp,
        freezerTemp: this.freezerTemp,
        inventory: "{"+this.inventory.map(item => `'${item}'`).join(',')+"}"
      });
      
    },
    addItem() {
      if (this.newItem.trim() !== '') {
        this.inventory.push(this.newItem.trim());
        this.newItem = '';
      }
    },
    increaseFridgeTemp() {
      if (this.fridgeTemp < 7) {
        this.fridgeTemp++;
      }      
    },
    decreaseFridgeTemp() {
      if (this.fridgeTemp > 1) {
        this.fridgeTemp--;
      }
    },
    increaseFreezerTemp() {
      if (this.freezerTemp < -10) {
        this.freezerTemp++;
      }
    },
    decreaseFreezerTemp() {
      if (this.freezerTemp > -20) {
        this.freezerTemp--;
      }
    }
  }
};
</script>

<style scoped>
.fridge {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
}
.inventory {
  border-radius: 5px;
  padding: 16px;
  margin: 16px;
  width: 50%;
  text-align: center;
  background-color: #ededed;
  color: #000;
  box-shadow: 2px 2px 7px #646cff;
}
.inventory-list {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  align-items: center;
  list-style-type: none;
}

.add-item {
  display: flex;
  gap: 10px;
  justify-content: center;
  align-items: center;
}

.temperature {
  display: flex;
  justify-content: space-around;
  justify-content: space-evenly;
  align-items: center;
}
.temperature-buttons {
  display: flex;
  flex-direction: column;
}

</style>