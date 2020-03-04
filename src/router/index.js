import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Chatbot from '../views/Chatbot.vue';
import FirstPage from './FirstPage.vue';

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {path: '/Chatbot', component: Chatbot},
  {path: '/FirstPage', component: FirstPage},


  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
