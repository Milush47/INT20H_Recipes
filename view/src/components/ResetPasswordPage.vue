<template>
  <div>
    <h2>Password Reset</h2>
    <form @submit.prevent="resetPassword">
      <div>
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="email" required>
      </div>
      <div>
        <label for="newPassword">New Password:</label>
        <input type="password" id="newPassword" v-model="newPassword" required>
      </div>
      <div>
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" v-model="confirmPassword" required>
      </div>
      <div>
        <button type="submit">Reset Password</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'ResetPasswordPage',
  data() {
    return {
      email: '',
      newPassword: '',
      confirmPassword: ''
    };
  },
  methods: {
    resetPassword() {
      if (this.newPassword !== this.confirmPassword) {
        alert('Passwords do not match');
        return;
      }

      const data = {
        email: this.email,
        password: this.newPassword
      };

      axios.post('http://localhost:8080/reset-password', data)
          .then(response => {
            alert('Password reset successful');
          })
          .catch(error => {
            alert('Password reset failed');
          });
    }
  }
};
</script>
