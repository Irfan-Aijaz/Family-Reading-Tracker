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
              <tr v-for="session in sessions" v-bind:key="session.id" v-on:click="$router.push({ name: 'session details', params: {id: session.id } })"><!-- this should take a session id as a parameter-->
                  <td>
                      {{session.title}}
                  </td>
                  <td>
                      {{session.time}}
                  </td>
                  <td>
                      {{session.dayOfSession}}
                  </td>
              </tr>
          </tbody>
      </table>
  </div>
</template>

<script>
export default {
  name: "sessions",
  data() {
    return {
      sessions: [
          {
              id: 1,
              title: "harry potter",
              time: 10,
              dayOfSession: "8/7/2021"
          },
          {
              id: 2,
              title: "harry potter 2",
              time: 11,
              dayOfSession: "8/6/2021"
          }
      ],
    };
  },
};
</script>
   
<style>

</style>