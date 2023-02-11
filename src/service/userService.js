import axios from "axios";

export async function createUser(data) {
    const response = await axios.post('/auth/register', { user:data });
    return response.data;
}