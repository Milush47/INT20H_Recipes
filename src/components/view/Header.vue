<template>
  <header>
    <div class="header-wrapper">
<!--      <img class="logo" src="../assets/logo.png" alt="logo" />-->
      <nav>
        <ul>
          <li>
            <RouterLink to="/">Головна</RouterLink>
          </li>
          <li>
            <router-link to="/profile">Мої рецепти</router-link>
          </li>
          <li>
            <router-link to="/profile">Мої продукти</router-link>
          </li>
        </ul>
      </nav>
    </div>
  </header>
</template>

<script>
export default {
  name: "Header"
}
</script>

<style scoped>

</style>