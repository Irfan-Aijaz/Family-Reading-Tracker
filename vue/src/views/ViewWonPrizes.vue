<template>
  <div>
      <div class="prize-grid">
          <div class=prize-list>
              <won-prizes-list></won-prizes-list>
          </div>
      </div>
  </div>
</template>

<script>
import WonPrizesList from "../components/WonPrizesList.vue"
export default {
    components: { WonPrizesList }
}
</script>

<style scoped>
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