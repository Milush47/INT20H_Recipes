<template>
  <form @submit.prevent="submit">
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
    <button type="submit" :disabled="!isValidEmail || !isValidPassword">
      Login
    </button>
  </form>
</template>

<script>
export default {
  data() {
    return {
      email: "",
      password: "",
    };
  },
  computed: {
    isValidEmail() {
      const emailRegex = /^\S+@\S+\.\S+$/;
      return emailRegex.test(this.email);
    },
    isValidPassword() {
      return this.password.length >= 8;
    },
  },
  methods: {
    submit() {
      const reigsterRequest = {
        email: this.email,
        password: this.password,
      };
      fetch("http://localhost:8080/auth/authenicate", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(reigsterRequest),
      })
        .then((response) => {
          if (response.ok) {
          } else {
          }
        })
        .catch((error) => {
        });
    },
  },
};
</script>

<style>
.is-invalid {
  border-color: red;
}
.invalid-feedback {
  color: red;
  font-size: 14px;
}
</style>
