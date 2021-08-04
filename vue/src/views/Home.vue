<template>
  <div class="home">
    <h1>Home</h1>
    <div
      v-if="this.$store.state.user.authorities[0].name == 'ROLE_ADMIN'"
      id="register-family"
      class="text-center"
    >
      <label for="family-name" class="sr-only"
        >Family name: {{ this.$store.state.user.familyName }}
      </label>
     <!-- <register/> -->
      <button v-on:click="$router.push({ name: 'register' })">
        Add Family Member
      </button>
    </div>
  </div>
</template>

<script>
// import Register from "../views/Register.vue";

export default {
  name: "home",
  data() {
    return {
      family: {
        familyName: "",
        id: "",
      },
    };
  },
  // components: {
  //   Register,
  // },
};
</script>
