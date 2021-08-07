<template>
  <div id="session" class="text-center">
      <form class="form-session" @submit.prevent="newSession">

           <h1 class="h3 mb-3 font-weight-normal">New Session For: {{ this.$store.state.user.username}}</h1>
          <div
            class="alert alert-danger"
            role="alert"
            v-if="sessionErrors"
          >
            {{ sessionErrorMsg }}
          </div>
          <div>
            <!--The form inputs for recording a session -->
          <label for="isbn" class="sr-only">ISBN: </label>
          <input
            type="text"
            id="isbn"
            class="form-control"
            placeholder="ISBN"
            v-model="session.isbn"
            required
            autofocus
          />
          </div>
          <div>
          <label for="date" class="sr-only">Date: </label>
          <input
            type="date"
            id="date"
            class="form-control"
            placeholder="Date"
            v-model="session.daySession"
            required
            autofocus
          />
          </div>
          <div>
          <label for="pages" class="sr-only">Pages Read: </label>
          <input
            type="text"
            id="pages"
            class="form-control"
            placeholder="Pages Read"
            v-model="session.pagesRead"
            required
            autofocus
          />
          </div>
          <div>
          <label for="time_start" class="sr-only">Time Start: </label>
          <input
            type="time"
            id="time_start"
            class="form-control"
            placeholder="Time Start"
            v-model="session.timeStart"
            required
            autofocus
          />
          </div>
          <div>
          <label for="time_end" class="sr-only">Time End: </label>
          <input
            type="time"
            id="time_end"
            class="form-control"
            placeholder="Time End"
            v-model="session.timeEnd"
            required
            autofocus
          />
          </div>
          <div>
          <label for="format" class="sr-only">Format: </label>
          <input
            type="text"
            id="format"
            class="form-control"
            placeholder="Paper, digital, etc."
            v-model="session.format"
            required
            autofocus
          />
          </div>
          <div>
          <label for="notes" class="sr-only">Notes: </label>
          <input
            type="text"
            id="notes"
            class="form-control"
            placeholder="Notes"
            v-model="session.notes"
            required
            autofocus
          />
          <button class="btn btn-lg btn-primary btn-block" type="submit">
            Submit
          </button>
          </div>
          </form>
            </div>

</template>

<script>
import authService from "../services/AuthService";

export default {
  // Saving a session object
    name: "session",
    data(){
        return{
            session:{
                userId:"",
                isbn:"",
                daySession:"",
                timeStart:"",
                timeEnd:"",
                pagesRead:"",
                format:"",
                notes:"",
            },
            sessionErrors: false,
            sessionErrorMsg: "There were problems creating this session.",
        };
    },
    methods:{
      //Calls the createSession service to send the session object to the server
        newSession() {
            this.session.userId = this.$store.state.user.id;
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
.form-session {
  display: grid;
  grid-template-columns: 1fr;
  gap: 5px;
  justify-items: center;
  align-items: start;
}
</style>