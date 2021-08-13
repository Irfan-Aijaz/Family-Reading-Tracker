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

    getSessionsByFamilyId(familyId) {
      return axios.get('/get_sessions_by_family_id/' + familyId)
    },

    getMinutesReadForClaim(startDate, endDate, userId) {
      return axios.get('/get_minutes_read_for_book_in_date_range', {params: {startDate: startDate, endDate: endDate, userId: userId}})
    }
    

}