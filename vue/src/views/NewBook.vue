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
              v-if="book.isbn"
              v-bind:src="
                'http://covers.openlibrary.org/b/isbn/' + book.isbn + '-M.jpg'
              "
            />
        
            {{ book.author }}
      
            <button v-on:click="$router.push({ name: 'newSession' })">
              Start Reading
            </button>
            <button>Remove</button>
        
      </div>
    </div>
    <!--Add navigation buttons -->
    <!--Add parent navigation buttons -->
    <div
      v-if="this.$store.state.user.authorities[0].name == 'ROLE_ADMIN'"
      id="register-family"
      class="text-center"
    >
      <div class="parentOptions">
        <button v-on:click="$router.push({ name: 'register' })">
          Add Family Member
        </button>
        <button v-on:click="$router.push({ name: 'newBook' })">Add Book</button>
        <button v-on:click="$router.push({ name: 'newSession' })">
          Record Session
        </button>
        <button
          v-on:click="
            $router.push({
              name: 'inProgress',
              params: { id: $store.state.user.id },
            })
          "
        >
          In Progress
        </button>
        <button v-on:click="$router.push({ name: 'completed' })">
          Completed
        </button>
        <button v-on:click="$router.push({ name: 'viewSessionsHistory' })">
          Track Family Progress
        </button>
        <button v-on:click="$router.push({ name: 'prizes' })">Prizes</button>
      </div>
    </div>
    <!--Add child navigation buttons -->
    <div
      v-if="this.$store.state.user.authorities[0].name == 'ROLE_USER'"
      class="text-center"
    >
      <div class="childOptions">
        <button v-on:click="$router.push({ name: 'newBook' })">Add Book</button>
        <button v-on:click="$router.push({ name: 'inProgress' })">
          In Progress
        </button>
        <button v-on:click="$router.push({ name: 'newSession' })">
          Record Session
        </button>
        <button v-on:click="$router.push({ name: 'viewSessionsHistory' })">
          View Reading Activity
        </button>
        <button v-on:click="$router.push({ name: 'completed' })">
          Completed
        </button>
        <button v-on:click="$router.push({ name: 'prizes' })">Prizes</button>
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
  width: 75%;
  height: 125px;
  margin-left: 20%;
  align-content: right;
  margin-bottom: 10px;
  margin-top: 5%;
}
.books {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  justify-items: center;
  text-align: center;
  margin-right: 25%;
  margin-top: 5%;
  grid-template-rows: 250px  250px 250px  250px;
}
.parentOptions {
  display: grid;
  grid-template-columns: 1fr;
  justify-items: center;
  align-items: start;
  position: absolute;
  top: 25%;
  font-size: 25px;
  border: 2px solid rgb(139, 203, 232);
  border-radius: 15px;
  padding-top: 15px;
  width: 200px;
  height: 500px;
  margin-top: 0%;
  margin-left: 2%;
}
.childOptions {
  display: grid;
  grid-template-columns: 1fr;
  justify-items: center;
  align-items: start;
  position: absolute;
  top: 25%;
  font-size: 25px;
  border: 2px solid rgb(139, 203, 232);
  border-radius: 15px;
  padding-top: 15px;
  width: 200px;
  height: 500px;
  margin-top: 0%;
  margin-left: 2%;
}
</style>