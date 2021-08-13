<template>
    <div>
        <form class="form-edit-prize" @submit.prevent="deletePrize">
            <h2 class="edit-prize-header">Select an Inactive Prize to Delete:</h2>
                <div>
                    <label for="prize-id" class="sr-only">Prize Name: </label>
                        <select
                            id="prize-id"
                            name="prize-id"
                            v-model="prizeId"
                        >
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
        
    </div>
</template>

<script>
import prizeService from "@/services/PrizeService";


export default {
   
    name: "deleted-prize",
    data() {
        return {
            prizeList: [],
            prizeId: '',
            prizeRequestErrors: false,
            prizeRequestErrorMsg: "There were problems retrieving the prizes.",
            prizeDeletingErrors: false,
            prizeDeletingErrorMsg: "There were problems deleting the prize.",
        };
    },
    methods: {
        deletePrize() {
            prizeService
                .deleteInactivePrize(this.prizeId)
                .then((response) => {
                    if (response.status == 200) {
                        this.$router.push({
                        path: "/prizes",
                        query: { deletePrize: "success" },
                        });
                    }
                })
                .catch((error) => {
                    const response = error.response;
                    this.prizeDeletingErrors = true;
                    if (response.status === 400) {
                        this.prizeDeletingErrorMsg = "Bad Request: Prize Deletion Validation Errors";
                    }
                });
        },
        loadFamilyPrizes() {
            prizeService
                .getPrizesForFamily(this.$store.state.user.familyId)
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

<style scoped>
.form-edit-prize{
background-color: rgb(194, 191, 191);
border: 3px solid black;
}
</style>