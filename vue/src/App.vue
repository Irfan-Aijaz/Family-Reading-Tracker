<template>
  <div id="app">
    <header class="header">
      <div>
        <img src="../src/images/logo.png" />
      </div>

      <div class="thing">
        <router-link
          v-bind:to="{ name: 'home' }"
          v-if="$store.state.token != ''"
          >Home | </router-link
        >&nbsp;&nbsp;
        <router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
        >
          Logout</router-link
        >
      </div>
      <div></div>
    </header>
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
header.header {
  display: flex;
  justify-content: space-between;
}

header.header > * {
  flex: 1;
}

header.header img {
  max-width: 100px;
  float: left;
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