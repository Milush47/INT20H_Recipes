<template>
  <div class="register-page">
    <h1>Реєстрація</h1>
    <form @submit.prevent="register" method="post">
      <div class="form-group">
        <label for="firstName">Імʼя</label>
        <input
          type="text"
          class="form-control"
          id="firstName"
          placeholder="Введіть своє імʼя"
          required
          v-model="firstName"
        />
        <span v-if="msg.firstName">{{ msg.firstName }}</span>
      </div>
      <div class="form-group">
        <label for="lastName">Прізвище</label>
        <input
          type="text"
          class="form-control"
          id="firstName"
          placeholder="Введіть своє прізвище"
          required
          v-model="lastName"
        />
        <span v-if="msg.lastName">{{ msg.lastName }}</span>
      </div>
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
        <span v-if="msg.email">{{ msg.email }}</span>
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
        <span v-if="msg.password">{{ msg.password }}</span>
      </div>
      <div class="form-group">
        <label for="password2">Підтвердження пароля</label>
        <input
          type="password"
          class="form-control"
          id="password2"
          placeholder="Підтвердження пароля"
          required
          v-model="password2"
        />
        <span v-if="msg.password2">{{ msg.password2 }}</span>
      </div>
      <button type="submit" class="btn btn-primary">Зареєструватись</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "RegisterPage",
  data() {
    return {
      firstName: "",
      lastName: "",
      email: "",
      password: "",
      password2: "",
      msg: [],
      success: false,
      valid: true,
      message: null,
    };
  },
  methods: {
    validateEmail(value) {
      if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(value)) {
        this.msg["email"] = "";
      } else {
        this.msg["email"] = "Неправильна поштова скринька";
      }
    },
    validateFirstName(value) {
      if (/[А-Я][а-я]+/g.test(value)) {
        this.msg["firstName"] = "";
      } else {
        this.msg["firstName"] = "Неправильне імʼя";
      }
    },
    validateLastName(value) {
      if (/[А-Я][а-я]+/g.test(value)) {
        this.msg["lastName"] = "";
      } else {
        this.msg["lastName"] = "Неправильне прізвище";
      }
    },
    validatePassword(value) {
      if (value.length >= 8) {
        this.msg["password"] = "";
      } else {
        this.msg["password"] = "Пароль повинен бути не менше 8 символів";
      }
    },
    validatePassword2(value) {
      if (value === this.password) {
        this.msg["password2"] = "";
      } else {
        this.msg["password2"] = "Паролі не співпадають";
      }
    },

    watch: {
    email(value) {
      this.email = value;
      this.validateEmail();
    },
    firstName(value) {
      this.firstName = value;
      this.validateFirstName();
    },
    lastName(value) {
      this.lastName = value;
      this.validateLastName();
    },
    password(value) {
      this.password = value;
      this.validatePassword();
    },
    password2(value) {
      this.password2 = value;
      this.validatePassword2();
    }
  }
  },

};
</script>

<style scoped></style>
