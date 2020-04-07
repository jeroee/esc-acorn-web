import Vue from 'vue';
import App from './App.vue';
import vuetify from './plugins/vuetify';
import router from './plugins/router';
import {store} from './plugins/store';

Vue.config.productionTip = false;


new Vue({
  store:store,
  vuetify,router,
  render: h => h(App)     //which page to link
}).$mount('#app');        //refered name

