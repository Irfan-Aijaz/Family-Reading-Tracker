import axios from 'axios';

export default {
    createPrize(prize) {
        return axios.post('/create_prize', prize)
    },

    updatePrize(prize) {
        return axios.put('/update_prize', prize)
    }

}