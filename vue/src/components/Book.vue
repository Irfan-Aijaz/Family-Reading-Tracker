<template>
  <div class="book">
    <p>
      {{ title }}
    </p>

    <img
      v-if="title"
      :src="imgSrc"
    />

    <p>
      {{ author }}
    </p>

    <button v-on:click="$router.push({ name: 'newSession' })">
      Start Reading
    </button>
    <button>Remove</button>
  </div>
</template>

<script>
export default {
  name: "book",
  props: {
    title: {
      type: String,
      required: true
    },
    author: {
      type: String,
      required: true
    }
  },
  methods: {
  },
  computed: {
    imgSrc() {
      return 'http://covers.openlibrary.org/b/title/' + this.title + '-M.jpg'
    },
  }
};
</script>

<style scoped>
.books {
}
.book {
  width: 200px;
}
.book img {
  width: 100%;
}
</style>
