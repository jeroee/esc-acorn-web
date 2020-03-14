<template>
  <div class="chat">
    <h1>Hey, lets chat</h1>
    <p>Category selected is {{selectedIndex}}</p>
    <input v-model="messagelog" placeholder="type here to start texting" />
    <v-btn @click.native="sendMessage">Click me to send</v-btn>
    <ul v-for="message in messages" :key="message.id">
      <li>{{message}}</li>
    </ul>
  </div>
</template>

<script>
import rainbowSDK from "rainbow-web-sdk";
import axios from "axios";
export default {
  name: "Chat",
  // props: ["selectedIndex"],
  data: () => ({
    messages:[],
    selectedIndex: 0,
    messagelog:'',
    conversation:'',
    // }
  }),
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
        `http://still-sea-41149.herokuapp.com/api/agentss?category=${this.selectedIndex}` //obtain agent through category
      );
      let agent_id = response.data.agent.rainbowId; //get agent id
      let agent_name = response.data.agent.name; //get agent name
      let token = response.data.token; //get guest token
      console.log("agent ID is: ", agent_id);
      console.log("agent name is: ", agent_name);
      console.log("token is: ", token);
      //need to swap signin with token instead of admin login and password
      let account = await rainbowSDK.connection.signinSandBoxWithToken(token); //login to rainbow server with guest token
      if (account) {
        console.log("sign in success");
        let contact = await rainbowSDK.contacts.searchById(agent_id); //get contact from agent id
        this.conversation = await rainbowSDK.conversations.openConversationForContact(
          //open conversation from contact
          contact
        );
        await rainbowSDK.im.getMessagesFromConversation(this.conversation); //getting all messages from conversation
        console.log(this.conversation);

        document.addEventListener(
          rainbowSDK.im.RAINBOW_ONNEWIMMESSAGERECEIVED,
          this.receive
        );
        document.addEventListener(
          rainbowSDK.im.RAINBOW_ONNEWIMRECEIPTRECEIVED,
          this.receipt
        );
        // let message = "Test message"; //sample msg send to agent
        // await rainbowSDK.im.sendMessageToConversation(this.conversation, message);
        // console.log("message sent");
      } else {
        console.log("No account found!");
      }
    },

    sendMessage:function() {
      //testing send message
      let message = this.messagelog;
      if (message!=''){ 
             rainbowSDK.im.sendMessageToConversation(this.conversation, message);
             this.messages.push("customer says:    "+message);
      }
      console.log("message sent");
      this.messagelog='';
    },

    receive: function(event) {     //this function works when u receive a message
      let message = event.detail.message;
      console.log(message.data);
      this.messages.push("agent says:    "+message.data);
      console.log(message.side)
    },

    receipt: function(event) {      //this function works when u send out a message
      let message = event.detail.message;
      console.log(message.data);
      console.log(message.side);
}
  }
};
</script>

<style scoped>
</style>    