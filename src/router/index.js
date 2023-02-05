import { createRouter, createWebHistory } from 'vue-router'

import MainPage from "@/components/MainPage.vue";
import CatalogPage from "@/components/CatalogPage.vue";
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
            path: "/catalog",
            name: "CatalogPage",
            component: CatalogPage,
        }
    ],
})
export default router;