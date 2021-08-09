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
      <!--start app button choices here -->
      <div class="parentOptions">
        <button v-on:click="$router.push({ name: 'register' })">
          Add Family Member
        </button>
        <button v-on:click="$router.push({ name: 'newBook' })">
          Add Book</button>
        <button v-on:click="$router.push({ name: 'newSession' })">
          Record Session
        </button>
        <button v-on:click="$router.push({ name: 'inProgress', params: {id: $store.state.user.id} })">
          In Progress
        </button>
        <button v-on:click="$router.push({ name: 'completed' })">
          Completed
        </button>
        <button v-on:click="$router.push({ name: 'viewSessionsHistory' })">
          Track Family Progress
        </button>
        <button v-on:click="$router.push({ name: 'prizes' })">
          Prizes</button>
      </div>
    </div>
    <!--End parent/admin code, start child user code -->
    <div
      v-if="this.$store.state.user.authorities[0].name == 'ROLE_USER'"
      class="text-center"
    >
    <div class="child-welcome">
      <label for="family-name" class="sr-only"
        > Welcome {{ this.$store.state.user.username }}!
      </label>
     </div> 
      <!--start app button choices here -->
      <div class="childOptions">
        <button v-on:click="$router.push({ name: 'newBook' })">
          Add Book</button>
        <button v-on:click="$router.push({ name: 'inProgress' })">
          In Progress
        </button>
        <button v-on:click="$router.push({ name: 'newSession' })">
          Record Session
        </button>
        <button v-on:click="$router.push({ name: 'viewSessionsHistory'})">
          View Reading Activity
        </button>
        <button v-on:click="$router.push({ name: 'completed' })">
          Completed
        </button>
        <button v-on:click="$router.push({ name: 'prizes' })">
          Prizes</button>
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
  color: cornsilk;
  margin-bottom: 10%;

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
  border: 2px solid rgb(139, 203, 232);;
  border-radius: 15px;
  padding-top:15px;
  width: 200px;
  height: 500px;
  margin: 10px;
  margin-bottom: 10%;
}
.childOptions {
  display: grid;
  grid-template-columns: 1fr;
  gap: 0px;
  justify-items: center;
  align-items: start;
  position: absolute;
  top: 10px;
  left: 0px;
  font-size: 25px;
  border: 2px solid rgb(139, 203, 232);;
  border-radius: 15px;
  padding-top:15px;
  width: 200px;
  height: 500px;
  margin: 10px;
  margin-bottom: 10%;
}
.family-name{
  color: rgb(139, 187, 232);;
  font-size: 50px;
  margin-bottom: 5%;

}
.child-welcome{
  color: rgb(139, 187, 232);;
  font-size: 50px;
  margin-bottom: 5%;

}
.family-members{
  color: rgb(139, 182, 232);;
  font-size: 30px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  flex-wrap: wrap;

}
</style>
