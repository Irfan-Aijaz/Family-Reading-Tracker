<template>
  <div>
      <form class="form-claim-prize" @submit.prevent="claimPrize">
          <h2 class="claim-prize-header">Select a Prize to Claim:</h2>
          <div>
              <label for-"prize-id" class="sr-only">Prize Name: </label>
              <select
                id="prize-id"
                name="prize-id"
                v-model="prizeId">
                <option
                            v-for="prizeObject in prizeList"
                            v-bind:key="prizeObject.prizeId"
                            v-bind:value="prizeObject.prizeId"
                        >
                            {{ prizeObject.prizeName }}
                        </option>
                    </select>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    Delete
                </button>
      </form>
      <navigation></navigation>
  </div>

</template>

<script>
import prizeService from "@/services/PrizeService";
import Navigation from '../components/Navigation.vue';

export default {
    components: { Navigation },
    name: "claim-prize",
    data() {
        return {
            prizeList:[],
            prizeId: '',
            claimRequestErrors: false,
            claimRequestErrorMsg: "There were problems claiming the prize.",
        };
    },
    methods: {
        claimPrize(){}
    }
};
</script>

<style>
</style>