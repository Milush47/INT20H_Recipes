import axios from "axios";

export async function createUser(data) {
    const url = '/auth/register';
    const body = { user:data };
    const response = await axios.post(url, body);
    return response.data;
}

export async function loginUser(data) {
    const response = await axios.post('/auth/authenticate', { user:data });
    return response.data;
}

export async function logoutUser() {
    const response = await axios.post('/auth/logout');
    return response.data;
}

export async function getProfile() {
    const response = await axios.get('/profile');
    return response.data;
}