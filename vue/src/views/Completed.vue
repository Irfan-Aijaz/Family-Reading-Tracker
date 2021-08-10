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

      <button v-on:click="$router.push({ name: 'newSession' })">Read Again</button>
    </div>
  <navigation></navigation>
  </div>
</template>

<script>
import Navigation from '../components/Navigation.vue';
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
  gap: 15%;
  padding-right: 25%;
  padding-left: 25%;
}

</style>