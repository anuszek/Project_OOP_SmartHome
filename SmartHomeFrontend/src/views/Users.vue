<template>
  <div>
    <div v-if="state.isLoggedIn">
      <div v-if="state.userName == 'AdamusLech'">
        <img src="../assets/adamus.png" alt="" style="width: 100px; height: 100px;">
      </div>
      <h1>Hello {{ state.userName }}</h1>
      <button @click="logOut">Log Out</button>
      <div v-if="state.isUserAdmin" class="users-list">  
        <h2>Users: </h2>
          <UserComponent v-for="user in users" 
            :key="user.id"
            :user="user" 
            @remove-user="removeUser"
            @grant-admin="grantAdmin"
          />
        
      </div>
    </div>
    <div v-else>
      <p>hello, please log in</p>
    </div>
  </div>
</template>

<script>
import { inject } from 'vue';
import axios from 'axios';
import UserComponent from '../components/UserComponent.vue';

export default {
  setup() {
    const state = inject('state');
    const logOut = inject('logOut');

    return {
      state,
      logOut,
    };
  },
  components: {
    UserComponent,
  },
  data() {
    return {
      users: [],
    };
  },
  async mounted() {
    await this.fetchUsers();
  },
  methods: {
    async fetchUsers() {
      await axios.get('http://localhost:8080/api/users')
      .then((response) => {
        this.users = []
        Object.values(response.data).forEach((obj) => {
          Object.keys(obj).forEach((key) => {
            this.users.push({
              name: key,
              isAdmin: obj[key],
            });
          });
        });      
      })
      .catch((error) => {
        console.error('Error fetching users:', error);
      });
    },
    async removeUser(userName) {
      if (userName == this.state.userName) {
        alert("You can't delete yourself");
        return;
      }
      try {
          const response = await axios.post('http://localhost:8080/api/delete-user', {
            userName,
          });
        
          if (response.data == "User deleted") {
            alert("User deleted successfully");
            this.users = this.users.filter((user) => user.name !== userName);
          }
        } catch (error) {
          console.log("Error deleting user: ", error);
        }
    },
    async grantAdmin(userName) {
      try {
          const response = await axios.post('http://localhost:8080/api/grant-admin', {
            userName,
          });
        
          if (response) {
            console.log(response);
          }
        } catch (error) {
          console.log("Error deleting user: ", error);
        }
    },
  },
};
</script>

<style scoped>
.users-list {
  /* display: flex;
  flex-wrap: wrap; */
  width: 60vw;
  justify-content: center;
  text-align: left;
}
</style>