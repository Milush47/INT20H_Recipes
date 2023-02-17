import axios from "axios";

export async function createUser(data) {
    const url = '/auth/register';
    const body = { user:data };
    const registerRequest = await axios.post(url, body);
    return registerRequest.data;
}

export async function loginUser(data) {
    const registerRequest = await axios.post('/auth/authenticate', { user:data });
    return registerRequest.data;
}

export async function logoutUser() {
    const registerRequest = await axios.post('/auth/logout');
    return registerRequest.data;
}

export async function getProfile() {
    const registerRequest = await axios.get('/profile');
    return registerRequest.data;
}