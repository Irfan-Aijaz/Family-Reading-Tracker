<template>
    <div class="form">
        <!-- The form labels for updating a prize-->
        <form class="form-edit-prize" @submit.prevent="updatePrize">
            <h2 class="edit-prize-header">Select a Prize to Update:</h2>
            <div>
                <label for="prize-id" class="sr-only">Prize Name: </label>
                <select
                    id="family-members"
                    name="family-members"
                    v-model="prize.prizeId"
                >
                <option
                    v-for="object in prizeList"
                    v-bind:key="object.prizeId"
                    v-bind:value="object.prizeId"
                >
                    {{ object.prizeName }}
                </option>
                </select>
            </div>
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
                    <option value="Parent">Parent</option>
                    <option value="Child">Child</option>
                    <option value="Both">Both</option>
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
            Update Prize
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
    name: "updated-prize",
    data() {
        return {
            prize: {
                prizeId: '',
                prizeName: '',
                prizeDescription: '',
                milestoneMinutes: '',
                userGroup: '',
                maxPrizes: '',
                dateStart: '',
                dateEnd: ''
            },
            prizeList: [],
            prizeCreationErrors: false,
            prizeCreationErrorMsg: "There were problems creating a prize.",
        };
    },
    methods: {
        updatePrize() {
            prizeService
                .updatePrize(this.prize)
                .then((response) => {
                    if (response.status == 201) {
                        this.$router.push({
                        path: "/prizes",
                        query: { updatePrize: "success" },
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

</style>