<template>
  <form @submit.prevent="register">
    <div>
      <label for="first-name">First Name:</label>
      <input
        type="text"
        id="first-name"
        v-model="firstname"
        :class="{ 'is-invalid': !isValidFirstName }"
        required
      />
      <div class="invalid-feedback" v-if="!isValidFirstName">
        Please enter a valid first name.
      </div>
    </div>
    <div>
      <label for="last-name">Last Name:</label>
      <input
        type="text"
        id="last-name"
        v-model="lastname"
        :class="{ 'is-invalid': !isValidLastName }"
        required
      />
      <div class="invalid-feedback" v-if="!isValidLastName">
        Please enter a valid last name.
      </div>
    </div>
    <div>
      <label for="email">Email:</label>
      <input
        type="email"
        id="email"
        v-model="email"
        :class="{ 'is-invalid': !isValidEmail }"
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
    <div>
      <label for="confirm-password">Confirm Password:</label>
      <input
        type="password"
        id="confirm-password"
        v-model="confirmedPassword"
        :class="{ 'is-invalid': !isValidConfirmPassword }"
        required
      />
      <div class="invalid-feedback" v-if="!isValidConfirmPassword">
        Passwords do not match.
      </div>
    </div>
    <button
      type="submit"
      :disabled="
        !isValidFirstName ||
        !isValidLastName ||
        !isValidEmail ||
        !isValidPassword ||
        !isValidConfirmPassword
      "
    >
      Register
    </button>
  </form>
</template>

<script>
import { userService } from "../service/userService.js";
import { mapActions } from 'vuex';
import axios from 'axios';

export default {
  data() {
    return {
      firstname: "",
      lastname: "",
      email: "",
      password: "",
      confirmedPassword: "",
    };
  },
  computed: {
    isValidFirstName() {
      const nameRegex = /^[a-zA-Z]+([ -][a-zA-Z]+)*$/;
      return nameRegex.test(this.firstname);
    },
    isValidLastName() {
      const nameRegex = /^[a-zA-Z]+([ -][a-zA-Z]+)*$/;
      return nameRegex.test(this.lastname);
    },
    isValidEmail() {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      return emailRegex.test(this.email);
    },
    isValidPassword() {
      return this.password.length >= 8;
    },
    isValidConfirmPassword() {
      return this.confirmedPassword === this.password;
    },
  },
  methods: {
  //   ...mapActions(['submit']),
  //   submit() {
  //     const userData = {
  //       firstname: this.firstname,
  //       lastname: this.lastname,
  //       email: this.email,
  //       password: this.password,
  //       confirmedPassword: this.confirmedPassword,
  //     };
  //     // userService
  //     //   .register(registerRequest)
  //     //   .then((response) => {
  //     //     this.$router.push("/auth/authenticate");
  //     //   })
  //     //   .catch((error) => {
  //     //     if (error.response && error.response.status === 409) {
  //     //     this.emailError = error.message;
  //     //   } else {
  //     //     this.registrationError = 'Registration failed. Please try again later.'
  //     //   }
  //     //   });

  //       this.submit(userData);
  //   },

    async register() {
      const registerRequest = await axios.post('auth/register', {
        firstname: this.firstname,
        lastname: this.lastname,
        email: this.email,
        password: this.password,
        confirmedPassword: this.confirmedPassword,
      });

      await userService.register(registerRequest);

      localStorage.setItem('token', registerRequest.data.data.token);

      this.$router.push('/auth/authenticate');
    }
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
