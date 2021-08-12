<template>
  <div class="claim-grid">
    <div class="claim-list">
      <prize-request-list></prize-request-list>
    </div>
  </div>
</template>

<script>
import PrizeRequestList from "../components/PrizeRequestList.vue";
export default {
  components: { PrizeRequestList },
};
</script>

<style>
.claim-grid {
  display: grid;
  grid-template-columns: (3, 1fr);
  gap: 1px;
  justify-items: center;
  align-items: start;
  background-color: rgb(194, 191, 191);
  border: 3px solid black;
}

</style>