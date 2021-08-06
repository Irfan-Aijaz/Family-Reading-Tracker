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
  }


}
