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
        <tr class="session-detail-rows">
          <th>Title:</th>
          <td>{{ sessionDetails.title }}</td>
        </tr>
        <tr class="session-detail-rows">
          <th>ISBN:</th>
          <td>{{ sessionDetails.isbn }}</td>
        </tr>
        <tr class="session-detail-rows">
          <th>Time Spent Reading:</th>
          <td>{{ sessionDetails.minutesRead }} minutes</td>
        </tr>
        <tr class="session-detail-rows">
          <th>Time Started:</th>
          <td>{{ sessionDetails.timeStart }}</td>
        </tr>
        <tr class="session-detail-rows">
          <th>Time Ended:</th>
          <td>{{ sessionDetails.timeEnd }}</td>
        </tr>
        <tr class="session-detail-rows">
          <th>Date:</th>
          <td>{{ sessionDetails.daySession }}</td>
        </tr>
        <tr class="session-detail-rows">
          <th>Format:</th>
          <td>{{ sessionDetails.format }}</td>
        </tr>
        <tr class="session-detail-rows">
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
  background-color: grey;
}

</style>