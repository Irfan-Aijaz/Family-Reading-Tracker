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
              <input
                type="checkbox"
                v-bind:value="claim.claimedPrizeId"
                v-model="checkedClaims"
              />
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

      <div class="all-actions">
        <button v-on:click="updateClaims(checkedClaims, 2)">
          Approve Requests
        </button>
        <button v-on:click="updateClaims(checkedClaims, 3)">Reject Requests</button>
      </div>
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
    };
  },
  methods: {
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
  },
};
</script>

<style scoped>
</style>