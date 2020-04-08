import Vue from 'vue';
import App from './App.vue';
import vuetify from './plugins/vuetify';
import router from './plugins/router';
import {store} from './plugins/store';
import VueSocketIO from 'vue-socket.io';

Vue.use(new VueSocketIO({
  debug: true,
  connection: "https://esc-acorn-backend.herokuapp.com/",
  options: { autoConnect: false }
}));

Vue.config.productionTip = false;

new Vue({
  store:store,
  vuetify,router,
  render: h => h(App)     //which page to link
}).$mount('#app');        //refered name

