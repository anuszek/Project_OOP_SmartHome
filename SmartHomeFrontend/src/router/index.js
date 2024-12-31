import {createRouter, createWebHistory} from 'vue-router'
import App from '../App.vue'
import Dashboard from '../views/Dashboard.vue'
import Devices from '../views/Devices.vue'
import Users from '../views/Users.vue'
import Settings from '../views/Settings.vue'


const routes = [
  { path: '/', component: App },
  {path: '/dashboard', component: Dashboard},
  {path: '/devices', component: Devices},
  {path: '/users', component: Users},
  {path: '/settings', component: Settings}
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;