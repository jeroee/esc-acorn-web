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
            component: () => import('../views/ChatPage.vue')
        },

        {
            path: '/call',
            name: 'call',
            props: true,
            component: () => import('../views/CallPage.vue')
        },

        {
            path: '/feedback',
            name: 'feedback',
            component: () => import('../views/FeedBack.vue')
        },
    ]
});

router.beforeEach((to, from, next) => {
    //prevent people from entering call, chat, review pages directly
    if ((to.name === 'call' || to.name === 'chat') && !store.state.support && !store.state.feedback) {
        next({name:'home'});
        alert("Please do not navigate to the chat/call page by URL");
    } else if ( to.name === 'feedback' && !store.state.feedback) {
        next({ name: 'home' });
        alert("Please do not navigate to the feedback page by URL");
    } else {
        next();
    }

    //prevent people from going back to the home page from the chat or call pages
    if ((from.name === 'chat' || from.name === 'call') && to.name === 'home' && store.state.support) {
        alert("Please do not use the back button to go back to the home page.");
        next(from.name);
    }

    //prevent people from going back to the chat page from the call page (cannot prevent both ways, so protecting more important transition)
    if (from.name === 'call' && to.name === 'chat' && !store.state.moving) {
        alert("Please do not use the back button to switch between chat and call.");
        next({ name: 'call' });
    }

    //prevent people from going back to the chat and call page from feedback
    if ((to.name === 'chat' || to.name === 'call') && from.name === 'feedback') {
        next(from.name);
        alert("Please start a new support session!")
    }
});


export default router;
