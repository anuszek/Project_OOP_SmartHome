<template>
  <div>
    <p>API Call Status: {{ status.status }}</p>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      status: {},
    }
  },
  // mounted() {
  //   axios
  //     .get('http://localhost:8080/api/status')
  //     .then((response) => {
  //       this.status = response.data
  //     })
  //     .catch((error) => {
  //       console.log("Error fetching data: ", error)
  //     })
  // },
};

</script>