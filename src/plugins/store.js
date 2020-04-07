import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export const store = new Vuex.Store({
    state: {
        categoryIndex: 0,
        categoryName: "",
        agentName:"Placeholder Name",
        firstName:"",
        lastName:"",
        agentId:""
    },
});
