<template>
    <div class="chat">
        <transition name="fade">
            <Wait v-bind:connecting="connecting" v-bind:loading="loading" v-bind:disconnected="disconnected" v-bind:color="color" v-if="!start" />
        </transition>
        <div class="chatBox" id="chatBox" ref="chatBox">
            <h1 class="font-weight-light mb-5" id="header" ref="header">
                Let's chat
                <v-icon x-large color="black">chat</v-icon>
            </h1>
            <v-card
                    class="ma-5 green white--text chatBubble"
                    v-bind:class="item.position"
                    elevation="5"
                    flat
                    v-for="(item, index) in items"
                    :key="index"
            >
                <v-card-subtitle class="white--text pb-0">{{item.sender}}</v-card-subtitle>
                <v-card-text>
                    <br />
                    <p class="message">{{item.message}}</p>
                </v-card-text>
                <v-card-subtitle class="white--text text-right pr-2 pb-1">{{item.time}}</v-card-subtitle>
            </v-card>
        </div>
        <v-footer width="100%" padless>
            <v-textarea
                    id="WriteMessage"
                    class="scroll-y"
                    v-model="txt"
                    filled
                    height="70px"
                    hide-details
                    rows="1"
                    loading
                    style="font-size: 1.25rem"
                    color="green"
                    placeholder="Send a message..."
            />
            <v-btn
                    id="SendMessage"
                    @click="message"
                    height="70px"
                    x-large
                    depressed
                    tile
                    class="green white--text"
            >
                <h3>Send</h3>
                <v-icon right>send</v-icon>
            </v-btn>
            <div class="responsive">
                <v-btn
                        id="moveToCall"
                        @click="moveToCall"
                        height="70px"
                        x-large
                        depressed
                        tile
                        class="blue white--text"
                >
                    <h3>Move To Call</h3>
                    <v-icon right>call</v-icon>
                </v-btn>
                <v-btn
                        id="exitChat"
                        @click="exitChat"
                        height="70px"
                        x-large
                        depressed
                        tile
                        class="red white--text"
                >
                    <h3>Leave</h3>
                    <v-icon right>input</v-icon>
                </v-btn>
            </div>
        </v-footer>
    </div>
</template>

<script>
    import $ from 'jquery'
    import moment from 'moment';
    import rainbowSDK from "rainbow-web-sdk";
    // import axios from "axios";
    import Wait from "../components/Wait";
    export default {
        name: "Chatpage",
        components: {Wait},
        data: () => ({
            items: [], // array of messages, updated on receive and send
            color: "green", // sets the color for the wait component
            txt: "", // string buffer for chat text input
            conversation:'', // variable to hold the conversation object
            start: false, // removes the loading page from view - on true, removes loading page
            connecting: false, // updates the wait component to determinate loading
            disconnected: false, //updates the wait component to display no agents message
            cancelled: false, // sets a guard for polling during early exits - on true, prevents polling
            loading: 0, // updates the spin loader progress after agent found - values [0,100]
            spamCounter: 0,
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
            },
            token(){
                return this.$store.state.token;
            }
        },
        /**********************MOUNT ALL SOCKET METHODS HERE**********************/
        sockets: {
            handshake: function (data) {
                let self=this;
                console.log(data);
                console.log("Socket.io getAgent");
                console.log(self.categoryIndex);
                console.log(self.firstName);
                console.log(self.lastName);
                self.$socket.emit("getAgent", {
                    category: self.categoryIndex,
                    firstName: self.firstName,
                    lastName: self.lastName
                })
            },
            getAgentSuccess: function (data) {
                let self=this;
                console.log("Socket.io getAgentSuccess");
                self.$store.state.agentId = data.agentId;
                self.$store.state.agentName = data.agentName; //get agent name
                self.$store.state.token = data.token; //get guest token
                console.log(`Your agentId is ${self.agentId}`);
                console.log(`Your agentName is ${self.agentName}`);
                console.log(`Your token is ${self.token}`);
                self.connecting=true;
                self.startChat();
            },
            noAgentsWorking: function () {
                let self=this;
                self.disconnected=true;
                self.$store.state.support = false;
                setTimeout(function () {
                    alert("Moving you to the home page");
                    self.$router.push({path: "/"});
                },1000);
            }
        },
        methods: {
            /**********************INITIAL GET (DEPRECATED ON SOCKETING VERSION)**********************/
            // getConnection: async function() {
            //     let self=this;
            //     try {
            //         let response = await axios.get(
            //             `https://esc-acorn-backend.herokuapp.com/api/agents?category=${self.categoryIndex}` //obtain agent through category
            //         );
            //         self.agentId = response.data.agentId; //get agent id
            //         self.$store.state.agentName = response.data.agentName; //get agent name
            //         self.token = response.data.token; //get guest token
            //         console.log(this.agentId);
            //         console.log(`Your token is ${self.token}`);
            //         if (self.agentId!=="Null") {
            //             self.connecting=true;
            //             await self.startChat();
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
            //                 await self.startChat();
            //             } else {
            //                 console.log("You are still waiting for an agent");
            //             }
            //         } catch(err) {
            //             console.log(err);
            //         }
            //     },3000)
            // },
            startChat: async function () {
                let self=this;
                try {
                    await rainbowSDK.connection.signinSandBoxWithToken(self.token); //login to rainbow server with guest token
                    self.loading=50;
                    let contact = await rainbowSDK.contacts.searchById(self.agentId); //get contact from agent id
                    self.conversation = await rainbowSDK.conversations.openConversationForContact(contact);
                    self.loading=100;
                    await rainbowSDK.im.getMessagesFromConversation(self.conversation); //getting all messages from conversation
                    self.start=true;
                    document.addEventListener(rainbowSDK.im.RAINBOW_ONNEWIMMESSAGERECEIVED, self.receive);
                    document.addEventListener(rainbowSDK.im.RAINBOW_ONNEWIMRECEIPTRECEIVED, self.receipt);
                    self.items.push({message: "Hello "+self.firstName+", You've been connected with "+ self.agentName+". Please let them know how they can help you today.", position: "left", sender: "System", time: moment().format("h:mm a")});
                } catch(err) {
                    console.log(err)
                }
            },
            /**********************CLEANUP FUNCS (DEPRECATED ON SOCKETING VERSION)**********************/
            // leaveQueue: function(){ // remove queue entry
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
            // },
            /**********************MESSAGE FUNCS**********************/
            message() {
                let self=this;
                if (self.txt !== "") {
                    let message= self.txt;
                    rainbowSDK.im.sendMessageToConversation(self.conversation, message);
                    self.items.push({message: message, position: "right", sender: self.firstName +" "+ self.lastName, time: moment().format("h:mm a")});
                    $("#chatBox").animate({scrollTop: $('#chatBox')[0].scrollHeight}, 500);
                    self.txt = "";
                    self.spamCounter+=1;
                    console.log(self.spamCounter);
                    if(message.length>=400 && !message.includes(" ")){
                        self.forceExit();
                    }
                    if(self.spamCounter>20){
                        self.forceExit();
                    }
                }
            },
            receive: function(event) {
                let self=this;
                rainbowSDK.im.markMessageFromConversationAsRead(event.detail.conversation, event.detail.message);
                self.items.push({message: event.detail.message.data, position: "left", sender: self.agentName, time: moment().format("h:mm a")});
                $("#chatBox").animate({scrollTop: $('#chatBox')[0].scrollHeight}, 500);
                this.spamCounter=0;
                console.log(self.spamCounter);
            },
            receipt: function(event) {
                switch (event.detail.evt) {
                    case "server":
                        console.log("MESSAGE SENT");
                        break;
                    case "received":
                        console.log("AND RECEIVED");
                        break;
                    default:
                        break;
                }
            },
            exitChat: async function() {
                let self=this;
                await rainbowSDK.conversations.closeConversation(self.conversation);
                console.log("Session Ended");
                self.$socket.disconnect();
                self.$store.state.feedback=true;
                self.$store.state.support=false;
                await self.$router.push({path: "/feedback"});
            },
            moveToCall: async function() {
                let self=this;
                await rainbowSDK.conversations.closeConversation(self.conversation);
                console.log("Moving To Call");
                // self.$store.state.moving=true;
                await self.$router.push({path: "/call"});
            },
            forceExit: async function(){
                let self=this;
                await rainbowSDK.conversations.closeConversation(self.conversation);
                console.log("Spam message, terminating Session");
                self.$socket.disconnect();
                self.$store.state.support=false;
                await self.$router.push({path: "/"}).then(alert("Spam Message Detected"));
            },
        },
        mounted() {
            let self = this;
            if (self.agentId==="") {
                self.$socket.connect()
            } else {
                self.$store.state.moving=false;
                self.connecting=true;
                self.startChat();
            }

            window.addEventListener('keyup', function (event) { // invoke message on enter
                if (event.keyCode === 13) {
                    self.message();
                }
            });
            self.$refs["chatBox"].onscroll = function() {
                if (self.$refs["chatBox"].scrollTop > 0) {
                    self.$refs["header"].style.fontSize = "0px";
                } else {
                    self.$refs["header"].style.fontSize = "40px";
                }
            };
            // self.getConnection(); DEPRECATED
        },
        /**********************BACKUP CLEANUP METHOD**********************/
        beforeDestroy() {
            let self=this;
            document.removeEventListener(rainbowSDK.im.RAINBOW_ONNEWIMMESSAGERECEIVED, self.receive);
            document.removeEventListener(rainbowSDK.im.RAINBOW_ONNEWIMRECEIPTRECEIVED, self.receipt);
            console.log("Exiting");

            // DEPRECATED METHODS ON SOCKETING VERSION
            // self.leaveQueue();
            // self.cancelled=true;
            // clearInterval(self.polling);
            // self.endConversation();
        }
    }
</script>

<style scoped>
    .chat {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100vh;
    }
    .right {
        margin-left: auto !important;
        background-color: green !important;
    }
    #header {
        background-color: #f1f1f1;
        text-align: center;
        font-size: 60px;
        width: 100%;
        transition: 0.2s;
    }
    .chatBox {
        height: 100%;
        width: 100%;
        overflow: auto;
        -ms-overflow-style: none; /* Internet Explorer 10+ */
        scrollbar-width: none; /* Firefox */
    }
    .chatBox::-webkit-scrollbar {
        display: none; /* Safari and Chrome */
    }
    .fade-enter-active,
    .fade-leave-active {
        transition: opacity 0.5s;
    }
    .fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
        opacity: 0;
    }
    .message{
        color:white;
        font-size: 1.25rem;
        line-height: 2rem;
        margin-bottom: 0;
    }
    .chatBubble{
        width: 500px;
    }

    @media only screen and (max-width: 1024px) {
        .chatBubble{
            width: 350px;
        }
        .responsive{
            width: 100vw;
            display: flex;
            flex-direction: row;
            align-items: stretch;
        }
        #exitChat,#moveToCall {
            width: 50%;
        }
        #SendMessage {
            display: none;
        }
    }
</style>
