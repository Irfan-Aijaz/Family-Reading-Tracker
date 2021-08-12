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
        @button2-clicked="$router.push({ name: 'completed' })"
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
  },
  created() {
    this.retrieveUserBooksProgress();
  },
};
</script>

<style scoped>
</style>