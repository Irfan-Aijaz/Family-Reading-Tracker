<template>
  <div>
    <div class="books">
      <book
        v-for="(b, index) in userBooks"
        :key="index"
        :title="b.title"
        button-text1="New Session"
        button-text2="Completed"
        @button1-clicked="$router.push({ name: 'newSession' })"
        @button2-clicked="completedBook(index)"
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
          console.log("resquest success");
          console.log(response);
          if (response.status == 200) {
            this.userBooks = response.data;
          }
        })
        .catch((error) => {
          console.log("request error");
          const response = error.response;
          this.userBookRetrievalErrors = true;
          if (response.status === 400) {
            this.userBookRetrievalErrorMsg =
              "Bad Request: Update User Book Errors";
          }
        });
    },
    completedBook(index) {
      this.updateUserBook.isbn = this.userBooks[index].isbn
      bookService  
        .restartBook(this.updateUserBook)
        .then((resonse) => {
          if (resonse.status == 200) {
            this.$router.push({
              path: "/completed",
              query: { completedBook: "success" },
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
  created() {
    this.retrieveUserBooksProgress();
  },
};
</script>

<style scoped>
.books{
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  gap: 5%;
}
</style>