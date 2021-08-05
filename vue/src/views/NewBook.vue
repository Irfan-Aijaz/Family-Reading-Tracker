<template>
  <div class="books">
    <h2 class="book-title">{{ book.title }}</h2>

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
export default {
  name: "newBook",
  props: ["book"],
  methods: {
    toggleBookRead(book) {
      this.$store.commit("toggleBookRead", book);
    },
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