<template>
  <div>
    <div class="books">
      <div v-for="(userBooks, index) in userBooks" :key="index">
        {{ userBooks.title }}
        <div class="img">
          <img
            v-if="userBooks.title"
            v-bind:src="
              'http://covers.openlibrary.org/b/title/' +
              userBooks.title +
              '-M.jpg'
            "
          />
        </div>
        <div>
          {{ userBooks.pagesRead }} Pages Read of
          {{ userBooks.pagesTotal }} Total Pages
        </div>
        <button v-on:click="$router.push({ name: 'newSession' })">
          Create New Session
        </button>
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

</style>