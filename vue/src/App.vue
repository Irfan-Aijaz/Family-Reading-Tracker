<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <h2>Family Reading Book Tracker</h2>
    </div>
    <router-view />
  </div>
</template>

<style>
body{
  text-align: center;
  background-color:lightgreen;
}
h2{
  color:white;
}
</style>