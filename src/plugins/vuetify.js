import Vue from 'vue';
import Vuetify from 'vuetify/lib';
import VuePageTransition from 'vue-page-transition';
import 'material-design-icons-iconfont/dist/material-design-icons.css';

Vue.use(Vuetify);
Vue.use(VuePageTransition);

export default new Vuetify({
    icons: {
        iconfont: 'md',
    },
})
