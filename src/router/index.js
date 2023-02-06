import { createRouter, createWebHistory } from 'vue-router'

import MainPage from "@/components/MainPage.vue";
import CatalogPage from "@/components/CatalogPage.vue";
import Profile from "@/components/Profile.vue";
import Login from "@/components/LoginPage.vue";
import Register from "@/components/RegisterPage.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/",
            name: "MainPage",
            component: MainPage,
        },
        {
            path: "/catalog",
            name: "CatalogPage",
            component: CatalogPage,
        },
        {
            path: "/profile",
            name: "Profile",
            component: Profile,
        },
        {
            path: "/login",
            name: "LoginPage",
            component: Login,
        },
        {
            path: "/auth/register",
            name: "RegisterPage",
            component: Register,
        }
    ],
})
export default router;