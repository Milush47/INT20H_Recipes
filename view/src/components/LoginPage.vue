<template>
  <section class="login">
    <div class="container">
      <h1 class="title">Вхід</h1>
      <form @submit.prevent="register" method="post">
        <div class="form-group">
          <label for="email">Електронна пошта</label>
          <input
            type="text"
            class="form-control"
            id="email"
            placeholder="Введіть електронну пошту"
            required
            v-model="email"
          />
          <span oninput="validateEmail(email)" v-if="msg.email">{{ msg.email }}</span>
        </div>
        <div class="form-group">
          <label for="password">Пароль</label>
          <input
            type="password"
            class="form-control"
            id="password"
            placeholder="Пароль"
            required
            v-model="password"
          />
          <span oninput="validatePassword(password)" v-if="msg.password">{{ msg.password }}</span>
        </div>
        <button type="submit" class="btn btn-primary">Увійти</button>
      </form>
    </div>
  </section>
</template>

<script>
export default {
  name: "LoginPage",
  data() {
    return {
      email: "",
      password: "",
      msg: [],
    };
  },
  methods: {
    validateEmail(value) {
      const regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

      if (regex.test(value)) {
        this.msg["email"] = "";
      } else if (!regex.test(value)) {
        this.msg["email"] = "Неправильна поштова скринька";
      }
    },
    validatePassword(value) {
      if (value.length >= 8) {
        this.msg["password"] = "";
      } else {
        this.msg["password"] = "Пароль повинен бути не менше 8 символів";
      }
    },
  },
  watch: {
    email(value) {
      this.email = value;
      this.validateEmail();
    },
    password(value) {
      this.password = value;
      this.validatePassword();
    },
  },

};
</script>

<style scoped></style>
