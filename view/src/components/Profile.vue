<template>
  <div>
    <h2>Profile</h2>
    <p>First Name: {{ userData.firstname }}</p>
    <p>Last Name: {{ userData.lastname }}</p>
    <p>Email: {{ userData.email }}</p>
    <p>Preferences: {{ userData.preferences }}</p>
    <button class="logout-btn" @click="logout">Logout</button>
  </div>
</template>

<script>
import { userService } from "../service/userService.js";
import axios from "axios";

export default {
  name: "Profile",
  data() {
    return {
      userData: {
        firstname: "",
        lastname: "",
        email: "",
        preferences: "",
      },
    };
  },
  mounted() {
    this.fetchUserData();
  },

  methods: {
    async fetchUserData() {
      try {
        userService.getCurrentUser();
        this.userData = response.data.data;
      } catch (error) {
        this.error = error.message;
      }
    },
    logout() {
      userService.logout();
      this.$router.push("/auth/authenticate");
    },
  },
};
</script>

<style>
.logout-btn {
  background-color: #f44336;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 10%;
}
</style>
