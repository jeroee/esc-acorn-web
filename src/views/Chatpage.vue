<template>
    <div class="chat">
        <transition name="fade">
            <Waitpage v-bind:connecting="connecting" v-bind:loading="loading" v-if="!start"/>
        </transition>
        <div class="chatBox" id="chatBox" ref="chatBox">
            <h1 class="font-weight-light mb-5" id="header" ref="header">Let's chat <v-icon x-large color="black">chat</v-icon></h1>
            <v-card class="ma-5 green white--text" v-bind:class="item.sender" flat width="500px" v-for="item in items" :key="item.message">
                <v-card-subtitle class="white--text pb-0">{{item.sender}}</v-card-subtitle>
                <v-card-title style="word-break: keep-all">
                    {{ item.message }}
                </v-card-title>
                <v-card-subtitle class="white--text text-right pr-2 pb-1">{{item.time}}</v-card-subtitle>
            </v-card>
        </div>
        <v-footer width="100%" padless >
            <v-textarea
                    v-model="txt"
                    filled
                    hide-details
                    rows="1"
                    loading
                    style="font-size: 1.25rem"
                    color="green"
                    auto-grow
                    placeholder="Send a message..."
            />
            <v-btn @click="message" height="58px" x-large depressed tile class="green white--text">
                <h3>Send</h3><v-icon right>send</v-icon>
            </v-btn>
            <v-btn to="/" height="58px" x-large depressed tile class="red white--text">
                <h3>Leave</h3><v-icon right>input</v-icon>
            </v-btn>
        </v-footer>
    </div>
</template>

<script>
    import $ from 'jquery'
    import moment from 'moment';
    import rainbowSDK from "rainbow-web-sdk";
    import axios from "axios";
    import Waitpage from "./Waitpage";

    export default {
        name: "Chatpage",
        components: {Waitpage},
        data: () => ({
            token: "", // String variable for guest account token
            agentId: "", // String variable for agent id
            agentName: "Agent", // String variable for agent name
            items: [
                {
                    message: "Hi there! You've been connected with our agent. You may start typing to chat!",
                    sender: "Agent One",
                    time: moment().format("h:mm a")
                },
            ], // array of messages, updated on receive and send
            txt: "", // string buffer for chat text input
            conversation:'', // variable to hold the conversation object
            start: false, // removes the loading page from view - on true, removes loading page
            connecting: false, // updates the
            cancelled: false, // sets a guard for polling during early exits - on true, prevents polling
            loading: 0 // updates the spin loader progress after agent found - values [0,100]
        }),
        computed: {
            categoryIndex() {
                return this.$store.state.categoryIndex;
            }
        },
        methods: {
            /**********************INITIAL GET**********************/
            getConnection: async function() {
                let self=this;
                try {
                    let response = await axios.get(
                        `https://still-sea-41149.herokuapp.com/api/agentss?category=${this.categoryIndex}` //obtain agent through category
                    );
                    self.agentId = response.data.agentId; //get agent id
                    self.agentName = response.data.agentName; //get agent name
                    self.token = response.data.token; //get guest token
                    console.log(this.agentId);
                    console.log(`Your token is ${this.token}`);
                    if (this.agentId) {
                        this.connecting=true;
                        this.startChat();
                    } else {
                        console.log("No account found! Retrying every x seconds");
                        if (!this.cancelled) { //prevent polling in early exits
                            this.pollConnection();
                        } else console.log("Load was left early");
                    }
                } catch(err) {
                    console.log(err);
                }
            },

            /**********************POLLING GET**********************/
            pollConnection: function() {
                let self=this;
                self.polling=setInterval(async function () {
                    try {
                        let response = await axios.get(
                            `https://still-sea-41149.herokuapp.com/api/queue?token=${self.token}`
                        );
                        self.agentId = response.data.agentId; //get agent id
                        self.agentName = response.data.agentName; //get agent name
                        // console.log(response);
                        if (self.agentId) {
                            clearInterval(self.polling);
                            self.connecting=true;
                            await self.startChat();
                        } else {
                            console.log("You are still waiting for an agent");
                        }
                    } catch(err) {
                        console.log(err);
                    }
                },10000)
            },
            startChat: async function () {
                try {
                    await rainbowSDK.connection.signinSandBoxWithToken(this.token); //login to rainbow server with guest token
                    this.loading=50;
                    let contact = await rainbowSDK.contacts.searchById(this.agentId); //get contact from agent id
                    this.conversation = await rainbowSDK.conversations.openConversationForContact(contact);
                    this.loading=100;
                    await rainbowSDK.im.getMessagesFromConversation(this.conversation); //getting all messages from conversation
                    this.start=true;
                    document.addEventListener(
                        rainbowSDK.im.RAINBOW_ONNEWIMMESSAGERECEIVED,
                        this.receive
                    );
                    document.addEventListener(
                        rainbowSDK.im.RAINBOW_ONNEWIMRECEIPTRECEIVED,
                        this.receipt
                    );
                } catch(err) {
                    console.log(err)
                }
            },

            leaveQueue: function(){ // remove queue entry
                let self=this;
                axios.delete(`https://still-sea-41149.herokuapp.com/api/queue?token=?${self.token}`)
                    .then(res => console.log(res))
                    .catch(err => console.log(err))
            },

            /**********************MESSAGE FUNCS**********************/
            message() {
                if (this.txt !== "") {
                    let message= this.txt;
                    rainbowSDK.im.sendMessageToConversation(this.conversation, message);
                    this.items.push({message: message, sender: "you", time: moment().format("h:mm a")});
                    $("#chatBox").animate({scrollTop: $('#chatBox')[0].scrollHeight}, 500);
                    this.txt = "";
                }
            },
            receive: function(event) {     //this function works when u receive a message
                console.log(event.detail.message.data);
                console.log(event.detail.message.side);
                this.items.push({message: event.detail.message.data, sender: this.agentName, time: moment().format("h:mm a")});
                $("#chatBox").animate({scrollTop: $('#chatBox')[0].scrollHeight}, 500);
            },

            receipt: function(event) {      //this function works when u send out a message
                console.log(event.detail.message.data);
                console.log(event.detail.message.side);
            },
            endConversation: async function() {
                let self=this;
                axios.patch(`https://still-sea-41149.herokuapp.com/api/agentss?rainbowId=${self.agentId}`)
                    .then(res => console.log(res))
                    .catch(err => console.log(err))
            }
        },
        mounted() {
            const self = this;
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
            this.getConnection();
        },
        beforeDestroy() {
            console.log("exiting");
            this.leaveQueue();
            this.cancelled=true;
            clearInterval(self.polling);
            this.endConversation();
        }
    }
</script>

<style scoped>
.chat {
    display:flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
}

.you {
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
    height:100%;
    width:100%;
    overflow:auto;
    -ms-overflow-style: none;  /* Internet Explorer 10+ */
    scrollbar-width: none;  /* Firefox */
}
.chatBox::-webkit-scrollbar {
    display: none;  /* Safari and Chrome */
}

.fade-enter-active, .fade-leave-active {
    transition: opacity .5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
    opacity: 0;
}
</style>
