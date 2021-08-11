<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }" v-if="$store.state.token != ''"
        >Home | </router-link
      >&nbsp;&nbsp;
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
      >
        Logout</router-link
      >
      <ul>
        <li><h1>Family Reading Book Tracker</h1></li>
      </ul>
    </div>
    <navigation
      v-if="$store.state.token != ''"
    />
    <router-view />
  </div>
</template>

<script>
import Navigation from "./components/Navigation.vue";

export default {
 name: "App",
 components: {
   'navigation': Navigation
 }
};
</script>

<style>
body {
  text-align: center;
  background-color: rgb(139, 139, 139);
}
h1 {
  color: black;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}
</style>