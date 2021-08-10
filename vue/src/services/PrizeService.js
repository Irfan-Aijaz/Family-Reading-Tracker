import axios from 'axios';

export default {
    createPrize(prize) {
        return axios.post('/create_prize', prize)
    },

    updatePrize(prize) {
        return axios.put('/update_prize', prize)
    },

    getPrizesForFamilyUserGroup(familyId, userGroup) {
        return axios.get('/get_prizes_for_family_and_user_group', { params: {familyId: familyId, userGroup: userGroup}})
    }

}