<template>
  <div class="container">
    <table id="tblSessions">
      <thead>
        <!--  -->
        <div class="show-all">
          <select
            v-if="$store.state.user.authorities[0].name == 'ROLE_ADMIN'"
            id="family-members"
            name="family-members"
            v-model="filter.username"
          >
            <option disable value>Show All</option>
            <option
              v-for="object in usersInFamily"
              v-bind:key="object.username"
              v-bind:value="object.username"
            >
              {{ object.username }}
            </option>
          </select>
        </div>
        <!--  -->
        <div class="message">
          <h2>Select a Session for More Details:</h2>
        </div>

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
          class="session-rows"
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
import authService from "../services/AuthService";
export default {
  name: "sessions",
  data() {
    return {
      sessions: [],
      usersInFamily: [],
      filter: {
        daySession: "",
        minutesRead: "",
        sessionId: "",
        title: "",
        username: "",
      },
    };
  },
  computed: {
    filteredSessions() {
      return this.sessions.filter((session) => {
        return (
          session.username == this.filter.username || this.filter.username == ""
        );
      });
    },
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
    loadUsersByFamilyId() {
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
#tblSessions {
  padding: 15px;
  text-align: left;
  margin-left: 20%;
}
.show-all {
  left: 5%;
}
.session-rows:hover,.session-detail-rows:hover {
  background-color: #ffffff;
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