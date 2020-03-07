<template>
    <div class="chat">
        <h1>Hey, lets chat</h1>
        <li v-for= "post in posts" :key="post.body">
            <br>
            {{post.id}}
            <br>
            {{post.title}}
            <br>
            {{post.body}}
            <br>
            <!-- {{post.body}} -->
        </li>
    </div>
</template>

<script>
    import rainbowSDK from 'rainbow-web-sdk'
   import axios from 'axios';
    export default {
        name: "Chat",
        data(){
            return {
                posts:[]
            }
        },
    created(){
        console.log("page created");

            // Activate full SDK log
        rainbowSDK.setVerboseLog(false);
        var applicationID = "a58cfac05b0711eabf7e77d14e87b936",
        applicationSecret = "JnjQaOpCW9Pc3u2IUQAvyjyiAEINpBo47Vb5S3jSUxHdgQkc3pqFFXGHJPojXbGu";
        rainbowSDK
            .initialize(applicationID, applicationSecret)
            .then(function() {
                console.log("[DEMO] :: Rainbow SDK is initialized!");
            })
            .catch(function(err) {
                console.log("[DEMO] :: Something went wrong with the SDK...", err);
            });

        console.log("[DEMO] :: On SDK Ready !");
        // do something when the SDK is ready
        var strLogin = "aaronkhoo@live.com";        
        var strPassword = "6]<epFf$Er'0"; 
        rainbowSDK.connection.signin(strLogin, strPassword)
        .then(function(account) {
            console.log("sign in success!");
            console.log(account)
        })
        .then(function(){
        rainbowSDK.contacts.searchById("5e4950b6e9f12730636972b5")      //agent 5
        .then(function(entityFound) {
            console.log("entered search")
            if(entityFound) {
                console.log("found entity !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                console.log(entityFound);  
                rainbowSDK.conversations.openConversationForContact(entityFound)
                .then(function(conversations){
                console.log("opening conversation");
                console.log(conversations)
                })
                .catch(function(err){
                console.log(err)
                })
            }
            else {
                console.log("no entitiy!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            }
        })
        })
        .catch(function(err) {
            console.log("error signing in");
            console.log(err)
        })
        axios.get(`http://jsonplaceholder.typicode.com/posts`)
             .then(response => {this.posts = response.data})
          
    // rainbowSDK.contacts.searchById("5e4950b6e9f12730636972b5")
    // .then(function(entityFound) {
    //   console.log("entered search")
    //   if(entityFound) {
    //       console.log("found entity !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    //       console.log(entityFound);          
    //       // rainbowSDK.conversations.openCoversationForContact(entityFound)
    //       // .then(function(conversations){
    //       //   console.log("opening conversation");
    //       //   console.log(conversations)
    //       // })
    //       // .catch(function(err){
    //       //   console.log(err)
    //       // })
    //   }
    //   else {
    //       console.log("no entitiy!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
    //   }
    // })
    // .catch(function(err){
    //   console.log(err);
    // });
    //rainbowSDK.conversations.openCoversationforContact()


    



    /* Bootstrap the SDK */
    //angular.bootstrap(document, ["sdk"]).get("rainbowSDK");

    /* Callback for handling the event 'RAINBOW_ONREADY' */
  //   var onReady = function onReady() {
  //       console.log("[DEMO] :: On SDK Ready !");
  //       //do something when the SDK is ready
  //       var strLogin = "aaronkhoo@live.com";       
  //       var strPassword = "6]<epFf$Er'0"; 
  //       rainbowSDK.connection.signin(strLogin, strPassword)
  //       .then(function(account) {
  //             console.log("sign in success!");
  //             console.log(account);
  //       })
  //       .catch(function(err) {
  //           console.log("error signing in");
  //           console.log(err);
  //       })
  //   };

  //   /* Callback for handling the event 'RAINBOW_ONCONNECTIONSTATECHANGED' */
  //   var onLoaded = function onLoaded() {
  //       console.log("[DEMO] :: On SDK Loaded !");

  //       // Activate full SDK log
  //       rainbowSDK.setVerboseLog(true);

  //       rainbowSDK
  //           .initialize(applicationID, applicationSecret)
  //           .then(function() {
  //               console.log("[DEMO] :: Rainbow SDK is initialized!");
  //           })
  //           .catch(function(err) {
  //               console.log("[DEMO] :: Something went wrong with the SDK...", err);
  //           });
  //   };

  //   /* Listen to the SDK event RAINBOW_ONREADY */
  //   document.addEventListener(rainbowSDK.RAINBOW_ONREADY, onReady)

  //   /* Listen to the SDK event RAINBOW_ONLOADED */
  //   document.addEventListener(rainbowSDK.RAINBOW_ONLOADED, onLoaded)

  //   /* Load the SDK */
  //   rainbowSDK.load();
  }

  
};
            
//              rainbowSDK.contacts.searchById("5e495058e9f1273063697294").then(function(entityFound) {
//                 if(entityFound) {
//                     console.log("found entity !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
//                 }
//                 else {
//                     console.log("no entitiy!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
//                 }
//             })

//             axios.get(`http://jsonplaceholder.typicode.com/posts`)
//             .then(response => {this.posts = response.data})
            
//         }
//     }
</script>



<style scoped>

</style>