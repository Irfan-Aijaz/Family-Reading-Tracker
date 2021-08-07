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
      <label for="family-name" class="sr-only"
        >Family Name: {{ this.$store.state.user.familyName }}
      </label>
      <!-- Add list of family member usernames here -->
      <label
        for="family-members"
        class="sr-only"
        v-for="name in familyMembers"
        v-bind:key="name"
        >Family Member: {{ name }}
      </label>
      <!--start app button choices here -->
      <div class="parentOptions">
        <button v-on:click="$router.push({ name: 'register' })">
          Add Family Member
        </button>
        <button v-on:click="$router.push({ name: 'newBook' })">Add Book</button>
        <button v-on:click="$router.push({ name: 'session' })">
          Record Session
        </button>
        <button v-on:click="$router.push({ name: 'inProgress' })">
          In Progress
        </button>
        <button v-on:click="$router.push({ name: 'completed' })">
          Completed
        </button>
        <button v-on:click="$router.push({ name: 'trackFamilyProgress' })">
          Track Family Progress
        </button>
        <button v-on:click="$router.push({ name: 'prizes' })">Prizes</button>
      </div>
    </div>
    <!--End parent/admin code, start child user code -->
    <div
      v-if="this.$store.state.user.authorities[0].name == 'ROLE_USER'"
      class="text-center"
    >
      <label for="family-name" class="sr-only"
        >Family name: {{ this.$store.state.user.familyName }}
      </label>
      <!--start app button choices here -->
      <div class="childOptions">
        <button v-on:click="$router.push({ name: 'newBook' })">Add Book</button>
        <button v-on:click="$router.push({ name: 'inProgress' })">
          In Progress
        </button>
        <button v-on:click="$router.push({ name: 'session' })">
          Record Session
        </button>
        <button v-on:click="$router.push({ name: 'view completed sessions'})">
          View Reading Activity
        </button>
        <button v-on:click="$router.push({ name: 'completed' })">
          Completed
        </button>
        <button v-on:click="$router.push({ name: 'prizes' })">Prizes</button>
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
.text-center {
  display: grid;
  grid-template-columns: 1fr;
  gap: 10px;
  justify-items: center;
  align-items: start;
  font-size: 25px;
  text-decoration-color: cornsilk;
}
.parentOptions {
  display: grid;
  grid-template-columns: 1fr;
  gap: 0px;
  justify-items: center;
  align-items: start;
  position: absolute;
  top: 10px;
  left: 0px;
  font-size: 25px;
  text-decoration-color: cornsilk;
  border: 2px solid black;
  border-radius: 10px;
  width: 200px;
  height: 300px;
  margin: 10px;
}
.childOptions {
  display: grid;
  grid-template-columns: 1fr;
  gap: 10px;
  justify-items: center;
  align-items: start;
  position: absolute;
  top: 20px;
  left: 10px;
  font-size: 25px;
  text-decoration-color: cornsilk;
  border: 2px solid black;
  border-radius: 10px;
  width: 250px;
  height: 500px;
  margin: 30px;
}
.familyName {
  text-decoration-color: blanchedalmond;
  font-size: 40px;
}
.sr-only{
  text-decoration-color: cornsilk;
  
}
</style>
