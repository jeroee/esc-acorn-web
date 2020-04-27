import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'

//enables axios plugin to be used in Vue
Vue.use(VueAxios, axios);

export default new Axios({
});
