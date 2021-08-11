<template>
  <div>
    <navigation></navigation>
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
import Navigation from "../components/Navigation.vue";
import bookService from "../services/BookService";

export default {
  components: {
    navigation: Navigation,
  },

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
.books {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  gap: 20px;
  position: relative;
  left: 20%;
  align-content: center;
  text-align: center;
  grid-template-rows: 100px 1fr 100px;
  margin-top: 5%;
}
.books img {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  gap: 40px;
  position: relative;
  left: 20%;
  align-content: center;
  text-align: center;
  grid-template-rows: 100px 100px 100px 100px;
  margin-top: 3%;
}
</style>