import {createRouter, createWebHistory} from 'vue-router'
import { inject } from 'vue'
import Home from '../views/Home.vue'
import Dashboard from '../views/Dashboard.vue'
import Devices from '../views/Devices.vue'
import Users from '../views/Users.vue'
import Settings from '../views/Settings.vue'
import AddDevice from '../views/AddDevice.vue'


const routes = [
  { path: '/', component: Home },
  {path: '/dashboard', component: Dashboard},
  {path: '/devices', component: Devices},
  {path: '/users', component: Users},
  {path: '/settings', component: Settings},
  {path: '/add-device', component: AddDevice},
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;