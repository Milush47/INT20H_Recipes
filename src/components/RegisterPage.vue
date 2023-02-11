<template>
  <div class="registration-container">
    <h1>Sign Up</h1>
    <form>
      <div class="form-group">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" v-model="firstname" />
        <p v-if="errors.firstname" class="error">{{ errors.firstname }}</p>
      </div>
      <div class="form-group">
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" v-model="lastname" />
        <p v-if="errors.lastname" class="error">{{ errors.lastname }}</p>
      </div>
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="email" />
        <p v-if="errors.email" class="error">{{ errors.email }}</p>
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" />
        <p v-if="errors.password" class="error">{{ errors.password }}</p>
      </div>
      <div class="form-group">
        <label for="passwordConfirmation">Confirm Password:</label>
        <input
          type="password"
          id="passwordConfirmation"
          v-model="passwordConfirmation"
        />
        <p v-if="errors.passwordConfirmation" class="error">
          {{ errors.passwordConfirmation }}
        </p>
      </div>
      <div class="form-group">
        <button type="submit" @click.prevent="register">Sign Up</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      firstname: "",
      lastname: "",
      email: "",
      password: "",
      passwordConfirmation: "",
      errors: {
        firstname: "",
        lastname: "",
        email: "",
        password: "",
        passwordConfirmation: "",
      },
    };
  },
  methods: {
    async register() {
      this.clearErrors();
      if (!this.firstname) {
        this.errors.firstname = "First name is required";
      }
      if (!this.lastname) {
        this.errors.lastname = "Last name is required";
      }
      if (!this.email) {
        this.errors.email = "Email is required";
      } else if (!this.validateEmail(this.email)) {
        this.errors.email = "Email is invalid";
      }
      if (!this.password) {
        this.errors.password = "Password is required";
      } else if (this.password.length < 6) {
        this.errors.password = "Password must be at least 6 characters long";
      }
      if (!this.passwordConfirmation) {
        this.errors.passwordConfirmation = "Password confirmation is required";
      } else if (this.password !== this.passwordConfirmation) {
        this.errors.passwordConfirmation =
          "Password confirmation does not match";
      }

      if (!Object.values(this.errors).some((error) => error)) {
        try {
          const response = await axios.post("https://localhost:8080/auth/register", {
            firstName: this.firstname,
            lastName: this.lastname,
            email: this.email,
            password: this.password,
            passwordConfirmation: this.passwordConfirmation,
          });
          console.log(response.data);
        } catch (error) {
          console.error(error);
        }
      }
    },
    clearErrors() {
      this.errors = {
        firstname: "",
        lastname: "",
        email: "",
        password: "",
        passwordConfirmation: "",
      };
    },
    validateEmail(email) {
      const regex =
        /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return regex.test(email);
    },
  },
};
</script>

<style>
.registration-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

h2 {
  margin-bottom: 20px;
}

form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.form-group {
  margin-bottom: 20px;
}

input {
  padding: 10px;
  font-size: 16px;
  border: 1px solid gray;
  border-radius: 5px;
  width: 300px;
}

.error {
  color: red;
  font-size: 14px;
  margin-top: 5px;
}

button {
  padding: 10px 20px;
  font-size: 16px;
  background-color: green;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>
