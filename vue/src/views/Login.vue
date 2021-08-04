<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <ul>
        <li>
          <h2 class="h3 mb-3 font-weight-normal">Please Sign In</h2>
          <div
            class="alert alert-danger"
            role="alert"
            v-if="invalidCredentials"
          >
            Invalid username and password!
          </div>
          <div
            class="alert alert-success"
            role="alert"
            v-if="this.$route.query.registration"
          >
            Thank you for registering, please sign in.
          </div>
        </li>
        <li>
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
        </li>
        <li>
          <label for="password" class="sr-only">Password: </label>
          <input
            type="password"
            id="password"
            class="form-control"
            placeholder="Password"
            v-model="user.password"
            required
          />
        </li>
        <li>
          <button type="submit">Sign in</button>
        </li>
        <li>
          <router-link :to="{ name: 'register' }">Need an account?</router-link>
        </li>
      </ul>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .setFamily(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_FAMILY", response.data.user);
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style>
.login {
  display: flex;
  justify-content: center;
  justify-items: center;
  align-items: center;
  text-align: center;
  background-color: lightgreen;
}
h2 {
  color: black;
}
ul {
  list-style-type: none;
}
li {
  margin-top: 10px;
}
</style>
