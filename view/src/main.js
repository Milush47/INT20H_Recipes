import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

import "./assets/main.css";
import setAuthHeader from "./service/setAuthHeader";

if(localStorage.token) {
    setAuthHeader(localStorage.token);
} else {
    setAuthHeader(false);
}

const app = createApp(App);

app.use(router);

app.mount("#app");
