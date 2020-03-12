import Vue from 'vue'
import Router from 'vue-router'
import Homepage from "./views/Homepage";

Vue.use(Router);

console.log("router loaded");

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            name: 'home',
            component: Homepage
        },
        {
            path: '/wait',
            name: 'wait',
            // route level code-splitting
            // this generates a separate chunk (chat.[hash].js) for this route
            // which is lazy-loaded when the route is visited.
            component: () => import(/* webpackChunkName: "chat" */ './views/Waitpage.vue')
        },
        {
            path: '/chat',
            name: 'chat',
            component: () => import('./views/Chatpage.vue')
        }
    ]
})
