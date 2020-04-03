import Vue from 'vue';
import Vuetify from 'vuetify/lib';
import VuePageTransition from 'vue-page-transition';

Vue.use(Vuetify,{
    iconfont: 'md',
    theme: {
        primary: '#9652ff',
        success: '#3cd1c2',
        info: '#ffaa2c',
        error: '#f83e70'


    }
});
Vue.use(VuePageTransition);

export default new Vuetify({
});
