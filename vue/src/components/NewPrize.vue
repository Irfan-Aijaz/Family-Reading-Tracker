<template>
    <div class="new-prize-grid">
        <!-- The form labels for adding a new prize-->
        <navigation></navigation>
        <div class="form-div">
            <form class="form-add-prize" @submit.prevent="createPrize">
                <h2 class="new-prize-title">Add Prize to Prizes</h2>
                <div>
                    <label for="prize-name" class="sr-only">Prize Name: </label>
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
                Add Prize
                </button>
            </form>
        </div>
        
        
    </div>
</template>

<script>
import prizeService from "@/services/PrizeService";
import Navigation from '@/components/Navigation.vue';

export default {
    
    components: { Navigation },
    name: "new-prize",
    data() {
        return {
            prize: {
                prizeName: '',
                prizeDescription: '',
                milestoneMinutes: '',
                userGroup: '',
                maxPrizes: '',
                dateStart: '',
                dateEnd: '',
                familyId: ''
            },
            prizeCreationErrors: false,
            prizeCreationErrorMsg: "There were problems creating a prize.",
        };
    },
    methods: {
        createPrize() {
            this.prize.familyId = this.$store.state.user.familyId;
            prizeService
                .createPrize(this.prize)
                .then((response) => {
                    if (response.status == 201) {
                        this.$router.push({
                        path: "/prizes",
                        query: { createPrize: "success" },
                        });
                    }
                })
                .catch((error) => {
                    const response = error.response;
                    this.prizeCreationErrors = true;
                    if (response.status === 400) {
                        this.prizeCreationErrorMsg = "Bad Request: Prize Creation Validation Errors";
                    }
                });
        }
    }

}
</script>

<style>
.form-div {
  display: grid;
  grid-template-columns: 1fr;
  align-items: center;
}
</style>