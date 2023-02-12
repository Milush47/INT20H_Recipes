<template>
  <div>
    <h2>Profile</h2>
    <p>First Name: {{ user.first_name }}</p>
    <p>Last Name: {{ user.last_name }}</p>
    <p>Email: {{ user.email }}</p>
    <button @click="logout">Logout</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Profile",
  data() {
    return {
      user: {
        first_name: "",
        last_name: "",
        email: "",
      },
    };
  },
  mounted() {
    this.fetchUser();
  },
  methods: {
    async fetchUser() {
      try {
        const res = await axios.get("http://localhost:8080/user");
        this.user = res.data;
      } catch (err) {
        console.error(err);
      }
    },
    logout() {
      localStorage.removeItem("token");
      this.$router.push("/auth/authenticate");
    },
  },
};
</script>
