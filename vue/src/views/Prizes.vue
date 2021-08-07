<!--begin form here to add prizes for books read -->
<template>
  <div class="prizes">
    <h2 class="prize-name">Add Prize</h2>
    <label for="prize-name" class="sr-only"> Prize: </label>
    <input
      type="text"
      id="prize"
      class="form-control"
      placeholder="Prize"
      v-model="prize.name"
      required
      autofocus
    />

    <label for="to-redeem" class="sr-only"> To Redeem: </label>
    <input
      type="text"
      id="to-redeem"
      class="form-control"
      placeholder="To Redeem"
      v-model="prize.cost"
      required
      autofocus
    />

  </div>
</template>