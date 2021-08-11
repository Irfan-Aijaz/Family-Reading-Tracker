
<template>
  <div class="navigation">
    <div
      v-if="this.$store.state.user.authorities[0].name == 'ROLE_ADMIN'"
      id="register-family"
      class="text-center"
    >
      <div class="parent-options">
        <button v-on:click="$router.push({ name: 'register' })">
          Add Family Member
        </button>
        <button v-on:click="$router.push({ name: 'newBook' })">Add Book</button>
        <button v-on:click="$router.push({ name: 'newSession' })">
          Record Session
        </button>
        <button
          v-on:click="
            $router.push({
              name: 'inProgress',
              params: { id: $store.state.user.id },
            })
          "
        >
          In Progress
        </button>
        <button v-on:click="$router.push({ name: 'completed' })">
          Completed
        </button>
        <button v-on:click="$router.push({ name: 'viewSessionsHistory' })">
          Track Family Progress
        </button>
        <button v-on:click="$router.push({ name: 'prizes' })">
          View Available Prizes
          </button>
          <button>
            View Claim Prize Requests 
            </button>
      </div>
    </div>

    <div
      v-if="this.$store.state.user.authorities[0].name == 'ROLE_USER'"
      class="text-center"
    >
      <div class="child-options">
        <button v-on:click="$router.push({ name: 'newBook' })">Add Book</button>
        <button v-on:click="$router.push({ name: 'inProgress' })">
          In Progress
        </button>
        <button v-on:click="$router.push({ name: 'newSession' })">
          Record Session
        </button>
        <button v-on:click="$router.push({ name: 'viewSessionsHistory' })">
          View Reading Activity
        </button>
        <button v-on:click="$router.push({ name: 'completed' })">
          Completed
        </button>
        <button v-on:click="$router.push({ name: 'prizes' })">
          View Available Prizes
        </button>

      </div>
    </div>
  </div>
</template>

<style scoped>
/*.parent-options {
  border: 5px solid black;
  display: grid;
  grid-template-columns: 1fr;
  height: 500px;
  width: 175px;
  margin-left: 10%;
  gap: 40px;
  position: absolute;
  top: 5%;
}
.child-options {
  border: 2px solid black;
  display: grid;
  grid-template-columns: 1fr;
  height: 500px;
  width: 175px;
  margin-left: 10%px;
  gap: 40px;
  position: absolute;
  top: 5%;
}
*/
.navigation {
  margin-bottom: 10px;
}
.parent-options, .child-options {
  border: 2px solid black;
  border-radius: 10px;
  width: 90%;
  margin: 0 auto;
  height: 50px;
}
button {
  height: 50px;
  margin-left: 5px;
  margin-right: 5px;
}
</style>
