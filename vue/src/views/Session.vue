<template>
  <div is="session" class="text-center">
      <form class="form-session" @submit.prevent="session">

           <h1 class="h3 mb-3 font-weight-normal">New Session</h1>
          <div
            class="alert alert-danger"
            role="alert"
            v-if="sessionErrors"
          >
            {{ sessionErrorMsg }}
          </div>
          <label for="isbn" class="sr-only">ISBN: </label>
          <input
            type="text"
            id="isbn"
            class="form-control"
            placeholder="isbn"
            v-model="session.isbn"
            required
            autofocus
          />
          </div>

</template>

<script>
export default {

}
</script>

<style>

</style>