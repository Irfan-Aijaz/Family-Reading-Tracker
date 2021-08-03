<template>
  <div class="home">
    <h1>Home</h1>
    <div id="register-family" class="text-center">
      <h3 class="h3 mb-3 font-weight-normal">Register Family</h3>
      <label for="family-name" class="sr-only">Family name: </label>
      <!-- <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      /> -->
    </div>


    <!-- <p>You must be authenticated to see this</p> -->
  </div>
</template>

<script>
export default {
  name: "home",
  data() {
    return {
      family: {
        family_name: "",
        id: ""
      }
    };
  }
};
</script>
