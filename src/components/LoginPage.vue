<template>
  <section class="login">
    <div class="container">
      <h1 class="title">
        Вхід
      </h1>
      <form>
        <div class="form-group">
          <label for="exampleInputEmail1">Електронна пошта</label>
          <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"
                 placeholder="Введіть електронну пошту">
        </div>
        <div class="form-group">
          <label for="exampleInputPassword1">Пароль</label>
          <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Введіть пароль">
        </div>
        <button type="submit" class="btn btn-primary">Увійти</button>
      </form>
    </div>
  </section>
</template>

<script>
export default {
  name: "LoginPage"
}
</script>

<style scoped>

</style>