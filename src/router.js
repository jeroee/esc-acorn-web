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
        }
    ]
})
