import axios from "axios";

export async function getProducts() {
    const response = await axios.get('/my-products');
    return response.data;
}