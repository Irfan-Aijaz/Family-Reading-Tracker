<template>
  <div is="session" class="text-center">
      <form class="form-session" @submit.prevent="startSession">

           <h1 class="h3 mb-3 font-weight-normal">New Session</h1>
          <div
            class="alert alert-danger"
            role="alert"
            v-if="sessionErrors"
          >
            {{ sessionErrorMsg }}
          </div>
          <label for="isbn" class="sr-only">ISBN: </label>
          <input
            type="text"
            id="isbn"
            class="form-control"
            placeholder="isbn"
            v-model="session.isbn"
            required
            autofocus
          />
          <label for="date" class="sr-only">Date: </label>
          <input
            type="text"
            id="date"
            class="form-control"
            placeholder="date"
            v-model="session.date"
            required
            autofocus
          />
          <label for="time_start" class="sr-only">Time Start: </label>
          <input
            type="text"
            id="time_start"
            class="form-control"
            placeholder="time_start"
            v-model="session.time_start"
            required
            autofocus
          />
          <label for="time_end" class="sr-only">Time End: </label>
          <input
            type="text"
            id="time_end"
            class="form-control"
            placeholder="time_end"
            v-model="session.time_end"
            required
            autofocus
          />

          <label for="format" class="sr-only">Format: </label>
          <input
            type="text"
            id="format"
            class="form-control"
            placeholder="format"
            v-model="session.format"
            required
            autofocus
          />
          <label for="notes" class="sr-only">Notes: </label>
          <input
            type="text"
            id="notes"
            class="form-control"
            placeholder="notes"
            v-model="session.notes"
            required
            autofocus
          />
          <button class="btn btn-lg btn-primary btn-block" type="submit">
            Submit
          </button>
          </form>
            </div>

</template>

<script>
import authService from "../services/AuthService";

export default {
    name: "session",
    data(){
        return{
            session:{
                userId:"",
                isbn:"",
                daySession:"",
                timeStart:"",
                timeEnd:"",
                format:"",
                notes:"",
            },
            sessionErrors: false,
            sessionErrorMsg: "There were problems creating this session.",
        };
    },
    methods:{
        startSession(){
            this.session.userId = this.$store.state.user.userId;
            authService
          .createSession(this.session)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/",
                query: { createSession: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.sessionErrors = true;
            if (response.status === 400) {
              this.sessionErrorMsg = "Bad Request: Validation Errors";
            }
          });
        }
    }
};
</script>

<style>

</style>