<template>
    <div class="call" id="call">
        <transition name="fade">
            <Wait v-bind:connecting="connecting" v-bind:loading="loading" v-bind:disconnected="disconnected" v-bind:color="color" v-if="!start" />
        </transition>
        <h1 class="font-weight-light mb-5 header">
            Let's talk
            <v-icon x-large color="black">phone</v-icon>
        </h1>
        <audio id="globalAudioTag" autoplay />
        <!--to allow customer to receive audio from agent-->
        <div class="callBox">
            <div class="lottie">
                <Lottie :options="defaultOptions" :height="450" :width="450" />
            </div>
            <h1 class="font-weight-light agent-text">You're on the phone with {{agentName}}</h1>
        </div>
        <v-footer width="100%" padless>
            <v-btn
                id="moveToChat"
                @click="moveToChat"
                height="70px"
                width="50%"
                x-large
                depressed
                tile
                class="green white--text"
            >
                <h3>
                    Move To Chat<v-icon right>message</v-icon>
                </h3>
            </v-btn>
            <v-btn
                id="endcall"
                @click="exitCall"
                height="70px"
                width="50%"
                x-large
                depressed
                tile
                class="red white--text"
            >
                <h3>Leave Call</h3>
                <v-icon right>input</v-icon>
            </v-btn>
        </v-footer>
    </div>
</template>

<script>
    import Wait from "../components/Wait";
    import rainbowSDK from "rainbow-web-sdk";
    import Lottie from "vue-lottie";
    import animationData from "../assets/customer-support";
    // import axios from "axios";

    export default {
        name: "Call",
        components: { Wait, Lottie },
        data: () => ({
            defaultOptions: { animationData: animationData },
            start: false,
            connecting: false,
            disconnected: false,
            loading: 0,
            call: "",
            exit: false,
            color: "blue"
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
            firstName() {
                return this.$store.state.firstName;
            },
            lastName() {
                return this.$store.state.lastName;
            },
            token() {
                return this.$store.state.token;
            }
        },

        /**********************MOUNT ALL SOCKET METHODS HERE**********************/
        sockets: {
            handshake: function(data) {
                let self = this;
                console.log(data);
                console.log("Socket.io getAgent");
                console.log(self.categoryIndex);
                console.log(self.firstName);
                console.log(self.lastName);
                self.$socket.emit("getAgent", {
                    category: self.categoryIndex,
                    firstName: self.firstName,
                    lastName: self.lastName,
                });
            },
            getAgentSuccess: function(data) {
                let self = this;
                console.log("Socket.io getAgentSuccess");
                self.$store.state.agentId = data.agentId;
                self.$store.state.agentName = data.agentName; //get agent name
                self.$store.state.token = data.token; //get guest token
                console.log(`Your agentId is ${self.agentId}`);
                console.log(`Your agentName is ${self.agentName}`);
                console.log(`Your token is ${self.token}`);
                self.connecting = true;
                self.startCall();
            },
            noAgentsWorking: function () {
                let self=this;
                self.disconnected=true;
                self.$store.state.support = false;
                setTimeout(function () {
                    alert("Moving you to the home page");
                    self.$router.push({path: "/"});
                },1000);
            },
            rainbowError: function () {
                let self=this;
                self.$store.state.support = false;
                setTimeout(function () {
                    alert("Sorry, our group has run out of Rainbow guest tokens, please try again at a later point of time!");
                    self.$router.push({path: "/"});
                },1000);
            }
        },
        methods: {
            checkCall: function() {
                if (rainbowSDK.webRTC.canMakeAudioVideoCall()) {
                    //check if browser is compatible for audio calls
                    console.log("Browser supports calls");
                } else {
                    console.log("Browser does not support calls");
                    alert(
                        "Your browser does not support audio calls, please update to the latest version, or use a different browser!"
                    );
                    this.$router.push({ path: "/feedback" });
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

            startCall: async function() {
                let self = this;
                try {
                    await rainbowSDK.connection.signinSandBoxWithToken(this.token); //login to rainbow server with guest token
                    self.loading = 50;
                    let contact = await rainbowSDK.contacts.searchById(this.agentId); //get contact from agent id
                    let res = rainbowSDK.webRTC.callInAudio(contact); //start to call the contact with available agent
                    if (res.label === "OK") {
                        console.log("calling");
                    }
                    document.addEventListener(rainbowSDK.webRTC.RAINBOW_ONWEBRTCCALLSTATECHANGED, self.onWebRTCCallChanged);
                    await self.sleep(2000); //sleep to prevent rapid switches which break the rainbow websocket connection
                    self.loading = 100;
                    await self.sleep(100); //sleep for effect
                    self.start = true;
                } catch (err) {
                    console.log(err);
                    await self.moveToChat();
                }
            },
            sleep: async function(ms) {
                return new Promise(resolve => setTimeout(resolve, ms));
            },
            /**********************CALL FUNCS**********************/
            onWebRTCCallChanged: function(event) {
                let self = this;
                self.call = event.detail;
                console.log("event is: " + event.detail);
                //console.log("OnWebRTCCallChanged event", event.detail.status);
                if (event.detail.status.value === "Unknown") {
                    document.removeEventListener(rainbowSDK.webRTC.RAINBOW_ONWEBRTCCALLSTATECHANGED, self.onWebRTCCallChanged);
                    if (self.exit) { // only leave the call if customer wants to exit
                        console.log("Session Ended");
                        self.$socket.disconnect();
                        self.$store.state.feedback=true;
                        self.$store.state.support=false;
                        self.$router.push({ path: "/feedback" });
                    } else {
                        self.$store.state.moving=true;
                        self.$router.push({ path: "/chat" });
                    }
                }
            },
            exitCall: async function() {
                let self = this;
                self.exit = true;
                await rainbowSDK.webRTC.release(self.call);
            },
            moveToChat: async function() {
                let self = this;
                await rainbowSDK.webRTC.release(self.call);
                console.log("Moving to Chat");
            }
        },
        mounted() {
            let self = this;
            self.checkCall();
            if (self.agentId === "") {
                self.$socket.connect();
            } else {
                // self.$store.state.moving=false;
                self.connecting = true;
                self.startCall();
            }
            // self.socket = io.connect('http://localhost:4000/');
            // this.getConnection(); DEPRECATED
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

        /**********************BACKUP CLEANUP METHOD**********************/
        beforeDestroy() {
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
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100vh;
    }

    .callBox {
        height: 100%;
        width: 100vw;
        display: flex;
        padding:32px;
        flex-direction: column;
        justify-content: center;
    }

    .agent-text {
        text-align: center;
    }

    .header {
        background-color: #f1f1f1;
        text-align: center;
        font-size: 60px;
        width: 100%;
        transition: 0.2s;
    }

    .lottie {
        width: 100%;
        height: 350px;
        display: flex;
        justify-content: center;
        align-items: center;
        overflow: hidden;
    }

    .fade-enter-active,
    .fade-leave-active {
        transition: opacity 0.5s;
    }
    .fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
        opacity: 0;
    }
</style>
