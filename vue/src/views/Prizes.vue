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
    <family-prize-list></family-prize-list>
    
  </div>
</template>

<script>
import Navigation from '../components/Navigation.vue';
import FamilyPrizeList from '../components/FamilyPrizeList.vue';
export default {
  components: { Navigation, FamilyPrizeList },

};
</script>
