import Vue from 'vue';
import Vuex from 'vuex';

//Enables Vuex plugin to be used in Vue
Vue.use(Vuex);

export const store = new Vuex.Store({

    //Vuex Store stores state of the current data, will change depending on what is being updated in the Vue Components
    state: {
        categoryIndex: 0,       //Support help category indexed [0-5]
        categoryName: "",       //Support help category name ["Acorn Products", "Acorn Services", "Acorn Applications", "Acorn Pay", "Acorn ID"]
        agentName:"",           //Agent Name
        firstName:"",           //Customer's First Name
        lastName:"",            //Customer's Last Name
        agentId:"",             //Agent ID
        token:"",               //Rainbow Guest Token of Customer
        support: false,         //Nav guard
        feedback: false,        //Nav guard
        moving:false,           //Nav guard
    },
});
