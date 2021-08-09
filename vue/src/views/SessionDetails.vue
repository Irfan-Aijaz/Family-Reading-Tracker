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
  <div class = "container">
      <table id = "tblSessionDetails">
          <tr>
              <th>Title:</th>
              <td>{{sessionDetails.title}}</td>
          </tr>
          <tr>
              <th>ISBN:</th>
              <td>{{sessionDetails.isbn}}</td>
          </tr>
          <tr>
              <th>Time Spent Reading:</th>
              <td>{{sessionDetails.minutesRead}} minutes</td>
          </tr>
          <tr>
              <th>Time Started:</th>
              <td>{{sessionDetails.timeStart}}</td>
          </tr>
          <tr>
              <th>Time Ended:</th>
              <td>{{sessionDetails.timeEnd}}</td>
          </tr>
          <tr>
              <th>Date:</th>
              <td>{{sessionDetails.daySession}}</td>
          </tr>
          <tr>
              <th>Format:</th>
              <td>{{sessionDetails.format}}</td>
          </tr>
          <tr>
              <th>Notes:</th>
              <td>{{sessionDetails.notes}}</td>
          </tr>
          </table>
  </div>
</template>

<script>
import sessionService from "../services/SessionService";

export default {
    name: "sessionDetails",
    data() {
        return {
            sessionDetails: {}
        }
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
        }
    },
    created() {
        this.loadSessionDetailsBySessionId();
    }

}
</script>

<style>
th, td {
  padding: 15px;
  text-align: left;
  
} 

</style>