import Vue from 'vue';
import Vuetify from 'vuetify/lib';
import VuePageTransition from 'vue-page-transition';
import 'material-design-icons-iconfont/dist/material-design-icons.css';

//enable vuetify plugin to be used in Vue
Vue.use(Vuetify);
Vue.use(VuePageTransition);

export default new Vuetify({
    icons: {
        iconfont: 'md',
    },
})
