<template>
  <div class="container">
      <table id = "tblSessions">
          <thead>
              <tr>
                  <th>Title</th>
                  <th>Time Spent Reading</th>
                  <th>Day of Session</th>
              </tr>
          </thead>
          <tbody>
              <tr v-for="session in sessions" v-bind:key="session.sessionId" v-on:click="$router.push({ name: 'sessionDetails', params: {id: session.sessionId } })"><!-- this should take a session id as a parameter-->
                  <td>
                      {{session.title}}
                  </td>
                  <td>
                      {{session.minutesRead}} minutes
                  </td>
                  <td>
                      {{session.daySession}}
                  </td>
              </tr>
          </tbody>
      </table>
  </div>
</template>

<script>
import sessionService from "../services/SessionService";
export default {
  name: "sessions",
  data() {
    return {
      sessions: [
        //   {
        //       id: 1,
        //       title: "harry potter",
        //       time: 10,
        //       dayOfSession: "8/7/2021"
        //   },
        //   {
        //       id: 2,
        //       title: "harry potter 2",
        //       time: 11,
        //       dayOfSession: "8/6/2021"
        //   }
      ],
    };
  },
  methods: {
      loadSessionsByUserId() {
          sessionService
            .getSessionsByUserId(this.$store.state.user.id)
            .then((response) => {
                if (response.status == 200) {
                    this.sessions = response.data;
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
      this.loadSessionsByUserId();
  }
};
</script>
   
<style>

</style>