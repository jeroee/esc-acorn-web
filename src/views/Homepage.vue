<template>
  <div style="margin-top:-5px">
    <!-- <div class="hero mb-10">
            <v-container class="flex-col">
                <h1 class="display-3 font-weight-medium">Welcome to Acorn Support</h1>
            </v-container>

    </div>-->

    <FirstPagePic></FirstPagePic>
    <Menu></Menu>
    <br />
    <br />
    <v-container class="flex-col">

       <v-text-field 
        id="FirstName" 
        v-model="name1"
        style="width: 400px"
        color="green"
        outlined="true"
        :error-messages="name1Errors"
        :counter="30"
        label="Insert your first name"
        required
        @input="$v.name1.$touch()"
        @blur="$v.name1.$touch()"></v-text-field>


      <v-text-field 
        id="LastName" 
        v-model="name2"
        style="width: 400px"
        color="green"
        outlined="true"
        :error-messages="name2Errors"
        :counter="30"
        label="Insert your last name"
        required
        @input="$v.name2.$touch()"
        @blur="$v.name2.$touch()"></v-text-field>

      

      <v-select
        id="category"
        v-model="selected"
        style="width: 400px"
        :items="categories"
        outlined
        color="green"
        :menu-props="{ offsetY:true, openOnClick:false }"
        label="Choose a support category..."
      ></v-select>

      <div style="flex-direction: row">
        <v-btn
          @click="requestChat"
          x-large
          depressed
          class="ma-5 green white--text"
          :disabled="isSelected" 
        >
          <v-icon left>message</v-icon>Get Chat Support
        </v-btn>

        <v-btn
          @click="requestCall"
          x-large
          depressed
          class="ma-5 green white--text"
          :disabled="isSelected" 
        >
          <v-icon left>phone</v-icon>Get Call Support
        </v-btn>

       

      </div>

      <ManyCard></ManyCard>
    </v-container>
  </div>
</template>

<script>
// import popup from '../components/popup' // this is the popup table
import { required, maxLength} from 'vuelidate/lib/validators'
import Vue from 'vue'
import Vuelidate from 'vuelidate'
import { validationMixin } from 'vuelidate'
Vue.use(Vuelidate)
import Menu from "../components/Menu";
import ManyCard from "../components/ManyCard";
import FirstPagePic from "../components/FirstPagePic"; // this is the popup table


export default {
  components: { FirstPagePic, Menu, ManyCard },

  name: "Home",
  mixins: [validationMixin],

  validations: {
    name1: { required, maxLength: maxLength(30) },
    name2: { required, maxLength: maxLength(30) },
  },

  

  data: () => ({
    categories: ["Acorn Products", "Acorn Services", "Acorn Applications"],
    selected: "",
    firstName: "",
    lastName: "",
    name1:"",
    name2:""
    
  }),
  computed: {
    isSelected() {
      if(this.name2 =="" || this.name1 == "" || this.selected == ""){
        return true
      }
      return false;
    },
    name1Errors() {
      const errors = [];
      if (!this.$v.name1.$dirty) return errors;
      !this.$v.name1.maxLength && errors.push('Name must be at most 10 characters long');
      !this.$v.name1.required && errors.push('Name is required.');
      return errors;
    },
    name2Errors() {
      const errors = [];
      if (!this.$v.name2.$dirty) return errors;
      !this.$v.name2.maxLength && errors.push('Name must be at most 30 characters long');
      !this.$v.name2.required && errors.push('Name is required.');
      return errors;
    }

  },
  methods: {
    alert: function() {
      alert("Coming Soon");
    },

    submit() {
    this.$v.$touch();
    },
   

        requestChat: function () {
                  if (this.selected) {
                      this.$store.state.categoryIndex = this.categories.indexOf(this.selected);
                      this.$store.state.categoryName = this.selected;
                      this.$router.push({path: "/chat"});
                  }
              },

        requestCall: function () {
            if (this.selected ) {
                this.$store.state.categoryIndex = this.categories.indexOf(this.selected);
                this.$store.state.categoryName = this.selected;
                this.$router.push({path: "/call"});
            }
        },


  }
};
</script>

<style scoped>
.flex-col {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
}
/* .hero{
    background-image: url("../assets/acorn_bg.png");
    height: 400px;
    background-position: center;
    background-size: cover;
} */
</style>
