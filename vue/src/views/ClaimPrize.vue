<template>
  <div>
    <form class="form-claim-prize" @submit.prevent="whichClaimToUse">
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
  </div>
</template>

<script>
import prizeService from "@/services/PrizeService";
import sessionService from "@/services/SessionService";

export default {
  name: "claim-prize",
  data() {
    return {
      prizeList: [],
      sessionList: [],
      prizeId: "",
      minutesRead: "",
      minutesRequired: "",
      claimRequestErrors: false,
      claimRequestErrorMsg: "There were problems claiming the prize.",
    };
  },
  methods: {
    loadSessionsForUser() {
      sessionService
        .getSessionsByUserId(this.$store.state.user.id)
        .then((response) => {
          if (response.status == 200) {
            this.sessionList = response.data;
          }
        });
    },
    setMinutesRead(dateEnd, dateStart) {
      sessionService
        .getMinutesReadForClaim(dateStart, dateEnd, this.$store.state.user.id)
        .then((response) => {
          this.minuutesRead = response.data;
        });
    },
    whichClaimToUse() {
      if (this.$store.state.user.authorities[0].name == "ROLE_ADMIN") {
        return this.claimPrizeAdmin();
      } else {
        return this.claimPrize();
      }
    },
    search(list, id) {
      for (var i = 0; i < list.length; i++) {
        if (list[i].prizeId == id) {
          return {
            dateEnd: list[i].dateEnd,
            dateStart: list[i].dateStart,
            minutesRequired: list[i].milestoneMinutes,
          };
        }
      }
    },
    claimPrizeAdmin() {
      prizeService
        .claimPrizeForAdmin(this.prizeId, this.$store.state.user.id)
        .then((response) => {
          if (response.status == 200) {
            this.$router.push({
              path: "/prizes",
              query: { claimPrize: "Claim successful" },
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
    claimPrize() {
      const obj = this.search(this.prizeList, this.prizeId);
      sessionService
        .getMinutesReadForClaim(
          obj.dateStart,
          obj.dateEnd,
          this.$store.state.user.id
        )
        .then((response) => {
          if (response.status == 200) {
            this.minutesRead = response.data.minutes;
            if (this.minutesCurrentlyRequired <= this.minutesRead) {
              prizeService
                .claimPrizeRequestChild(this.prizeId, this.$store.state.user.id)
                .then((response2) => {
                  if (response2.status == 200) {
                    this.$router.push({
                      path: "/prizes",
                      query: { claimPrize: "Claim request sent" },
                    });
                  }
                })
                .catch((error) => {
                  const response2 = error.response2;
                  this.prizeDeletingErrors = true;
                  if (response2.status === 400) {
                    this.prizeDeletingErrorMsg =
                      "Bad Request: Prize Claim Validation Errors";
                  }
                });
            }
          }
        });

      // if (this.minutesCurrentlyRequired <= this.minutesRead) {
      //   prizeService
      //     .claimPrizeRequestChild(this.prizeId, this.$store.state.user.id)
      //     .then((response) => {
      //       if (response.status == 200) {
      //         this.$router.push({
      //           path: "/prizes",
      //           query: { claimPrize: "Claim request sent" },
      //         });
      //       }
      //     })
      //     .catch((error) => {
      //       const response = error.response;
      //       this.prizeDeletingErrors = true;
      //       if (response.status === 400) {
      //         this.prizeDeletingErrorMsg =
      //           "Bad Request: Prize Claim Validation Errors";
      //       }
      //     });
      // }
    },
    loadFamilyPrizesForClaiming() {
      prizeService
        .getPrizesForFamilyUserGroupClaiming(
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
    minutesCurrentlyRequired() {
      const obj = this.search(this.prizeList, this.prizeId);
      return obj.minutesRequired;
    },
    assignUserGroup() {
      if (this.$store.state.user.authorities[0].name == "ROLE_ADMIN") {
        return "PARENT";
      } else {
        return "CHILD";
      }
    },
  },
  created() {
    this.loadFamilyPrizesForClaiming();
    this.loadSessionsForUser();
  },
};
</script>

<style scoped>
.form-claim-prize {
  background-color: rgb(194, 191, 191);
  width: 600px;
  height: 150px;
  border: 3px solid black;
  margin-left: 25%;
}
</style>