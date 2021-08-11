<template>
  <div id="register" class="text-center">
    <div class="blah">
      <navigation> </navigation>
      <div>
        <form class="form-register" @submit.prevent="register">
          <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
          <div
            class="alert alert-danger"
            role="alert"
            v-if="registrationErrors"
          >
            {{ registrationErrorMsg }}
          </div>
          <div v-if="this.$store.state.token == ''" class="form">
            <label for="family" class="sr-only"> Family Name: </label>
            <input
              type="family"
              id="family"
              class="form-control"
              placeholder="family-name"
              v-model="user.familyName"
              required
            />
          </div>

          <label for="username" class="sr-only">Username: </label>
          <input
            type="text"
            id="username"
            class="form-control"
            placeholder="Username"
            v-model="user.username"
            required
            autofocus
          />

          <label for="password" class="sr-only"> Password: </label>
          <input
            type="password"
            id="password"
            class="form-control"
            placeholder="Password"
            v-model="user.password"
            required
          />

          <input
            type="password"
            id="confirmPassword"
            class="form-control"
            placeholder="Confirm Password"
            v-model="user.confirmPassword"
            required
          />

          <div v-if="this.$store.state.token != ''">
            <input
              type="radio"
              id="Parent"
              value="admin"
              name="role"
              v-model="user.role"
            />
            <label for="one">Parent</label>
            <input
              type="radio"
              id="Child"
              value="user"
              name="role"
              v-model="user.role"
            />
            <label for="two">Child</label>
          </div>

          <button class="btn btn-lg btn-primary btn-block" type="submit">
            Create Account
          </button>

          <router-link
            :to="{ name: 'login' }"
            v-if="this.$store.state.token == ''"
            >Have an account?</router-link
          >
        </form>
      </div>
      <!-- <div class="picture">
        <img
          src="https://static.independent.co.uk/s3fs-public/thumbnails/image/2019/01/30/15/family-guy.jpg?width=1200"
        />
      </div> -->
    </div>
  </div>
</template>

<script>
import Navigation from "../components/Navigation.vue";
import authService from "../services/AuthService";

export default {
  components: { Navigation },
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        familyName: "",
        familyId: "",
        role: "",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else if (this.$store.state.token != "") {
        this.user.familyId = this.$store.state.user.familyId;
        authService
          .adminRegister(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      } else {
        this.user.role = "admin";
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style>
.form-register {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
}

div {
  background-color: grey;
  text-align: center;
}
.confirmPassword {
  align-content: center;
}
.blah {
  left: 40%;
}
.picture img {
  width: 300px;
  height: 300px;
  position: absolute;
  right: 5%;
  top: 15%;
}
#sr-only {
  align-content: center;
  left: 50%;
}
</style>
