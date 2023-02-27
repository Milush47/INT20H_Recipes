import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'

axios.defaults.baseURL = "http://localhost:8080/";
axios.defaults.headers.common["Authorization"] = "Bearer " + localStorage.getItem("token");

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import './assets/main.css'

const app = createApp(App);

app.use(router);

app.mount("#app");

