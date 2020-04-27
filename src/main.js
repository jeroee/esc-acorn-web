import Vue from 'vue';
import App from './App.vue';
import vuetify from './plugins/vuetify';
import router from './plugins/router';
import {store} from './plugins/store';
import VueSocketIO from 'vue-socket.io';

/*
This method mounts the web socket instance using the vue-socket.io wrapper.
On connection, it provides the applicationSignature string as authentication during the handshake.
This simulates an application signature which may either be generated earlier on or even computed dynamically from hashing, etc
*/
const applicationSignature= "BBO5e7IVtK9TeSAQ3RTYGsQOWOZ0QAe8k9jbvomydoOUEjK1lwTLIkK4J3yu";
Vue.use(new VueSocketIO({
    debug: true,
    connection: "https://esc-acorn-backend.herokuapp.com/",
    options: { autoConnect: false, query: { key: applicationSignature }}
}));

Vue.config.productionTip = false;
Vue.config.performance = true;
/*
This injects all the different plugins (Vuetify, Vuex, Vue-Router) used in the app.
*/
new Vue({
    store:store,
    vuetify,router,
    render: h => h(App)
}).$mount('#app');

