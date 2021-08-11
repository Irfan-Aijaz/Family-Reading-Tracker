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
            <select id="isbn" name="isbn" v-model="bookIndex">
              <option
                v-for="(book, index) in library"
                v-bind:key="book.isbn"
                v-bind:value="index"
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
              min="0"
              :max="selectedBook.pagesTotal"
              id="pages"
              class="form-control"
              :placeholder="selectedBook.pagesTotal"
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
      <!-- <div class="picture">
        <img
          src="https://y.yarn.co/8c0a1869-c221-4910-bc0d-2b3f42815897_text.gif"
          width="350px"
          height="300px"
        />
      </div> -->
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
      bookIndex: 0,
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
  watch: {
    bookIndex: function() {
      if (this.session.pagesRead>this.selectedBook.pagesTotal) {
        this.session.pagesRead = this.selectedBook.pagesTotal;
      }
    }
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
      this.session.isbn = this.selectedBook.isbn;
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
  computed: {
    selectedBook() {
      return this.library[this.bookIndex];
    }
  },
  created() {
    this.family();
  },
};
</script>

<style>
.form-session {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 15px;
  background-color: rgb(158, 157, 157);
  border: 2px solid black;
  width: 500px;
  height: 300px;
  margin-left: 30%;
  
}
</style>