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

.book {
  width: 200px;
  background-color: rgb(134, 134, 134, .7);
  border: 2px solid black;
  height: 320px;
}
.book img {
  width: 50%;
}
</style>
