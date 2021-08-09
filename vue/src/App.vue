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
    <router-view />
  </div>
</template>

<style>
body {
  text-align: center;
  background-color: lightgreen;

}
h1 {
  color: white;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}
</style>