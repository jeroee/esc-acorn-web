import Vue from 'vue'
import Router from 'vue-router'
import { store } from './store.js';

Vue.use(Router);

let router = new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            name: 'home',
            component: () => import('../views/Homepage')
        },
        {
            path: '/chat',
            name: 'chat',
            component: () => import('../views/Chatpage.vue')
        },

        {
            path: '/call',
            name: 'call',
            props: true,
            component: () => import('../views/CallPage.vue')
        },

        {
            path: '/FeedBack',
            name: 'FeedBack',
            component: () => import('../views/FeedBack.vue')
        },
    ]
});

router.beforeEach((to, from, next) => {
    if (to.name !== 'home' && !store.state.support) {
        next({ name: 'home' });
    } else {
        next();
    }
});

router.beforeEach((to, from, next) => {
    if (to.name === 'review' || to.name === 'call' && from.name === 'chat' && !store.state.chatStop) {
        next({ name: 'home'});
    }
    else{
        next();
    }
    if ((to.name === 'chat' || to.name === 'call') && from.name === 'Feedback' && !store.state.feedback) {
                next({ name: 'home'});
            }
});



export default router;
