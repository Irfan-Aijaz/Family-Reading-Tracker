<template>
  <div class="prize-requests">
    <div>
      <table>
        <thead>
          <tr>
            <th></th>
            <th>Claim Id</th>
            <th>Prize Id</th>
            <th>Username</th>
            <th>Status</th>
            <th>Description</th>
            <th>Minutes Completed</th>
            <th>Date Claimed</th>
            <th>Date Approved/Rejected</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="claim in claimList" v-bind:key="claim.claimedPrizeId">
            <td>
              <!-- <input
                type="checkbox"
                v-bind:value="(claim.claimedPrizeId)"
                v-model="checkedClaims"
              /> -->
              <button
                v-on:click="updateClaim(claim.claimedPrizeId, 2, claim.prizeId)"
              >
                Approve
              </button>
              <button
                v-on:click="updateClaim(claim.claimedPrizeId, 3, claim.prizeId)"
              >
                Reject
              </button>
            </td>
            <td>
              {{ claim.claimedPrizeId }}
            </td>
            <td>
              {{ claim.prizeId }}
            </td>
            <td>
              {{ claim.username }}
            </td>
            <td>
              {{ statusText(claim.claimPrizeRequestStatusId) }}
            </td>
            <td>
              {{ claim.description }}
            </td>
            <td>
              {{ claim.milestoneMinutes }}
            </td>
            <td>
              {{ claim.dateClaimed }}
            </td>
            <td>
              {{ claim.dateApprovedRejected }}
            </td>
          </tr>
        </tbody>
      </table>

      <!-- <div class="all-actions">
        <button v-on:click="updateClaims(checkedClaims, 2)">
          Approve Requests
        </button>
        <button v-on:click="updateClaims(checkedClaims, 3)">
          Reject Requests
        </button>
      </div> -->
    </div>
  </div>
</template>

<script>
import prizeService from "@/services/PrizeService";
export default {
  name: "claimList",
  data() {
    return {
      claimList: [],
      checkedClaims: [],
      prizeList: [],
    };
  },
  methods: {
    search(list, id) {
      for (var i = 0; i < list.length; i++) {
        if (list[i].prizeId == id) {
          return list[i].amountAvailable;
        }
      }
    },
    updateClaim(claimId, status, id) {
      if (status == 2) {
        if (this.search(this.prizeList, id) > 0) {
          prizeService.updateClaimStatus(claimId, status).then((response) => {
            this.loadClaims();
            this.loadPrizes();
            console.log(response);
          });
        }
      } else {
          prizeService.updateClaimStatus(claimId, status).then((response) => {
            this.loadClaims();
            this.loadPrizes();
            console.log(response);
          });
      }
    },
    updateClaims(checkedClaims, status) {
      prizeService
        .updateClaimsStatus(checkedClaims, status)
        .then((response) => {
          if (response.status == 200) {
            this.loadClaims();
            console.log(response);
          }
        });
    },
    loadPrizes() {
      prizeService
        .getPrizeClaimCounters(this.$store.state.user.familyId)
        .then((response) => {
          if (response.status == 200) {
            this.prizeList = response.data;
          }
        });
    },
    loadClaims() {
      prizeService
        .getClaimsForFamily(this.$store.state.user.familyId)
        .then((response) => {
          if (response.status == 200) {
            this.claimList = response.data;
          }
        });
    },
    statusText(statusId) {
      if (statusId == 1) {
        return "Pending";
      } else if (statusId == 2) {
        return "Approved";
      } else {
        return "Rejected";
      }
    },
  },
  computed: {},
  created() {
    this.loadClaims();
    this.loadPrizes();
  },
};
</script>

<style scoped>
</style>