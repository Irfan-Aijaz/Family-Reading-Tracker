import axios from 'axios';

export default {

    createSession(session) {
        return axios.post('/create_session', session)
    },

    getSessionDetailsBySessionId(sessionId) {
      return axios.get('/get_session_details_by_session_id/' + sessionId)
    },

    getSessionsByUserId(userId) {
      return axios.get('/get_sessions_by_user_id/' + userId)
    },
    

}