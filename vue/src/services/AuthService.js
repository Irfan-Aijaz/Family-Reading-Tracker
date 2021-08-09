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

  getUserIdsByFamilyId(familyId) {
    return axios.get('/retrieve_user_ids/' + familyId)
  },

  getUserDTOsByFamilyId(familyId) {
    return axios.get('/retrieve_user_dtos/' + familyId)
  },


}
