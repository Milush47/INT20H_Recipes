import axios from 'axios';

export async function getRecipes() {
    const response = await axios.get('/recipes');
    return response.data;
}