<template>
    <div class= "call" id="call">
        <transition name="fade">
            <Waitpage v-bind:connecting="connecting" v-bind:loading="loading" v-if="!start"/>
        </transition>
        <h1 class="font-weight-light mb-5 header">Let's talk <v-icon x-large color="black">phone</v-icon></h1>
        <audio id="globalAudioTag" autoplay/>     <!--to allow customer to receive audio from agent-->
        <div class="callBox">
            <h1 class="font-weight-light agent-text">You're on the phone with {{agentName}}</h1>
            <Lottie :options="defaultOptions" :height="450" :width="450" style="margin-bottom: 100px" v-on:animCreated="handleAnimation"/>
        </div>
        <v-footer width="100%" padless>
            <v-btn @click="movetochat" height="58px" width="50%" x-large depressed tile class="green white--text">
                <h3><v-icon left>message</v-icon>Move To Chat</h3>
            </v-btn>
            <v-btn @click="endCall" height="58px" width="50%" x-large depressed tile class="red white--text">
                <h3>Leave Call</h3><v-icon right>input</v-icon>
            </v-btn>
        </v-footer>
    </div>
</template>

<script>
import Waitpage from "./Waitpage";
import rainbowSDK from "rainbow-web-sdk";
import Lottie from 'vue-lottie';
import animationData from '../assets/customer-support';
// import axios from "axios";
import io from 'socket.io-client';

export default {
    name: "Call",
    components:{Waitpage,Lottie},
    data: () => ({
        defaultOptions: {animationData: animationData},
        token: "", // String variable for guest account token
        start: false,
        connecting: false,
        cancelled: false,
        loading: 0,
        socket:"", // holds the socket object
        call: ""
    }),
    computed: {
        categoryIndex() {
            return this.$store.state.categoryIndex;
        },
        agentName() {
            return this.$store.state.agentName;
        },
        agentId() {
            return this.$store.state.agentId;
        },
        firstName(){
            return this.$store.state.firstName;
        },
        lastName(){
            return this.$store.state.lastName;
        }
    },
    mounted() {
        let self = this;
        self.checkCall();

        self.socket = io.connect('https://esc-acorn-backend.herokuapp.com/');
        // self.socket = io.connect('http://localhost:4000/');
        /**********************MOUNT ALL SOCKET METHODS HERE**********************/
        self.socket.on("handshake", function (data) {
            console.log(data);
            console.log("Socket.io getAgent");
            console.log(self.categoryIndex);
            console.log(self.firstName);
            console.log(self.lastName);
            self.socket?.emit("getAgent",{
                category: self.categoryIndex,
                firstName: self.firstName,
                lastName: self.lastName
            })
        });

        self.socket.on("getAgentSuccess", function (data) {
            console.log("Socket.io getAgentSuccess");
            self.$store.state.agentId = data.agentId;
            self.$store.state.agentName = data.agentName; //get agent name
            self.token = data.token; //get guest token
            console.log(`Your agentId is ${self.agentId}`);
            console.log(`Your agentName is ${self.agentName}`);
            console.log(`Your token is ${self.token}`);
            self.connecting=true;
            self.startCall();
        });


        // this.getConnection(); DEPRECATED
    },
    methods: {
        checkCall: function() {
            if (rainbowSDK.webRTC.canMakeAudioVideoCall()) {
                //check if browser is compatible for audio calls
                console.log("Browser supports calls");
            } else {
                console.log("Browser does not support calls");
                alert("Your browser does not support audio calls, please update to the latest version, or use a different browser!");
                this.$router.push({path: "/feedback"});
            }
            console.log("requesting microphone access");
            navigator.mediaDevices //authorise the application to access media device
                .getUserMedia({ audio: true })
                .then(function(stream) {
                    stream.getTracks().forEach(function(track) {
                        track.stop();
                    });
                    navigator.mediaDevices
                        .enumerateDevices()
                        .then(function(devices) {
                            devices.forEach(function(device) {
                                if (device.deviceId === "default") {
                                    console.log(device);
                                    console.log(device.label, "is available");
                                }
                            });
                        })
                        .catch(function(error) {
                            console.log(error);
                        });
                    rainbowSDK.webRTC.useMicrophone("default");
                    rainbowSDK.webRTC.useSpeaker("default");
                })
                .catch(function(error) {
                    console.log(error);
                });
        },

        /**********************INITIAL GET (DEPRECATED ON SOCKETING VERSION)**********************/
        // getConnection: async function() {
        //     let self=this;
        //     try {
        //         let response = await axios.get(
        //             `https://esc-acorn-backend.herokuapp.com/api/agents?category=${this.categoryIndex}` //obtain agent through category
        //         );
        //         self.agentId = response.data.agentId; //get agent id
        //         self.$store.state.agentName = response.data.agentName; //get agent name
        //         self.token = response.data.token; //get guest token
        //         console.log(this.agentId);
        //         console.log(`Your token is ${self.token}`);
        //         if (self.agentId!=="Null") {
        //             self.connecting=true;
        //             await self.startCall();
        //         } else {
        //             console.log("No account found! Retrying every x seconds");
        //             if (!self.cancelled) { //prevent polling in early exits
        //                 self.pollConnection();
        //             } else console.log("Load was left early");
        //         }
        //     } catch(err) {
        //         console.log(err);
        //     }
        // },

        /**********************POLLING GET (DEPRECATED ON SOCKETING VERSION)**********************/
        // pollConnection: function() {
        //     let self=this;
        //     self.polling=setInterval(async function () {
        //         try {
        //             let response = await axios.get(
        //                 `https://esc-acorn-backend.herokuapp.com/api/queue?token=${self.token}`
        //             );
        //             self.agentId = response.data.agentId; //get agent id
        //             self.$store.state.agentName = response.data.agentName; //get agent name
        //             // console.log(response);
        //             if (self.agentId!=="Null") {
        //                 clearInterval(self.polling);
        //                 self.connecting=true;
        //                 await self.startCall();
        //             } else {
        //                 console.log("You are still waiting for an agent");
        //             }
        //         } catch(err) {
        //             console.log(err);
        //         }
        //     },3000)
        // },

        startCall: async function () {
            let self=this;
            try {
                await rainbowSDK.connection.signinSandBoxWithToken(this.token); //login to rainbow server with guest token
                self.loading=50;
                let contact = await rainbowSDK.contacts.searchById(this.agentId); //get contact from agent id
                self.call = rainbowSDK.webRTC.callInAudio(contact);      //start to call the contact with available agent
                self.loading=100;
                self.start=true;
                document.addEventListener(rainbowSDK.webRTC.RAINBOW_ONWEBRTCCALLSTATECHANGED, this.onWebRTCCallChanged);
            } catch(err) {
                console.log(err)
            }
        },


    /**********************CALL FUNCS**********************/
    onWebRTCCallChanged: function(event) {
      //console.log("OnWebRTCCallChanged event", event.detail.status);
      if (event.detail.status.value==="Unknown"){    //if agent ends the call first, user will be directed to Feedback Page
          this.$router.push({path: "/feedback"});
      }
    },
    endCall: async function() {
      console.log("removing call");
      document.addEventListener(
        rainbowSDK.webRTC.RAINBOW_ONWEBRTCCALLSTATECHANGED,
        this.onWebRTCCallChanged
      );
      await rainbowSDK.webRTC.release(this.call);
      await console.log("released");
      await this.$router.push({ path: "/feedback" });
    }
  },
  /**********************CLEANUP FUNCS (DEPRECATED ON SOCKETING VERSION)**********************/
  // leaveQueue: async function(){ // remove queue entry
  //     let self=this;
  //     axios.delete(`https://esc-acorn-backend.herokuapp.com/api/queue?token=${self.token}`)
  //         .then(res => console.log(res))
  //         .catch(err => console.log(err))
  // },
  // endConversation: async function() {
  //     let self=this;
  //     axios.patch(`https://esc-acorn-backend.herokuapp.com/api/agents?agentId=${self.agentId}`)
  //         .then(res => console.log(res))
  //         .catch(err => console.log(err))
  // }
  //},
  //DONT REMOVE THE COMMENTED PART HERE
  // endCall: function() {
  // //function to end call from the customer's side when pressing End Call   //currently not working yet!!
  //   console.log("removing call");
  //   window.location.href = 'Feedback';
  //console.log(event.detail.status.value);
  //document.addEventListener(rainbowSDK.webRTC.RAINBOW_ONWEBRTCCALLSTATECHANGED, this.onWebRTCCallChanged);
  //rainbowSDK.webRTC.release(this.call); //cannot get this line of code working
  // console.log("res:", res);
  //},
  /**********************BACKUP CLEANUP METHOD**********************/
  beforeDestroy() {
    let self = this;
    self.socket.disconnect();
    console.log("Exiting");
    // DEPRECATED METHODS ON SOCKETING VERSION
    // self.leaveQueue();
    // self.cancelled=true;
    // clearInterval(self.polling);
    // self.endConversation();
  }
};
</script>


<style scoped>
.call {
    display:flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
}

.callBox {
    height:100%;
    width:100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    justify-items: center;
    align-content: center;
    overflow:auto
}

.agent-text{
    text-align: center;
    position: absolute;
    width: 100%;
    margin-top: 150px;
}

.header {
    background-color: #f1f1f1;
    text-align: center;
    font-size: 60px;
    width: 100%;
    transition: 0.2s;
}

.fade-enter-active, .fade-leave-active {
    transition: opacity .5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
    opacity: 0;
}
</style>
