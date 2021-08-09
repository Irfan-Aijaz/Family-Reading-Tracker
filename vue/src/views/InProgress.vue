<template>
  <div class="books">
    <div v-for="(userBooks, index) in userBooks" :key="index">
      {{ userBooks.title }}
      <img
        v-if="userBooks.title"
        v-bind:src="
          'http://covers.openlibrary.org/b/title/' + userBooks.title + '-M.jpg'
        "
      />
      <div>{{ userBooks.pagesRead }} Pages Read</div>
      <button v-on:click="$router.push({ name: 'newSession' })">Create New Session</button>
    </div>
          <div
        v-if="this.$store.state.user.authorities[0].name == 'ROLE_ADMIN'"
        id="register-family"
        class="text-center"
      >
        <div class="parentOptions">
          <button v-on:click="$router.push({ name: 'register' })">
            Add Family Member
          </button>
          <button v-on:click="$router.push({ name: 'newBook' })">
            Add Book
          </button>
          <button v-on:click="$router.push({ name: 'newSession' })">
            Record Session
          </button>
          <button
            v-on:click="
              $router.push({
                name: 'inProgress',
                params: { id: $store.state.user.id },
              })
            "
          >
            In Progress
          </button>
          <button v-on:click="$router.push({ name: 'completed' })">
            Completed
          </button>
          <button v-on:click="$router.push({ name: 'viewSessionsHistory' })">
            Track Family Progress
          </button>
          <button v-on:click="$router.push({ name: 'prizes' })">Prizes</button>
        </div>
      </div>
      <div
        v-if="this.$store.state.user.authorities[0].name == 'ROLE_USER'"
        class="text-center"
      >
        <div class="childOptions">
          <button v-on:click="$router.push({ name: 'newBook' })">
            Add Book
          </button>
          <button v-on:click="$router.push({ name: 'inProgress' })">
            In Progress
          </button>
          <button v-on:click="$router.push({ name: 'newSession' })">
            Record Session
          </button>
          <button v-on:click="$router.push({ name: 'viewSessionsHistory' })">
            View Reading Activity
          </button>
          <button v-on:click="$router.push({ name: 'completed' })">
            Completed
          </button>
          <button v-on:click="$router.push({ name: 'prizes' })">Prizes</button>
        </div>
      </div>
  </div>
</template>

<script>
import bookService from "../services/BookService";

export default {
  name: "userBook",
  data() {
    return {
      userBooks: [],
      userBookRetrievalErrors: false,
      userBookRetrievalErrorMsg:
        "There was a problem retrieving user books list.",
    };
  },
  methods: {
    retrieveUserBooksProgress() {
      bookService
        .getUserBooksProgress(this.$store.state.user.id)
        .then((response) => {
          if (response.status == 200) {
            this.userBooks = response.data;
          }
        })
        .catch((error) => {
          const response = error.response;
          this.userBookRetrievalErrors = true;
          if (response.status === 400) {
            this.userBookRetrievalErrorMsg =
              "Bad Request: Update User Book Errors";
          }
        });
    },
  },
  created() {
    this.retrieveUserBooksProgress();
  },
};
</script>

<style>
.parentOptions {
  display: grid;
  grid-template-columns: 1fr;
  gap: 0px;
  justify-items: center;
  align-items: start;
  position: absolute;
  top: 10px;
  left: 0px;
  font-size: 25px;
  border: 2px solid rgb(139, 203, 232);;
  border-radius: 15px;
  padding-top:15px;
  width: 200px;
  height: 500px;
  margin: 10px;
  margin-bottom: 10%;
}
.childOptions {
  display: grid;
  grid-template-columns: 1fr;
  gap: 0px;
  justify-items: center;
  align-items: start;
  position: absolute;
  top: 10px;
  left: 0px;
  font-size: 25px;
  border: 2px solid rgb(139, 203, 232);;
  border-radius: 15px;
  padding-top:15px;
  width: 200px;
  height: 500px;
  margin: 10px;
  margin-bottom: 10%;
}
.books {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  gap: 15%;
  padding-right: 25%;
  padding-left: 25%;
}

</style>