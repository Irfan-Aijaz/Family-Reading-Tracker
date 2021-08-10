<!--begin form here to add prizes for books read -->
<template>
  <div class="prize-grid">
    <navigation></navigation>
    <div class="prize-view">
      <button v-on:click="$router.push({ name: 'newPrize' })">
              Create a New Prize
      </button>
      <button v-on:click="$router.push({ name: 'updatePrize' })">
              Update Prize
      </button>
      <family-prize-list></family-prize-list>
    </div>
  </div>
</template>

<script>
import Navigation from '../components/Navigation.vue';
import FamilyPrizeList from '../components/FamilyPrizeList.vue';
export default {
  components: { Navigation, FamilyPrizeList },

};
</script>

<style>
.prize-grid {
  display: grid;
  grid-template-columns: (3, 1fr);
  gap: 1px;
  justify-items: center;
  align-items: start;
}
</style>
