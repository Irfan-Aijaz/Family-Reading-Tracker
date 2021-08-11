<template>
  <div id="app">
    <div id="nav">
      <div class="logo">
        <img
          src="https://files.slack.com/files-pri/T0GNFLF6D-F02AX9HUZMH/image.png"
        />
      </div>
      <div>
      <router-link v-bind:to="{ name: 'home' }" v-if="$store.state.token != ''"
        >Home | </router-link
      >&nbsp;&nbsp;
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
      >
        Logout</router-link
      >
      </div>
    </div>
    <h1>Oracle Reading Tracker</h1>
    <navigation v-if="$store.state.token != ''" />
    <router-view />
  </div>
</template>

<script>
import Navigation from "./components/Navigation.vue";

export default {
  name: "App",
  components: {
    navigation: Navigation,
  },
};
</script>

<style>
div#nav {
  display: flex;
  justify-content:flex-start;
}
body {
  text-align: center;
  background-color: rgb(139, 139, 139);
  background-image: url("https://c.tenor.com/vDtqjv47FsIAAAAC/book-school.gif");
  background-repeat: no-repeat;
  background-size: cover;
}
h1 {
  color: black;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}
.logo img {
  width: 100px;
  height: 100px;
}
</style>