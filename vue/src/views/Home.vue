<template>
  <div class="home">
    <h1>Home</h1>
    <div
      v-if="this.$store.state.user.authorities[0].name == 'ROLE_ADMIN'"
      id="register-family"
      class="text-center"
    >
      <ul>
        <li>
          <label for="family-name" class="sr-only"
            >Family name: {{ this.$store.state.user.familyName }}
          </label>
        </li>
        <!-- <register/> -->
        <!-- Add list of family member usernames here -->
        <li>
          <label for="family-members" class="sr-only"
            >Family members: {{ this.$store.state.user.userName }}
          </label>
        </li>
        <li>
          <button v-on:click="$router.push({ name: 'register' })">
            Add Family Member
          </button>
        </li>
      </ul>
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
        familyid: "",
        familyName: "",
      },
    };
  },
  // components: {
  //   Register,
  // },
};
</script>
<style scoped>
.familyName {
  text-decoration-color: blanchedalmond;
}
</style>
