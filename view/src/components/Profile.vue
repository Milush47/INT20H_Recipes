<template>
  <div>
    <h2>Profile</h2>
    <p>First Name: {{ userData.firstname }}</p>
    <p>Last Name: {{ userData.lastname }}</p>
    <p>Email: {{ userData.email }}</p>
    <button class="logout-btn" @click="logout">Logout</button>
  </div>
</template>

<script>
import { userService } from "../service/userService.js";

export default {
  name: "Profile",
  data() {
    return {
      userData: {
        firstname: "",
        lastname: "",
        email: "",
      },
    };
  },
  mounted() {
    //this.fetchUser();
  },
  async created() {
    const response = await axios.get('user'); 
    this.userData = response.data;
  },
  methods: {
    // async fetchUser() {
    //   try {
    //     const res = await axios.get("http://localhost:8080/user");
    //     this.user = res.data;
    //   } catch (err) {
    //     console.error(err);
    //   }
    // },
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
