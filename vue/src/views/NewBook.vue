<template>
  <div class="books">
    <form class="form-register-new-book" @submit.prevent="create">
      <h2 class="book-title">Add Book to Library</h2>
      <label for="title" class="sr-only">Title: </label>
      <input
        type="text"
        id="title"
        class="form-control"
        placeholder="Title"
        v-model="book.title"
        required
        autofocus
      />
      <label for="author" class="sr-only">Author: </label>
      <input
        type="text"
        id="author"
        class="form-control"
        placeholder="Author"
        v-model="book.author"
        required
        autofocus
      />
      <label for="isbn" class="sr-only">ISBN: </label>
      <input
        type="text"
        id="isbn"
        class="form-control"
        placeholder="ISBN"
        v-model="book.isbn"
        required
        autofocus
      />

      <button class="btn btn-lg btn-primary btn-block" type="submit">
          Add Book
      </button>
    </form>

    <img
      v-if="book.isbn"
      v-bind:src="
        'http://covers.openlibrary.org/b/isbn/' + book.isbn + '-M.jpg'
      "
    />

 
  </div>
</template>



<script>
import authService from "../services/AuthService";


export default {
  name: "newBook",
  data() {
    return {
      book: {
        isbn: "",
        title: "",
        author: "",
      },
      bookCreationErrors: false,
      bookCreationErrorMsg: "There was a problem adding a book.",
    };
  },

  // props: ["book"],
  methods: {
    toggleBookRead(book) {
      this.$store.commit("toggleBookRead", book);
    },
    create() {
      authService
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
.books {
  border: 2px solid black;
  border-radius: 10px;
  width: 800px;
  height: 150px;
  margin: 30px;
  position: absolute;
  right: 225px;
  text-align: center;

}

.books.read {
  background-color: lightgray;
}

.books .book-title {
  font-size: 1.5rem;
}

.books .book-author {
  font-size: 1rem;
}

</style>