<template>
  <div class="call">
    <h1>Call me</h1>
  </div>
</template>

<script>
// import rainbowSDK from "rainbow-web-sdk";
// import axios from "axios";

export default {
  name: "Call"
//   props: ["selectedIndex"],

//   created() {
    
//     //document.addEventListener(rainbowSDK.RAINBOW_ONREADY, this.getConnection);
//     document.addEventListener(rainbowSDK.RAINBOW_ONLOADED, this.onLoaded);

//     rainbowSDK.load();
//     this.getConnection();
//   },
//   methods: {
//     onLoaded: function() {
//       var applicationID = "a58cfac05b0711eabf7e77d14e87b936";
//       var applicationSecret =
//         "JnjQaOpCW9Pc3u2IUQAvyjyiAEINpBo47Vb5S3jSUxHdgQkc3pqFFXGHJPojXbGu";
//       rainbowSDK.setVerboseLog(false);
//       // //   await rainbowSDK.initialize(applicationID, applicationSecret);
//       rainbowSDK
//         .initialize(applicationID, applicationSecret)
//         .then(function() {
//           console.log("[DEMO] :: Rainbow SDK is initialized!");
//         })
//         .catch(function(err) {
//           console.log("[DEMO] :: Something went wrong with the SDK...", err);
//         });

//       console.log("I'm in onLoaded");
//     },

//     getConnection: async function() {
//       //   rainbowSDK.initialize()
//       console.log("I'm in waitConnection");
//     //   var strLogin = "aaronkhoo@live.com";
//     //   var strPassword = "6]<epFf$Er'0";
//       let response = await axios.get(
//         `http://still-sea-41149.herokuapp.com/api/agentss?category=${this.selectedIndex}`
//       );
//       let agent_id = response.data.agent.rainbowId;
//       let agent_name = response.data.agent.name;
//       let token = response.data.token;
//       //let token = response.data.
//       console.log("agent ID is: ", agent_id);
//       console.log("agent name is: ", agent_name);
//       console.log("token is: ",token);
//       //   let agent_id = "5e4950b6e9f12730636972b5";
//       //need to swap signin with token instead of admin login and password
//       let account = await rainbowSDK.connection.signinSandBoxWithToken(token);
//       if (account) {
//         console.log("sign in success");
//         let contact = await rainbowSDK.contacts.searchById(agent_id);
//         let conversation = await rainbowSDK.conversations.openConversationForContact(
//           contact
//         );
//         await rainbowSDK.im.getMessagesFromConversation(conversation);
//         console.log(conversation);

//         document.addEventListener(
//           rainbowSDK.im.RAINBOW_ONNEWIMMESSAGERECEIVED,
//           this.receive
//         );
//         document.addEventListener(
//           rainbowSDK.im.RAINBOW_ONNEWIMRECEIPTRECEIVED,
//           this.receipt
//         );
//         let message = "Test message";
//         await rainbowSDK.im.sendMessageToConversation(conversation, message);
//         console.log("message sent");

//       } else {
//         console.log("No account found!");
//       }
//     },

//     //sendMessage: async function() {
//       //testing send message
//     //   let message = "Test message";
//     //   await rainbowSDK.im.sendMessageToConversation(conversation, message);
//     //   console.log("message sent");
//    // },

//     receive: function(e) {
//       let message = e.detail.message;
//       console.log(message);
//     },

//     receipt: function(e) {
//       let message = e.detail.message.data;
//       console.log(message);
//     }
//   }
};
</script>

<style scoped>
</style>