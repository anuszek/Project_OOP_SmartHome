<template>
  <nav>
    <ul>
      <li><router-link to="/dashboard">Dashboard</router-link></li>
      <li><router-link to="/devices">Devices</router-link></li>
      <li><router-link to="/users">Users</router-link></li>
      <li><router-link to="/settings">Settings</router-link></li>
    </ul>
  </nav>
</template>

<script>
  export default { }
</script>

<style scoped>
  nav {
    background-color: #333;
    color: white;
    padding: 10px;
  }

  ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
  }

  li {
    display: inline;
    margin-right: 10px;
  }

  a {
    color: white;
    text-decoration: none;
  }

  a:hover {
    text-decoration: underline;
  }
</style>