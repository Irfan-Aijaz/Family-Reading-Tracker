<!--begin form here to add prizes for books read -->
<template>
  <div>
    <div class="prize-grid">
      <div class="prize-view">
        <button v-on:click="$router.push({ name: 'newPrize' })">
          Create a New Prize
        </button>
        <button v-on:click="$router.push({ name: 'updatePrize' })">
          Update Prize
        </button>
        <button v-on:click="$router.push({ name: 'deletePrize' })">
          Delete Inactive Prize
        </button>
        <button v-on:click="$router.push({ name: 'claimPrize' })">
          Claim Prize
        </button>
      </div>

      <div class="prize-list">
        <family-prize-list></family-prize-list>
      </div>
    </div>
  </div>
</template>

<script>
import FamilyPrizeList from "../components/FamilyPrizeList.vue";
export default {
  components: { FamilyPrizeList },
};
</script>

<style>
.prize-grid {
  display: grid;
  grid-template-columns: (3, 1fr);
  gap: 1px;
  justify-items: center;
  align-items: start;
  background-color: rgb(194, 191, 191);
  border: 3px solid black;
}
.prize-list:hover {
  background-color: #ffffff;
  cursor: pointer;
}
</style>
