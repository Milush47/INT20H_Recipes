import axios from 'axios';
import setAuthHeader from "./setAuthHeader";

const BASE_URL = "http://localhost:8080";

export const userService = {
  login: async function (request) {
    try {
      const response = await axios.post(
        `${BASE_URL}/auth/authenticate`,
        request
      );
      const token = response.data.data.token;
      localStorage.setItem("token", token);

      setAuthHeader(token);
    } catch (error) {
      console.error(error);
      throw new Error(error.response.message);
    }
  },

  logout: async function () {
    localStorage.removeItem("token");
  },

  getCurrentUser: async function () {
    try {
      const response = await axios.get(
        `${BASE_URL}/profile`
      );
      return response.data;
    } catch (error) {
      console.error(error);
      throw new Error(error.response.message);
    }
  },

  register: async function (registerRequest) {
    try {
      const response = await axios.post(
        `${BASE_URL}/auth/register`,
        registerRequest
      );

      const token = response.data.data.token;
      localStorage.setItem("token", token);

      setAuthHeader(token);
    } catch (error) {
      console.error(error);
      throw new Error(error.response.message);
    }
  },
};
