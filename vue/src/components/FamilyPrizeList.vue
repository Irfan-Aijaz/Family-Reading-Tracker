<template>
  <div class="Prizes">
      <div>
          <table>
              <thead>
                  <tr>
                      <th>Prize Name</th>
                      <th>Description</th>
                      <th>Minutes Required</th>
                      <th>User Group</th>
                      <th>Quantity</th>
                      <th>Date Start</th>
                      <th>Date End</th>
                  </tr>
              </thead>
              <tbody>
                  <tr v-for="prize in prizeList"  v-bind:key="prize.prizeId">
                      <td>
                          {{ prize.prizeName }}
                      </td>
                      <td>
                          {{ prize.prizeDescription }}
                      </td>
                      <td>
                          {{ prize.milestoneMinutes }}
                      </td>
                      <td>
                          {{ prize.userGroup }}
                      </td>
                      <td>
                          {{ prize.maxPrizes }}
                      </td>
                      <td>
                          {{ prize.dateStart }}
                      </td>
                      <td>
                          {{ prize.dateEnd }}
                      </td>
                  </tr>
              </tbody>
          </table>
      </div>
  </div>
</template>

<script>
import prizeService from "@/services/PrizeService";


export default {
    name: "prizeList",
    data() {
        return {
            prizeList: [],
            userGroup: '',
            prizeRequestErrors: false,
            prizeRequestErrorMsg: "There were problems retrieving the prizes.",
        }

    },
    methods: {
        loadFamilyPrizes() {
            prizeService
                .getPrizesForFamilyUserGroup(this.$store.state.user.familyId, this.assignUserGroup)
                .then((response) => {
                    if (response.status == 200) {
                        this.prizeList = response.data;
                    }
                })
                .catch((error) => {
                    const response = error.response;
                    this.prizeRequestErrors = true;
                    if (response.status === 400) {
                        this.prizeRequestErrorMsg = "Bad Request: Prize Request Validation Errors";
                    }
                })
        }
    },
    computed: {
        assignUserGroup() {
            if (this.$store.state.user.authorities[0].name == 'ROLE_ADMIN') {
                return 'PARENT';
            } else {
                return 'CHILD';
            }
        } 
    },
    created() {
        this.loadFamilyPrizes();
    }
}
</script>

<style>
.prizes{
  background-color: rgb(194, 191, 191);
}
</style>