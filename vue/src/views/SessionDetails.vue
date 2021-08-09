<template>
  <!-- daySession:"2021-08-02"
format:"paper"
isbn:"1408855895"
minutesRead:180
notes:"asdfasdf"
pagesRead:123
sessionId:1
timeEnd:"16:49:00"
timeStart:"13:49:00"
title:"test"
userId:4 -->
  <div class="container">
    <div class="table">
      <table id="tblSessionDetails">
        <tr>
          <th>Title:</th>
          <td>{{ sessionDetails.title }}</td>
        </tr>
        <tr>
          <th>ISBN:</th>
          <td>{{ sessionDetails.isbn }}</td>
        </tr>
        <tr>
          <th>Time Spent Reading:</th>
          <td>{{ sessionDetails.minutesRead }} minutes</td>
        </tr>
        <tr>
          <th>Time Started:</th>
          <td>{{ sessionDetails.timeStart }}</td>
        </tr>
        <tr>
          <th>Time Ended:</th>
          <td>{{ sessionDetails.timeEnd }}</td>
        </tr>
        <tr>
          <th>Date:</th>
          <td>{{ sessionDetails.daySession }}</td>
        </tr>
        <tr>
          <th>Format:</th>
          <td>{{ sessionDetails.format }}</td>
        </tr>
        <tr>
          <th>Notes:</th>
          <td>{{ sessionDetails.notes }}</td>
        </tr>
      </table>
    </div>
    <div>
      <button v-on:click="$router.push({ name: 'viewSessionsHistory' })">
        Return to Session History
      </button>
    </div>

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
import sessionService from "../services/SessionService";

export default {
  name: "sessionDetails",
  data() {
    return {
      sessionDetails: {},
    };
  },
  methods: {
    loadSessionDetailsBySessionId() {
      sessionService
        .getSessionDetailsBySessionId(this.$route.params.id)
        .then((response) => {
          if (response.status == 200) {
            this.sessionDetails = response.data;
          }
        })
        .catch((error) => {
          const response = error.response;
          if (response.status === 401) {
            //
          }
        });
    },
  },
  created() {
    this.loadSessionDetailsBySessionId();
  },
};
</script>

<style>
#tblSessionDetails {
  padding: 15px;
  text-align: left;
  margin-left: 20%;
}
tr:hover {
  background-color: #f5f5f5;
  cursor: pointer;
}
.parentOptions {
  display: grid;
  grid-template-columns: 1fr;
  gap: 0px;
  justify-items: center;
  align-items: start;
  position: absolute;
  top: 10px;
  left: 0px;
  font-size: 25px;
  border: 2px solid rgb(139, 203, 232);
  border-radius: 15px;
  padding-top: 15px;
  width: 200px;
  height: 500px;
  margin: 10px;
  margin-bottom: 10%;
}
.childOptions {
  display: grid;
  grid-template-columns: 1fr;
  gap: 0px;
  justify-items: center;
  align-items: start;
  position: absolute;
  top: 10px;
  left: 0px;
  font-size: 25px;
  border: 2px solid rgb(139, 203, 232);
  border-radius: 15px;
  padding-top: 15px;
  width: 200px;
  height: 500px;
  margin: 10px;
  margin-bottom: 10%;
}
</style>