import axios from "../service/axios-auth.js";
import Cookies from "js-cookie";

const BASE_URL = "http://localhost:8080";

// export const userService = {
//   register(registerRequest) {
//     return fetch(`${BASE_URL}/auth/register`, {
//       method: "POST",
//       headers: {
//         "Content-Type": "application/json",
//       },
//       body: JSON.stringify(registerRequest),
//     })
//       .then((response) => {
//         if (response.ok) {
//           return response.json();
//         } else {
//           return Promise.reject(new Error("Failed to register"));
//         }
//       })
//       .catch((error) => {
//         if (error.response && error.response.status === 409) {
//           throw new Error(error.response.data.message);
//         }
//         throw error;
//       });
//   },
//   login(request) {
//     return fetch(`${BASE_URL}/auth/authenticate`, {
//       method: "POST",
//       headers: {
//         "Content-Type": "application/json",
//       },
//       body: JSON.stringify(request),
//     }).then((response) => {
//       if (response.ok) {
//         return response.json();
//       } else {
//         return Promise.reject(new Error("Failed to login"));
//       }
//     });
//   },
//   getUserDetails() {
//     return fetch(`${BASE_URL}/user`, {
//       method: "GET",
//       headers: {
//         "Content-Type": "application/json",
//       },
//       body: JSON.stringify(),
//     }).then(() => {
//       if (response.ok) {
//         return response.json();
//       } else {
//         return Promise.reject(new Error("Failed to get user details"));
//       }
//     });
//   },
//   logout() {
//     localStorage.removeItem("token");
//   },
// };

const access_token = localStorage.getItem("token");

export const userService = {
  login: async function (request) {
    try {
      const response = await axios.post(
        `${BASE_URL}/auth/authenticate`,
        JSON.stringify(request),
        {
          headers: {
            Authorization: access_token,
            "Content-Type": "application/json",
          },
        }
      );
      return response.data.user;
    } catch (error) {
      console.error(error);
      throw new Error(error.response.data.message);
    }
  },

  logout: function () {
    //localStorage.removeItem("token");
  },

  getCurrentUser: async function () {
    try {
      const response = await axios.get(`${BASE_URL}/user`);
      return response.data;
    } catch (error) {
      console.error(error);
      throw new Error(error.response.data.message);
    }
  },

  register: async function (registerRequest) {
    try {
      const response = await axios.post(
        `${BASE_URL}/auth/register`,
        JSON.stringify(registerRequest),
        registerRequest,
        {
          headers: {
            Authorization: access_token,
            "Content-Type": "application/json",
          },
        }
      );
      const jwtToken = response.data.token;
      Cookies.set("jwtToken", jwtToken, { expires: 2 });
      return response.data.user;
    } catch (error) {
      console.error(error);
      throw new Error(error.response.data.message);
    }
  },
};
