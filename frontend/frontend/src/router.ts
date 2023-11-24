import { createRouter, createWebHistory } from "vue-router";
import Main from "./views/MainView.vue";
import GetStarted from "./views/GetStarted.vue";

export default createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes: [
        {
            path: "/",
            name: "home",
            component: Main,
            meta: {},
        },
        {
            path: "/getstarted",
            name: "getstarted",
            component: GetStarted,
        },
        { path: "/:catchAll(.*)*", redirect: "/" }, //catchAll Route
    ],
});
