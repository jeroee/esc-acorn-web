<template>
    <div class="chat">
        <h1 class="font-weight-light ma-10" v-if="!chatting">Let's chat <v-icon x-large color="black">chat</v-icon></h1>
        <div style="height:100%; width:100%; overflow:auto" id="chatBox">
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
                    style="font-size: 1.25rem"
                    loading
                    color="green"
                    auto-grow
                    placeholder="Send a message..."
            />
            <v-btn @click="message" height="58px" x-large depressed tile class="green white--text">
                <h3>Send</h3><v-icon right>send</v-icon>
            </v-btn>
        </v-footer>
    </div>
</template>

<script>
    import $ from 'jquery'
    import moment from 'moment';
    import angular from 'angular';

    export default {
        name: "Chatpage",
        data: () => ({
            guestId: "5e5fc884d8084c29e64eb03b",
            agentId: "",
            agentFirstName: "Agent",
            agentLastName: "One",
            chatting: false,
            items: [
                {
                    message: "Hi there! You've been connected with our agent. You may start typing to chat!",
                    sender: "Agent One",
                    time: moment().format("h:mm a")
                },
            ],
            txt: ""
        }),
        methods: {
            message() {
                if (this.txt !== "") {
                    this.chatting = true;
                    this.items.push({message: this.txt, sender: "you", time: moment().format("h:mm a")});
                    this.txt = "";
                    $("#chatBox").animate({scrollTop: $('#chatBox')[0].scrollHeight}, 500);
                }
            },
        },
        mounted() {
            let self = this;
            window.addEventListener('keyup', function (event) {
                if (event.keyCode === 13) {
                    self.message();
                }
            });
        },
        created() {
            console.log("[DEMO] :: Starter-Kit of the Rainbow SDK for Web with Vue.js started!");

            /* Bootstrap the SDK */
            angular.bootstrap(document, ["sdk"]).get("rainbowSDK");


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
</style>
