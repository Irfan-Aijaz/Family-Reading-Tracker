<template>
  <div>
    <div class="form">
      <!-- The form labels for adding a new book-->
      <form class="form-register-new-book" @submit.prevent="create">
        <h2 class="book-title">Add Book to Library</h2>
        <label for="title" class="sr-only"> Title: </label>
        <input
          type="text"
          id="title"
          class="form-control"
          placeholder="Title"
          v-model="book.title"
          required
          autofocus
        />
        <label for="author" class="sr-only"> Author: </label>
        <input
          type="text"
          id="author"
          class="form-control"
          placeholder="Author"
          v-model="book.author"
          required
          autofocus
        />
        <label for="isbn" class="sr-only"> ISBN: </label>
        <input
          type="text"
          id="isbn"
          class="form-control"
          placeholder="ISBN"
          v-model="book.isbn"
          required
          autofocus
        />
        <label for="pages_total" class="sr-only"> Total Pages: </label>
        <input
          type="text"
          id="pages_total"
          class="form-control"
          placeholder="20, 300, etc."
          v-model="book.pagesTotal"
          required
          autofocus
        />

        <button class="btn btn-lg btn-primary btn-block" type="submit">
          Add Book
        </button>
      </form>

      <!-- Display Added Books -->
    </div>
    <div class="books">
      <div v-for="(book, index) in books" :key="index">
        {{ book.title }}

        <img
          v-if="book.title"
          v-bind:src="
            'http://covers.openlibrary.org/b/title/' + book.title + '-M.jpg'
          "
        />

        <p>
          {{ book.author }}
        </p>

        <button v-on:click="$router.push({ name: 'newSession' })">
          Start Reading
        </button>
        <button>Remove</button>
      </div>
    </div>
  </div>
</template>

<script>
import bookService from "../services/BookService";

export default {
  name: "newBook",
  data() {
    return {
      book: {
        isbn: "",
        title: "",
        author: "",
        pagesTotal: "",
      },
      books: [],
      bookCreationErrors: false,
      bookCreationErrorMsg: "There was a problem adding a book.",
    };
  },
  mounted: function () {
    bookService.getAllBooks().then((response) => {
      console.log(response);
      this.books = response.data;
    });
  },
  methods: {
    toggleBookRead(book) {
      this.$store.commit("toggleBookRead", book);
    },
    create() {
      bookService
        .createBook(this.book)
        .then((response) => {
          if (response.status == 201) {
            this.$router.push({
              path: "/",
              query: { bookregistration: "success" },
            });
          }
        })
        .catch((error) => {
          const response = error.response;
          this.bookCreationErrors = true;
          if (response.status === 400) {
            this.bookCreationErrorMsg = "Bad Request: Validation Errors";
          }
        });
    },
  },
};
</script>

<style>
.form {
  border: 2px solid black;
  border-radius: 10px;
  width: 80%;
  margin: 0 auto;
}
.books {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  gap: 20px;
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