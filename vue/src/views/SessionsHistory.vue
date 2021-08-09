<template>
  <div class="container">
    <table id="tblSessions">
      <thead>
        <!--  -->
        <select v-if="$store.state.user.authorities[0].name == 'ROLE_ADMIN'" id="family-members" name="family-members" v-model="filter.username"> 
          <option disable value>Show All</option>
          <option v-for="object in usersInFamily" v-bind:key="object.username" v-bind:value="object.username">
            {{object.username}}
            </option>
        </select>
        <!--  -->
        <tr>
          <th>Title</th>
          <th>Time Spent Reading</th>
          <th>Day of Session</th>
          <th v-if="$store.state.user.authorities[0].name == 'ROLE_ADMIN'">
            User
          </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="session in filteredSessions"
          v-bind:key="session.sessionId"
          v-on:click="
            $router.push({
              name: 'sessionDetails',
              params: { id: session.sessionId },
            })
          "
        >
          <!-- this should take a session id as a parameter-->
          <td>
            {{ session.title }}
          </td>
          <td>{{ session.minutesRead }} minutes</td>
          <td>
            {{ session.daySession }}
          </td>
          <td v-if="$store.state.user.authorities[0].name == 'ROLE_ADMIN'">
            {{ session.username }}
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import sessionService from "../services/SessionService";
import authService from "../services/AuthService";
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
      usersInFamily: [],

//      daySession:"2021-08-02"
// minutesRead:180
// sessionId:1
// title:"test"
// username:"test1child"
      filter: {
         daySession: '',
         minutesRead: '',
         sessionId: '',
         title: '',
         username: ''
      }
    };
  },
  computed: {
      filteredSessions() {
          return this.sessions.filter((session) => {
              return session.username == this.filter.username || this.filter.username == '';
          });
      }
  },
  methods: {
    loadSessionsByFamilyId() {
      sessionService
        .getSessionsByFamilyId(this.$store.state.user.familyId)
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
    },
    loadUsersByFamilyId(){
      authService
        .getUserDTOsByFamilyId(this.$store.state.user.familyId)
        .then((response) => {
          if (response.status == 200) {
            this.usersInFamily = response.data;
          }
        })
        .catch((error) => {
          const response = error.response;
          if (response.status === 401) {
            //
          }
        });
    },
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
    },
  },
  created() {
    if (this.$store.state.user.authorities[0].name == "ROLE_USER") {
      this.loadSessionsByUserId();
    } else {
      this.loadSessionsByFamilyId();
      this.loadUsersByFamilyId();
    }
  },
};
</script>
   
<style>
th, td {
  padding: 15px;
  text-align: left;
  
} 
</style>