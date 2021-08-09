<!--begin form here to add prizes for books read -->
<template>
  <div class="prizes-view">
    <new-prize></new-prize>
    <div>
      <div
        v-if="this.$store.state.user.authorities[0].name == 'ROLE_ADMIN'"
        id="register-family"
        class="text-center"
      >
        <div class="parentOptions">
          <button v-on:click="$router.push({ name: 'register' })">
            Add Family Member
          </button>
          <button v-on:click="$router.push({ name: 'newBook' })">
            Add Book
          </button>
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
          <button v-on:click="$router.push({ name: 'prizes' })">Prizes</button>
        </div>
      </div>
      <div
        v-if="this.$store.state.user.authorities[0].name == 'ROLE_USER'"
        class="text-center"
      >
        <div class="childOptions">
          <button v-on:click="$router.push({ name: 'newBook' })">
            Add Book
          </button>
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
          <button v-on:click="$router.push({ name: 'prizes' })">Prizes</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import NewPrize from "@/components/NewPrize";

export default {
  components: {
    NewPrize,
  },
};
</script>
