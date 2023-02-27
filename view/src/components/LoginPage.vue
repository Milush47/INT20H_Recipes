<template>
  <form @submit.prevent="submit">
    <div>
      <label for="email">Email:</label>
      <input
        type="email"
        id="email"
        v-model="email"
        :class="{ 'is-invalid': !isValidEmail, }"
        required
      />
      <div class="invalid-feedback" v-if="!isValidEmail">
        Please enter a valid email address.
      </div>
    </div>
    <div>
      <label for="password">Password:</label>
      <input
        type="password"
        id="password"
        v-model="password"
        :class="{ 'is-invalid': !isValidPassword }"
        required
      />
      <div class="invalid-feedback" v-if="!isValidPassword">
        Password must be at least 8 characters long.
      </div>
    </div>
    <button type="submit" :disabled="!isValidEmail || !isValidPassword">
      Sign in
    </button>
  </form>
</template>

<script>
import { userService } from "../service/userService.js";
import axios from "axios";

export default {
  data() {
    return {
      email: "",
      password: "",
    };
  },
  computed: {
    isValidEmail() {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      return emailRegex.test(this.email);
    },
    isValidPassword() {
      const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%_^&*])(?=.{8,})/;
      return passwordRegex.test(this.password);
    },
    isExistEmail() {
      
    }
  },
  methods: {
    submit() {
      const request = {
        email: this.email,
        password: this.password,
      };
      userService
        .login(request)
        .then((response) => {
          this.$router.push('/profile')
        })
        .catch((error) => {
          if (error.response && error.response.status === 409) {
            this.emailError = error.message;
          } else {
            this.loginError =
              "Sign in failed. Please try again later.";
          }
        });
    },
  },
};
</script>

<style>
.is-invalid {
  border: 1px solid red;
}
.invalid-feedback {
  color: red;
}
</style>
