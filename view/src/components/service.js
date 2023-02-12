import axios from 'axios';

const baseUrl = 'http://localhost:8080/';

export default {
    async fetchDuePages() {
        try {
            const response = await axios.get(baseUrl);
            return response.data;
        } catch (error) {
            console.error(error);
            return [];
        }
    },

    async addDuePage(page) {
        try {
            const response = await axios.post(baseUrl, page);
            return response.data;
        } catch (error) {
            console.error(error);
            return {};
        }
    }
};
