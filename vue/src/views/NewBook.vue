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
      <book
        v-for="(b, index) in books"
        :key="index"
        :title="b.title"
        :author="b.author"
        button-text1="Start Reading"
        button-text2="Remove"
        @button1-clicked="$router.push({ name: 'newSession' })"
        @button2-clicked="removeBook(index)"
      />
    </div>
  </div>
</template>

<script>
import bookService from "../services/BookService";
import Book from "../components/Book.vue"

export default {
  name: "newBook",
  components: {
   'book': Book
 },
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
    removeBook (index) {
      console.log('Removing this book')
      console.log(this.books[index])
    }
  },
};
</script>

<style scoped>
.form {
  border: 4px solid black;
  border-radius: 10px;
  margin: 0 auto;
  background-color: rgb(194, 191, 191);
  margin-top: 5%;
  margin-bottom: 3%;
  width: 90%;
  
}
.books{
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  gap: 5%;
}
</style>