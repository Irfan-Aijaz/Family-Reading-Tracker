<template>
  <div>
    <div class="books">
      <div v-for="(userBooks, index) in userBooks" :key="index">
        {{ userBooks.title }}
        <img
          v-if="userBooks.title"
          v-bind:src="
            'http://covers.openlibrary.org/b/title/' +
            userBooks.title +
            '-M.jpg'
          "
        />

        <button v-on:click="restartBook" >
          Read Again
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
      bookIndex: 0,
      updateUserBook: {
        userId: this.$store.state.user.id,
        isbn: '',
        pagesRead: '0',
        minutesRead: '0',
        completed: 'false'
      },
      userBookRetrievalErrors: false,
      userBookRetrievalErrorMsg:
        "There was a problem retrieving user books list.",
      restartBookErrors: false,
      restartBookErrorMsg:
        "There was a problem restarting the book.",
    };
  },
  methods: {
    retrieveUserBooksProgress() {
      bookService
        .getUserBooksCompleted(this.$store.state.user.id)
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
    restartBook() {
      bookService
        .restartBook(this.updateUserBook)
        .then((resonse => {
          if (resonse.status == 200) {
            this.$router.push({
              path: "/inProgress",
              query: { restartBook: "success" },
            });
          }
        }))
        .catch((error) => {
          const response = error.response;
          this.restartBookErrors = true;
          if (response.status === 400) {
            this.restartBookErrorMsg = "Bad Request: Restart Book Errors";
          }
        });
    }
  },
  computed: {
    selectedBook() {
      return this.userBooks[this.bookIndex];
    }
  },
  created() {
    this.retrieveUserBooksProgress();
  },
};
</script>

<style>

</style>