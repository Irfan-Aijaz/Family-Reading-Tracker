import axios from 'axios';

export default {
    createPrize(prize) {
        return axios.post('/create_prize', prize)
    },
}