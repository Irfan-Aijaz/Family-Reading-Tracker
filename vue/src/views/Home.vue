<template>
  <div class="home">
    <h1>Home</h1>
    <div
      class="alert alert-success"
      role="alert"
      v-if="this.$route.query.bookregistration"
    >
      Book added successfully
    </div>
    <!--code for parent account starts here -->
    <div
      v-if="this.$store.state.user.authorities[0].name == 'ROLE_ADMIN'"
      id="register-family"
      class="text-center"
    >
      <div class="family-name">
        <label for="family-name" class="sr-only"
          >Welcome {{ this.$store.state.user.familyName }} Family!
        </label>
      </div>
      <!-- Add list of family member usernames here -->
      <div class="family-members">
        <label
          for="family-members"
          class="sp-only"
          v-for="name in familyMembers"
          v-bind:key="name"
          >{{ name }}
        </label>
      </div>
      <!--End parent/admin code, start child user code -->
      <div
        v-if="this.$store.state.user.authorities[0].name == 'ROLE_USER'"
        class="text-center"
      >
        <div class="child-welcome">
          <label for="family-name" class="sr-only"
            >Welcome {{ this.$store.state.user.user }}!
          </label>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "home",
  data() {
    return {
      familyMembers: [],
    };
  },
  methods: {
    family() {
      authService
        .getFamily(this.$store.state.user.familyId)
        .then((response) => {
          if (response.status == 200) {
            this.familyMembers = response.data;
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            // this.invalidCredentials = true;
          }
        });
    },
  },
  created() {
    this.family();
  },
};
</script>
<style scoped>
.family-name {
  color: rgb(0, 0, 0);
  font-size: 50px;
  top: 25%;
  background-color: grey;
  width: 90%;
  margin: 0 auto;
}
.child-welcome {
  color: rgb(0, 0, 0);
  font-size: 50px;
  top: 25%;
  background-color: grey;
  width: 90%;
  margin: 0 auto;
}
.family-members {
  display: grid;
  display: 1fr;
  color: rgb(132, 181, 236);
  font-size: 50px;
  background-color: ivory;
  font-size: 50px;
  top: 25%;
  width: 90%;
  margin: 0 auto;
}
.something {
  align-content: center;
}
</style>