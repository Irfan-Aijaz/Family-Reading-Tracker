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

    <button
      @click="$emit('button1-clicked')"
    >
      {{ buttonText1 }}
    </button>
    <button
      @click="$emit('button2-clicked')"
    >
      {{ buttonText2 }}
    </button>
 
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
      required: false
    },
    buttonText1: {
      type: String,
      required: true
    },
    buttonText2: {
      type: String,
      required: false
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
  height: 300px;
}
.book img {
  width: 50%;
}
</style>
