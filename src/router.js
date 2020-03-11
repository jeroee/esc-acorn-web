import Vue from 'vue'
import Router from 'vue-router'
import Homepage from "./views/Homepage";
import Chat from "./views/Chat";
import Call from "./views/Call"

Vue.use(Router);

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/home',
            name: 'home',
            component: Homepage
        },
        {
            path: '/',
            name: 'about',
            // route level code-splitting
            // this generates a separate chunk (about.[hash].js) for this route
            // which is lazy-loaded when the route is visited.
            component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
        },
        {
            path: '/chat/:selectedIndex',
            name: 'chat',
            component: Chat,
            props: true
        },
        {
            path: '/call/:selectedIndex',
            name: 'call',
            component: Call,
            props: true
        }
    ]
})
