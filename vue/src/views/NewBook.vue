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

    <h3 class="book-author">{{ book.author }}</h3>
    <button
      v-on:click="toggleBookRead(book)"
      v-bind:class="book.read ? 'mark-unread' : 'mark-read'"
    >
      Mark {{ book.read !== true ? "Read" : "Unread" }}
    </button>
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
        author: ""
      }
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
            // query: 
          });
        }
      })
      .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });

      
    }
  },
};
</script>

<style>
.books {
    border: 2px solid black;
    border-radius: 10px;
    width: 250px;
    height: 550px;
    margin: 20px;
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