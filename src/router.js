import Vue from 'vue'
import Router from 'vue-router'
import Homepage from "./views/Homepage";
// import Chatpage from "./views/Chatpage";
// import Waitpage from "./views/Waitpage";
// import Call from "./views/Call"

Vue.use(Router);

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
            path: '/chat',
            name: 'chat',
            component: () => import('./views/Chatpage.vue')
        },

        {
            path: '/WaitPage',
            name: 'WaitPage',
            component: () => import('./views/Waitpage.vue')
        },

        {
            path: '/call',
            name: 'call',
            props: true,
            component: () => import('./views/Call.vue')
        },

        {
            path: '/QandAPage1',
            name: 'QandAPage1',
            component: () => import('./views/QandAPage1.vue')
        },

        {
            path: '/FeedBack',
            name: 'FeedBack',
            component: () => import('./views/FeedBack.vue')
        },

        {
            path: '/Try',
            name: 'Try',
            component: () => import('./views/Try.vue')
        }

    ]
})
