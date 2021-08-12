<template>
  <div>
    <!-- The form labels for updating a prize-->
 
    <div class="form">
      <form class="form-edit-prize" @submit.prevent="updatePrizeSelection">
        <h2 class="edit-prize-header">Select a Prize to Update:</h2>
        <div>
          <label for="prize-id" class="sr-only">Prize Name: </label>
          <select id="prize-id" name="prize-id" v-model="prize.prizeId">
            <option
              v-for="prizeObject in prizeList"
              v-bind:key="prizeObject.prizeId"
              v-bind:value="prizeObject.prizeId"
            >
              {{ prizeObject.prizeName }}
            </option>
          </select>
        </div>
        <div>
          <label for="prize-name" class="sr-only">Rename Prize To: </label>
          <input
            type="text"
            id="prize-name"
            class="form-control"
            placeholder="Puppy, Legos, etc."
            v-model="prize.prizeName"
            required
            autofocus
          />
        </div>
        <div>
          <label for="description" class="sr-only">Description: </label>
          <input
            type="text"
            id="description"
            class="form-control"
            placeholder="Write a Description"
            v-model="prize.prizeDescription"
            required
            autofocus
          />
        </div>
        <div>
          <label for="milestone" class="sr-only">Milestone (minutes): </label>
          <input
            type="number"
            id="milestone"
            class="form-control"
            v-model="prize.milestoneMinutes"
          />
        </div>
        <div>
          <label for="group" class="sr-only">User-Group: </label>
          <select id="group" name="group" v-model="prize.userGroup">
            <option value="PARENT">Parent</option>
            <option value="CHILD">Child</option>
            <option value="BOTH">Both</option>
          </select>
        </div>
        <div>
          <label for="max-prizes" class="sr-only">Total Prizes: </label>
          <input
            type="number"
            id="max-prizes"
            class="form-control"
            v-model="prize.maxPrizes"
          />
        </div>
        <div>
          <label for="date-start" class="sr-only">Date Start: </label>
          <input
            type="date"
            id="date-start"
            class="form-control"
            :min="minDate"
            name="datefilter"
            value=""
            v-model="prize.dateStart"
            required
            autofocus
          />
        </div>
        <div>
          <label for="date-end" class="sr-only">Date End: </label>
          <input
            type="date"
            id="date-end"
            class="form-control"
            name="datefilter"
            value=""
            v-model="prize.dateEnd"
            required
            autofocus
          />
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="submit">
          Update Prize
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import prizeService from "@/services/PrizeService";


export default {
  
  name: "updated-prize",
  data() {
    return {
      prize: {
        prizeId: "",
        prizeName: "",
        prizeDescription: "",
        milestoneMinutes: "",
        userGroup: "",
        maxPrizes: "",
        dateStart: "",
        dateEnd: "",
      },
      prizeList: [],
      prizeRequestErrors: false,
      prizeRequestErrorMsg: "There were problems retrieving the prizes.",
      prizeEditingErrors: false,
      prizeEditingErrorMsg: "There were problems editing the prize.",
    };
  },
  methods: {
    updatePrizeSelection() {
      prizeService
        .updatePrize(this.prize)
        .then((response) => {
          if (response.status == 200) {
            this.$router.push({
              path: "/prizes",
              query: { updatePrize: "success" },
            });
          }
        })
        .catch((error) => {
          const response = error.response;
          this.prizeEditingErrors = true;
          if (response.status === 400) {
            this.prizeEditingErrorMsg =
              "Bad Request: Prize Editing Validation Errors";
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
    minDate() {
      var today = new Date();
      var dd = today.getDate();
      var mm = today.getMonth()+1; //January is 0!
      var yyyy = today.getFullYear();
      if(dd<10){
          dd='0'+dd
      } 
      if(mm<10){
          mm='0'+mm
      } 

      today = yyyy+'-'+mm+'-'+dd;
      return today;
    },
  },
  created() {
    this.loadFamilyPrizes();
  },
};
</script>

<style scoped>
.edit-prize-header, .sr-only {
background-color: rgb(194, 191, 191);
border: 3px solid black;
}
</style>