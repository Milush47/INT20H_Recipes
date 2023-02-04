import { createRouter, createWebHistory } from 'vue-router'

import MainPage from "@/components/MainPage.vue";
import Profile from "@/components/Profile.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/",
            name: "MainPage",
            component: MainPage,
        },
        {
            path: "/profile",
            name: "Profile",
            component: Profile,
        },
    ],
})
export default router;