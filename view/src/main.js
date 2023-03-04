import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

import "./assets/main.css";

axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token');
axios.defaults.headers.post['Content-Type'] = 'application/json';

// const axiosInstance = axios.create({
//     baseURL: 'http://localhost:8080/'
//   });
  
//   axiosInstance.interceptors.request.use(
//     config => {
//       const token = localStorage.getItem('token');
//       if (token) {
//         config.headers.Authorization = `Bearer ${token}`;
//       }
//       return config;
//     },
//     error => {
//       return Promise.reject(error);
//     }
//   );
  
//   Vue.prototype.$axios = axiosInstance;

const app = createApp(App);

app.use(router);

app.mount("#app");
