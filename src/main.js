import Vue from 'vue';
import App from './App.vue';
import vuetify from './plugins/vuetify';
import router from './plugins/router';
import {store} from './plugins/store';
import VueSocketIO from 'vue-socket.io';

/*
This method mounts the web socket instance using the vue-socket.io wrapper.
On connection, it provides the socketKey string as authentication during the handshake.
The socketKey simulates a pre-agreed application signature.
*/
const socketKey= "BBO5e7IVtK9TeSAQ3RTYGsQOWOZ0QAe8k9jbvomydoOUEjK1lwTLIkK4J3yu";
Vue.use(new VueSocketIO({
    debug: true,
    connection: "http://localhost:4000",
    // "https://esc-acorn-backend.herokuapp.com/",
    options: { autoConnect: false, query: { key: socketKey }}
}));

Vue.config.productionTip = false;
/*
This injects all the different plugins (Vuetify, Vuex, Vue-Router) used in the app.
*/
new Vue({
    store:store,
    vuetify,router,
    render: h => h(App)
}).$mount('#app');

