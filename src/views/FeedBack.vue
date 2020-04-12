<template>
  <v-container>
    <h1 class="font-weight-light ma-10 text-center">Help us improve our customer service</h1>
    <v-card>
      <v-toolbar dark color="blue">
        <v-btn icon dark to="/">
          <v-icon>close</v-icon>
        </v-btn>
        <v-toolbar-title>Rate your experience with {{agentName}}</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-toolbar-items>
          <v-btn to="/" text>
            Skip
            <v-icon right>redo</v-icon>
          </v-btn>
        </v-toolbar-items>
      </v-toolbar>

      <!-- Write your opinions  -->
      <v-card>
        <v-card-text>
          <v-container>
            <v-row>
              <v-list-item cols="100" sm="6">
                <h3 class="font-weight-regular">How helpful was this session?</h3>
                <v-rating
                  style="position: absolute; right: 0"
                  hover
                  :size="40"
                  clearable
                  v-model="rating1"
                />
              </v-list-item>
              <v-list-item cols="12" sm="6">
                <h3 class="font-weight-regular">How was our service?</h3>
                <v-rating
                  style="position: absolute; right: 0"
                  hover
                  :size="40"
                  clearable
                  v-model="rating2"
                />
              </v-list-item>
              <v-list-item cols="12" sm="6">
                <h3 class="font-weight-regular">How was the quality of the chat/call experience?</h3>
                <v-rating
                  style="position: absolute; right: 0"
                  hover
                  :size="40"
                  clearable
                  v-model="rating3"
                />
              </v-list-item>
              <v-col cols="12">
                <v-textarea
                  background-color="white"
                  :rows="1"
                  auto-grow
                  label="Additional Comments"
                  v-model="comments"
                  hint="Any additional feedback will be greatly appreciated!"
                />
              </v-col>
              <v-col cols="12">
                <v-text-field
                  v-model="email"
                  label="Email"
                  hint="Leave us your email if you would like us to get back to you."
                ></v-text-field>
              </v-col>
              <v-btn @click="sendDetails" text x-large width="100%">
                <h3>Submit</h3>
              </v-btn>
            </v-row>
          </v-container>
        </v-card-text>
      </v-card>
    </v-card>
  </v-container>
</template>


<script>
import axios from "axios";
export default {
  data: () => ({
    rating1: "",
    rating2: "",
    rating3: "",
    comments: "",
    email: "",
    feedback: false
  }),
  computed: {
    agentName() {
      return this.$store.state.agentName;
    },
    agentId() {
      return this.$store.state.agentId;
    }
  },
  created() {
    console.log(this.agentName);
    console.log(this.agentId);
  },
  mounted() { //to prevent back button from working to access chats/calls
    history.pushState(null, null, location.href);
    window.onpopstate = function() {
      history.go(1);
    };
  },

  methods: {
    sendDetails: async function() {
      // let response= await axios.patch(
      //     `https://still-sea-41149.herokuapp.com/api/review`,
      //     {
      //         'agentId' :this.agentId,
      //         'raiting1' :this.rating1,
      //         'raiting2' :this.rating2
      //     }
      // );
      await axios.patch(
        `https://still-sea-41149.herokuapp.com/api/review?agentId=${this.agentId}&rating1=${this.rating1}&rating2=${this.rating2}&rating3=${this.rating3}&email=${this.email}&comment=${this.comments}`
      );

      console.log("updated!!");
      this.$store.state.connectionType = true;
      //maybe can insert a pop up
      await this.popUp();
    },
    popUp: function() {
      this.$router.push({ path: "/" });
    }
  }
};
</script>
