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
          <Dropdown
            :options="[{ id: 1, name: ''}, { id: 2, name: 'Option 2'}]"
            v-on:selected="validateSelection"
             v-on:filter="getDropdownValues"
            :disabled="false"
            name="zipcode"
            :maxItem="10"
            placeholder="Please select an option">
            </Dropdown>
          <label for="format" class="sr-only">Format: </label>
          <input
            type="text"
            id="format"
            class="form-control"
            placeholder="format"
            v-model="session.format"
            required
            autofocus
          />

          </div>

</template>

<script>
export default {};
</script>

<style>
</style>