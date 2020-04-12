import Vue from 'vue'
import Router from 'vue-router'
import Homepage from "../views/Homepage";
import { store } from './store.js';

Vue.use(Router)

let router = new Router({
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
            component: () => import('../views/Chatpage.vue')
        },

        {
            path: '/WaitPage',
            name: 'WaitPage',
            component: () => import('../views/Waitpage.vue')
        },

        {
            path: '/call',
            name: 'call',
            props: true,
            component: () => import('../views/Call.vue')
        },

        {
            path: '/QandAPage1',
            name: 'QandAPage1',
            component: () => import('../views/QandAPage1.vue')
        },

        {
            path: '/ThanksForSubmit',
            name: 'ThanksForSubmit',
            component: () => import('../components/ThanksForSubmit.vue')
        },

        {
            path: '/FeedBack',
            name: 'FeedBack',
            component: () => import('../views/FeedBack.vue')
        },

        {
            path: '/ExitPopup',
            name: 'ExitPopup',
            component: () => import('../components/ExitPopup.vue')
        },

        {
            path: '/Pic',
            name: 'Pic',
            component: () => import('../components/Pic.vue')
        },

        {
            path: '/FirstPagePic',
            name: 'FirstPagePic',
            component: () => import('../components/FirstPagePic.vue')
        },

        {
            path: '/Menu',
            name: 'Menu',
            component: () => import('../components/Menu.vue')
        },

        {
            path: '/ProductCard',
            name: 'ProductCard',
            component: () => import('../components/ProductCard.vue')
        },

        {
            path: '/ManyCard',
            name: 'ManyCard',
            component: () => import('../components/ManyCard.vue')
        },

        {
            path: '/Popmotion',
            name: 'Popmotion',
            component: () => import('../views/Popmotion.vue')
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
    if (to.name == 'reivew' || to.name == 'call' && from.name == 'chat' && !store.state.chatStop) {
        next({ name: 'home'});
    }
    else{
        next();
    }
    if ((to.name == 'chat' || to.name == 'call') && from.name == 'Feedback' && !store.state.feedback) {
                next({ name: 'home'});
            }
});





export default router;
