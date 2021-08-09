import axios from 'axios';

export default {

    createBook(book) {
        return axios.post('/create_book', book)
    },

    getAllBooks(book) {
        return axios.get('/get_all_books', book)
    },

    getUserBooksProgress(userId) {
        return axios.get('/retrieve_books_progress/' + userId)
    },

    updateUserBook(session) {
        return axios.put('/update_user_book', session)
    },



    

    
}