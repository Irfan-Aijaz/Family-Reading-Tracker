<!--begin form here to add prizes for books read -->
<template>
  <div class="prizes-view">
    <button v-on:click="$router.push({ name: 'newPrize' })">
            Create a New Prize
    </button>
    <button v-on:click="$router.push({ name: 'updatePrize' })">
            Update Prize
    </button>
    <navigation></navigation>
    
  </div>
</template>

<script>
import Navigation from '../components/Navigation.vue';
export default {
  components: { Navigation },

};
</script>
