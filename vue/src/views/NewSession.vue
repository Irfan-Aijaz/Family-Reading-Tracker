<template>
  <div id="session" class="text-center">
    <div class="form-session">
      <div>
        <form @submit.prevent="newSession">
          <h1 class="h3 mb-3 font-weight-normal">
            New Session For: {{ this.$store.state.user.username }}
          </h1>
          <div class="alert alert-danger" role="alert" v-if="sessionErrors">
            {{ sessionErrorMsg }}
          </div>
          <div
            v-if="this.$store.state.user.authorities[0].name == 'ROLE_ADMIN'"
          >
            <label for="selected-child" class="sr-only"
              >Choose a reader:
            </label>
            <select
              id="family-members"
              name="family-members"
              v-model="session.userId"
            >
              <option
                v-for="object in familyMembers"
                v-bind:key="object.username"
                v-bind:value="object.userId"
              >
                {{ object.username }}
              </option>
            </select>
          </div>
          <div>
            <!--The form inputs for recording a session -->
            <label for="isbn" class="sr-only">Title: </label>
            <select id="isbn" name="isbn" v-model="session.isbn">
              <option
                v-for="book in library"
                v-bind:key="book.isbn"
                v-bind:value="book.isbn"
              >
                {{ book.title }}
              </option>
            </select>
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
              type="number"
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
            <select id="format" name="format" v-model="session.format">
              <option value="Paper">Paper</option>
              <option value="Digital">Digital</option>
              <option value="Audiobook">Audiobook</option>
              <option value="Reader">Read-Aloud (Reader)</option>
              <option value="Listener">Read-Aloud (Listener)</option>
              <option value="Other">Other</option>
            </select>
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
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import bookService from "../services/BookService";
import sessionService from "../services/SessionService";

export default {
  // Saving a session object
  name: "session",
  data() {
    return {
      familyMembers: [],
      session: {
        userId: "",
        isbn: "",
        daySession: "",
        timeStart: "",
        timeEnd: "",
        pagesRead: "",
        format: "",
        notes: "",
      },
      library: [],
      sessionErrors: false,
      sessionErrorMsg: "There were problems creating this session.",
    };
  },
  mounted: function () {
    bookService.getAllBooks().then((response) => {
      console.log(response);
      this.library = response.data;
    });
  },

  methods: {
    family() {
      authService
        .getUserDTOsByFamilyId(this.$store.state.user.familyId)
        .then((response) => {
          if (response.status == 200) {
            this.familyMembers = response.data;
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            // this.invalidCredentials = true;
          }
        });
    },
    //Calls the createSession service to send the session object to the server
    newSession() {
      if (this.$store.state.user.authorities[0].name == "ROLE_USER") {
        this.session.userId = this.$store.state.user.id;
      }
      sessionService
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
      bookService
        .updateUserBook(this.session)
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
            this.sessionErrorMsg = "Bad Request: Update User Book Errors";
          }
        });
    },
  },
  created() {
    this.family();
  },
};
</script>

<style>
.form-session {
  display: grid;
  grid-template-columns: 1fr;
  align-items: center;
}
</style>