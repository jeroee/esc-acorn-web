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
        v-model="firstName"
        style="width: 400px"
        color="green"
        outlined="true"
        label="Insert your first name"
      ></v-text-field>
      <v-text-field
        v-model="lastName"
        style="width: 400px"
        color="green"
        outlined="true"
        label="Insert your last name"
      ></v-text-field>

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
import Menu from "../components/Menu";
import ManyCard from "../components/ManyCard";
import FirstPagePic from "../components/FirstPagePic"; // this is the popup table

export default {
  components: { FirstPagePic, Menu, ManyCard },

  name: "Home",
  data: () => ({
    categories: ["Acorn Products", "Acorn Services", "Acorn Applications"],
    selected: "",
    firstName: "",
    lastName: ""
  }),
  computed: {
    isSelected() {
      return this.selected === "";
    }
  },
  methods: {
    alert: function() {
      alert("Coming Soon");
    },
    requestChat: async function() {
      if (this.selected && this.firstName != "" && this.lastName != "") {
        this.$store.state.categoryIndex = this.categories.indexOf(
          this.selected
        );
        this.$store.state.categoryName = this.selected;
        this.$store.state.firstName = this.firstName;
        this.$store.state.lastName = this.lastName;
        this.$router.push({ path: "/chat" });
      } else {
        alert("Please key in valid Name");
      }
    },
    // showAlert: function(){
    //     const options = {title: 'Info', size: 'sm'}
    //     dialog.alert('Your message', options)
    //     .then(res => {
    //     console.log(res) // {ok: true|false|undefined}
    // })
    requestCall: function() {
      if (this.selected && this.firstName != "" && this.lastName != "") {
        this.$store.state.categoryIndex = this.categories.indexOf(
          this.selected
        );
        this.$store.state.categoryName = this.selected;
        this.$router.push({ path: "/call" });
      } else {
        alert("Please key in valid Name");
      }
    }
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
