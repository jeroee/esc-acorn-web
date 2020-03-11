<template>
  <div class="chat">
    <h1>Hey, lets chat</h1>
    <p>Category selected is {{selectedIndex}}</p>
    <!-- <li v-for="post in posts" :key="post.body">
      <br />
      {{post.id}}
      <br />
      {{post.title}}
      <br />
      {{post.body}}
      <br />
      {{post.body}} -->
    <!-- </li> -->
  </div>
</template>

<script>
import rainbowSDK from "rainbow-web-sdk";
import axios from "axios";
export default {
  name: "Chat",
  props: ["selectedIndex"],
  data() {
    // return {
    //   posts: []
    // }
  },
created() {
    //somehow initialise doesnt complete fully to trigger RAINBOW_ONREADY, hence run getConnection;
    //document.addEventListener(rainbowSDK.RAINBOW_ONREADY, this.getConnection);
    document.addEventListener(rainbowSDK.RAINBOW_ONLOADED, this.onLoaded);
    rainbowSDK.load();
    this.getConnection();
  },
  methods: {
    onLoaded: function() {
      var applicationID = "a58cfac05b0711eabf7e77d14e87b936";
      var applicationSecret =
        "JnjQaOpCW9Pc3u2IUQAvyjyiAEINpBo47Vb5S3jSUxHdgQkc3pqFFXGHJPojXbGu";
      rainbowSDK.setVerboseLog(false);
      rainbowSDK
        .initialize(applicationID, applicationSecret)
        .then(function() {
          console.log("[DEMO] :: Rainbow SDK is initialized!");
        })
        .catch(function(err) {
          console.log("[DEMO] :: Something went wrong with the SDK...", err);
        });

      console.log("I'm in onLoaded");
    },

    getConnection: async function() {
      console.log("I'm in waitConnection");
    //   var strLogin = "aaronkhoo@live.com";
    //   var strPassword = "6]<epFf$Er'0";
      let response = await axios.get(
        `http://still-sea-41149.herokuapp.com/api/agentss?category=${this.selectedIndex}`  //obtain agent through category
      );
      let agent_id = response.data.agent.rainbowId;   //get agent id
      let agent_name = response.data.agent.name;      //get agent name
      let token = response.data.token;                //get guest token
      console.log("agent ID is: ", agent_id);
      console.log("agent name is: ", agent_name);
      console.log("token is: ",token);
      //need to swap signin with token instead of admin login and password
      let account = await rainbowSDK.connection.signinSandBoxWithToken(token);           //login to rainbow server with guest token
      if (account) {
        console.log("sign in success");
        let contact = await rainbowSDK.contacts.searchById(agent_id);                    //get contact from agent id
        let conversation = await rainbowSDK.conversations.openConversationForContact(   //open conversation from contact
          contact
        );
        await rainbowSDK.im.getMessagesFromConversation(conversation);                  //getting all messages from conversation
        console.log(conversation);

        document.addEventListener(
          rainbowSDK.im.RAINBOW_ONNEWIMMESSAGERECEIVED,
          this.receive
        );
        document.addEventListener(
          rainbowSDK.im.RAINBOW_ONNEWIMRECEIPTRECEIVED,
          this.receipt
        );
        let message = "Test message";                                                   //sample msg send to agent
        await rainbowSDK.im.sendMessageToConversation(conversation, message);
        console.log("message sent");

      } else {
        console.log("No account found!");
      }
    },

    //sendMessage: async function() {
      //testing send message
    //   let message = "Test message";
    //   await rainbowSDK.im.sendMessageToConversation(conversation, message);
    //   console.log("message sent");
   // },

    receive: function(e) {
      let message = e.detail.message;
      console.log(message);
    },

    receipt: function(e) {
      let message = e.detail.message.data;
      console.log(message);
    }
  }
};
</script>

<style scoped>
</style>