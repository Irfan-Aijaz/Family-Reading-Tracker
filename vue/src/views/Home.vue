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
        <!-- Add list of family member usernames here -->
        
          <label for="family-members" class="sr-only" v-for="name in updatedFamilyMembers" v-bind:key="name"
            >Family members: {{ name }}
          </label>
        
          <button v-on:click="$router.push({ name: 'register' })">
            Add Family Member
          </button>
       
    </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';
// import Register from "../views/Register.vue";

export default {
  name: "home",
  data() {
    return {
      familyMembers: []
    };
  },
  computed: {
    updatedFamilyMembers() {
      return this.family;
    }
  },
  methods: {
    family() {
      authService
        .getFamily(this.$store.state.user.familyId)
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            // this.invalidCredentials = true;
          }
        });
    }

  }
  // components: {
  //   Register,
  // },
};
</script>
<style scoped>
.text-center {
  display: grid;
  grid-template-columns: 1fr;
  gap: 5px;
  justify-items: center;
}
.familyName {
  text-decoration-color: blanchedalmond;
}
</style>
