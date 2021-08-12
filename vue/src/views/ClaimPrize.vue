<template>
  <div>
    <form class="form-claim-prize" @submit.prevent="claimPrize">
      <h2 class="claim-prize-header">Select a Prize to Claim:</h2>
      <div>
        <label for="prize-id" class="sr-only">Prize Name: </label>
        <select id="prize-id" name="prize-id" v-model="prizeId">
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
        Claim Prize
      </button>
    </form>
    <family-prize-list></family-prize-list>
    
  </div>
</template>

<script>
import FamilyPrizeList from "../components/FamilyPrizeList.vue";
import prizeService from "@/services/PrizeService";


export default {
  components: { FamilyPrizeList },
  name: "claim-prize",
  data() {
    return {
      prizeList: [],
      prizeId: "",
      claimRequestErrors: false,
      claimRequestErrorMsg: "There were problems claiming the prize.",
    };
  },
  methods: {
    claimPrize() {
      prizeService
        .claimPrizeRequestChild(this.prizeId, this.$store.state.user.id)
        .then((response) => {
          if (response.status == 200) {
            this.$router.push({
              path: "/prizes",
              query: { claimPrize: "Claim request sent" },
            });
          }
        })
        .catch((error) => {
          const response = error.response;
          this.prizeDeletingErrors = true;
          if (response.status === 400) {
            this.prizeDeletingErrorMsg =
              "Bad Request: Prize Claim Validation Errors";
          }
        });
    },
    loadFamilyPrizes() {
      prizeService
        .getPrizesForFamilyUserGroup(
          this.$store.state.user.familyId,
          this.assignUserGroup
        )
        .then((response) => {
          if (response.status == 200) {
            this.prizeList = response.data;
          }
        })
        .catch((error) => {
          const response = error.response;
          this.prizeRequestErrors = true;
          if (response.status === 400) {
            this.prizeRequestErrorMsg =
              "Bad Request: Prize Request Validation Errors";
          }
        });
    },
  },
  computed: {
    assignUserGroup() {
      if (this.$store.state.user.authorities[0].name == "ROLE_ADMIN") {
        return "PARENT";
      } else {
        return "CHILD";
      }
    },
  },
  created() {
    this.loadFamilyPrizes();
  },
};
</script>

<style scoped>
.form-claim-prize{
  background-color: rgb(194, 191, 191);
  width: 600px;
  height: 150px;
  border: 3px solid black;
  margin-left: 25%;
}
</style>