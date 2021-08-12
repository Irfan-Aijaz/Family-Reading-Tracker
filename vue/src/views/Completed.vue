<template>
  <div>
    <div class="books">
      <book
        v-for="(b, index) in userBooks"
        :key="index"
        :title="b.title"
        :author="b.author"
        button-text1="Read Again"
        @button1-clicked="restartBook(index)"
      />
    </div>
  </div>
</template>

<script>
import bookService from "../services/BookService";
import Book from "../components/Book.vue";

export default {
  name: "userBook",
  components: {
    book: Book,
  },
  data() {
    return {
      userBooks: [],
      bookIndex: 0,
      updateUserBook: {
        userId: this.$store.state.user.id,
        isbn: '',
        pagesRead: 0,
        minutesRead: 0,
        completed: "true",
      },
      userBookRetrievalErrors: false,
      userBookRetrievalErrorMsg:
        "There was a problem retrieving user books list.",
      restartBookErrors: false,
      restartBookErrorMsg: "There was a problem restarting the book.",
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
    restartBook(index) {
      this.updateUserBook.isbn = this.userBooks[index].isbn
      bookService  
        .restartBook(this.updateUserBook)
        .then((resonse) => {
          if (resonse.status == 200) {
            this.$router.push({
              path: "/inProgress",
              query: { restartBook: "success" },
            });
          }
        })
        .catch((error) => {
          const response = error.response;
          this.restartBookErrors = true;
          if (response.status === 400) {
            this.restartBookErrorMsg = "Bad Request: Restart Book Errors";
          }
        });
    },
  },
  computed: {
    selectedBook() {
      return this.userBooks[this.bookIndex];
    },
  },
  created() {
    this.retrieveUserBooksProgress();
  },
};
</script>

<style scoped >
</style>