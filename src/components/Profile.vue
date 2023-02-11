<template>
  <div class="wrapper">
    <div class="profile">
      <h2 class="title">Профіль</h2>
      <div class="profile__info">
        <div class="profile__info__text">
          <h2 class="profile__info__text__name">Іванов Іван Іванович</h2>
          <p class="profile__info__text__email"></p>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Profile",
  data: () => ({
    user: "",
    fields: [
      { name: "firstname", value: "firstname" },
      { name: "lastname", value: "lastname" },
      { name: "email", value: "email" },
    ],
    link: "user",
  })
  }
</script>

<style scoped>

</style>