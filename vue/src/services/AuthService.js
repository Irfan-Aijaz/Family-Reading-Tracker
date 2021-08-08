import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },

  adminRegister(user) {
    return axios.post('/register_as_admin', user)
  },

  getFamily(familyId) {
    return axios.get('/retrieve_family/' + familyId)
  },

  createBook(book) {
    return axios.post('/create_book', book)
  },

  createSession(session) {
    return axios.post('/create_session', session)
  },

  updateUserBook(session) {
    return axios.put('/update_user_book', session)
  },

  getAllBooks(book) {
    return axios.get('/get_all_books', book)
  },

  getUserIdsByFamilyId(familyId) {
    return axios.get('/retrieve_user_ids/' + familyId)
  },

  getUserDTOsByFamilyId(familyId) {
    return axios.get('/retrieve_user_dtos/' + familyId)
  },

  getSessionsByUserId(userId) {
    return axios.get('/get_sessions_by_user_id/' + userId)
  },

  getSessionDetailsBySessionId(sessionId) {
    return axios.get('/get_session_details_by_session_id/' + sessionId)
  },

  getUserBooksProgress(userId) {
    return axios.get('/retrieve_books_progress/' + userId)
  }



}
