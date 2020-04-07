<template>
    <div class= "call" id="call">
        <transition name="fade">
            <Waitpage v-bind:connecting="connecting" v-bind:loading="loading" v-if="!start"/>
        </transition>
        <audio id="globalAudioTag" autoplay style="display:none;"></audio>     <!--to allow customer to receive audio from agent-->
            <div class="text-xs-center">
                <v-card>
                    <v-card-title class="headline green lighten-2" primary-title> Customer Name</v-card-title>
                    <v-card-text>
                        WIP
                    </v-card-text>
                <v-divider/>
                <v-container>
                    <v-layout row class="mb-10 mt-10">
                        <v-flex xs4>
                            <v-btn large class="mx-10" fab dark small color="grey dark-2">
                                <v-icon>mic_off</v-icon>
                            </v-btn>
                        </v-flex>
                        <v-flex xs4>
                            <v-btn large class="mx-10" fab dark small color="grey dark-2">
                                <v-icon>keyboard</v-icon>
                            </v-btn>
                        </v-flex>
                        <v-flex xs4>
                            <v-btn large class="mx-10" fab dark small color="grey dark-2">
                                <v-icon>speaker_phone</v-icon>
                            </v-btn>
                          </v-flex>
                    </v-layout>
                    <v-layout row class="mb-10 mt-10">
                        <v-flex xs4>
                            <v-btn large class="mx-10" fab dark small color="grey dark-2">
                                <v-icon>add_ic_call</v-icon>
                            </v-btn>
                        </v-flex>
                        <v-flex xs4>
                            <v-btn large class="mx-10" fab dark small color="grey dark-2">
                                <v-icon>volume_down</v-icon>
                            </v-btn>
                        </v-flex>
                        <v-flex xs4>
                            <v-btn large class="mx-10" fab dark small color="grey dark-2">
                                <v-icon>contact_phone</v-icon>
                            </v-btn>
                        </v-flex>
                    </v-layout>
                </v-container>

                <v-divider></v-divider>
                <v-card-actions><v-spacer></v-spacer>

                  <v-btn @click="endCall" depressed color ="red white--text" class="btn">
                          <v-icon left>call_end</v-icon> End Call
                      </v-btn>
<!-- Step3 when you click 'Call_end', you will go to the feedback page  -->

                </v-card-actions>
            </v-card>
        </div>
    </div>
</template>

<script>
import Waitpage from "./Waitpage";
import rainbowSDK from "rainbow-web-sdk";
// import axios from "axios";
import io from 'socket.io-client';

export default {
    name: "Call",
    components:{Waitpage},
    data: () => ({
        token: "", // String variable for guest account token
        start: false,
        connecting: false,
        cancelled: false,
        loading: 0,
        socket:"" // holds the socket object
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
            navigator.mediaDevices //authoerise the application to access media device
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
        onWebRTCCallChanged: function(event){
            console.log("OnWebRTCCallChanged event", event.detail);
            if (event.detail.status.value==="Unknown"){    //if agent ends the call first, user will be directed to Feedback Page
              console.log(event.detail.status.value);
                this.$router.push({path: "/feedback"});
            }
        },
        //DONT REMOVE THE COMMENTED PART HERE
        // startCall: function() {
        //   this.call = rainbowSDK.webRTC.callInAudio(this.contact);
        //   if (this.call.label === "OK") {
        //     console.log("your call has been correctly initialised");
        //   }
        // },
        endCall: function() {
            let self=this;
            rainbowSDK.webRTC.release(self.call);
            //function to end call from the customer's side when pressing End Call   //currently not working yet!!
            // this.$router.push({path: "/feedback"});
            //console.log(event.detail.status.value);
            //document.addEventListener(rainbowSDK.webRTC.RAINBOW_ONWEBRTCCALLSTATECHANGED, this.onWebRTCCallChanged);
            //rainbowSDK.webRTC.release(this.call); //cannot get this line of code working
            // console.log("res:", res);
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
    },
    //DONT REMOVE THE COMMENTED PART HERE
    endCall: function() {
    //function to end call from the customer's side when pressing End Call   //currently not working yet!!
      console.log("removing call");
      window.location.href = 'Feedback';
      //console.log(event.detail.status.value);
      //document.addEventListener(rainbowSDK.webRTC.RAINBOW_ONWEBRTCCALLSTATECHANGED, this.onWebRTCCallChanged);
      //rainbowSDK.webRTC.release(this.call); //cannot get this line of code working
      // console.log("res:", res);
    },
    /**********************BACKUP CLEANUP METHOD**********************/
    beforeDestroy() {
        let self=this;
        self.socket.disconnect();
        console.log("Exiting");
        // DEPRECATED METHODS ON SOCKETING VERSION
        // self.leaveQueue();
        // self.cancelled=true;
        // clearInterval(self.polling);
        // self.endConversation();
    }
}
</script>


<style>
.btn {
  background-color: red;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  margin-bottom: 10px;
  opacity: 0.8;
}

.fade-enter-active, .fade-leave-active {
    transition: opacity .5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
    opacity: 0;
}
</style>
