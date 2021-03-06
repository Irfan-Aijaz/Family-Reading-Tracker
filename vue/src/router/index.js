import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import NewBook from '../views/NewBook.vue'
import NewSession from '../views/NewSession.vue'
import SessionDetails from '../views/SessionDetails'
import SessionsList from '../views/SessionsHistory'
import InProgress from '../views/InProgress'
import Prizes from '../views/Prizes'
import CreatePrize from '../components/NewPrize'
import UpdatePrize from '../components/EditPrize'
import Completed from '../views/Completed.vue'
import DeletePrize from '../components/DeletePrize'
import ClaimPrize from '../views/ClaimPrize'
import ClaimPrizeRequests from '../views/ClaimPrizeRequests'
import ViewWonPrizes from '../views/ViewWonPrizes'

// import FamilyPrizeList from '../components/FamilyPrizeList'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      },
    },
    {
      path: "/register_as_admin",
      name: "registerAsAdmin",
      component: Register,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/new_book",
      name: "newBook",
      component: NewBook,
      meta: {
        requiresAuth: true
      }
    },
    {
      path:"/new_session",
      name: "newSession",
      component: NewSession,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/view_sessions/:id",
      name: "sessionDetails",
      component: SessionDetails,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/view_sessions",
      name: "viewSessionsHistory",
      component: SessionsList,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/retrieve_books_progress/:id",
      name: "inProgress",
      component: InProgress,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/completed",
      name: "completed",
      component: Completed,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/prizes",
      name: "prizes",
      component: Prizes,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/new_prize",
      name: "newPrize",
      component: CreatePrize,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/update_prize",
      name: "updatePrize",
      component: UpdatePrize,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/delete_prize",
      name: "deletePrize",
      component: DeletePrize,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/claim_prize",
      name: "claimPrize",
      component: ClaimPrize,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/claims",
      name: "claims",
      component: ClaimPrizeRequests,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/won-prizes",
      name: "wonPrizes",
      component: ViewWonPrizes,
      meta: {
        requiresAuth: true
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
