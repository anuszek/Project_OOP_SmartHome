import {createRouter, createWebHistory} from 'vue-router'
import { inject } from 'vue'
import Home from '../views/Home.vue'
import Dashboard from '../views/Dashboard.vue'
import Devices from '../views/Devices.vue'
import Users from '../views/Users.vue'
import Settings from '../views/Settings.vue'
import AddDevice from '../views/AddDevice.vue'
import Fridge from '../views/devices/Fridge.vue'
import Lights from '../views/devices/Lights.vue'
import Heating from '../views/devices/Heating.vue'

const routes = [
  { path: '/', component: Home },
  {path: '/dashboard', component: Dashboard},
  {path: '/devices', component: Devices},
  {path: '/users', component: Users},
  {path: '/settings', component: Settings},
  {path: '/add-device', component: AddDevice},
  {path: '/fridge', component: Fridge},
  {path: '/lights', component: Lights},
  {path: '/heating', component: Heating}
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

// router.beforeEach((to, from, next) => {
//   const state = inject('state');

//   if(to.matched.some(record => record.meta.requiresAuth)) {
//     if(!state.isLoggedIn) {
//       next({path: '/'});
//     }
//     else {
//       next();
//     }
//     next();
//   }
// });

export default router;