<template>
<div class="Prizes">
  <div>
    <table>
      <thead>
        <tr>
          <!-- <th>Prize Name</th> -->
          
          <th>Description</th>
          <th>Date Won</th>
          <th v-if="this.$store.state.user.authorities[0].name == 'ROLE_ADMIN'">Username </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="prize in wonPrizeList" v-bind:key="prize.claimedPrizeId">
          <!-- <td>Name</td> -->
          <td>{{ prize.description }}</td>
          <td>{{ prize.dateApprovedRejected }}</td>
          <td> {{prize.username}}</td>
        </tr>
      </tbody>
    </table>
  </div>
  </div>
</template>

<script>
import prizeService from "@/services/PrizeService";
export default {
  name: "wonPrizeList",
  data() {
    return {
      wonPrizeList: [],
    };
  },
  methods: {
    loadPrizeListForUser() {
      prizeService
        .getWonPrizesForUser(this.$store.state.user.id)
        .then((response) => {
          if (response.status == 200) {
            this.wonPrizeList = response.data;
          }
        });
    },
    loadPrizeListForAdmin() {
      prizeService
        .getWonPrizesForFamily(this.$store.state.user.familyId)
        .then((response) => {
          if (response.status == 200) {
            this.wonPrizeList = response.data;
          }
        });
    },
  },
  created() {
    if (this.$store.state.user.authorities[0].name == "ROLE_USER") {
      this.loadPrizeListForUser();
    } else {
      this.loadPrizeListForAdmin();
    }
  },
};
</script>

<style>
.prizes {
  background-color: rgb(194, 191, 191);
}
.prizes:hover {
  background-color: #ffffff;
  cursor: pointer;
}
</style>