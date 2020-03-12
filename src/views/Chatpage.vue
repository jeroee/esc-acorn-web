<template>
    <div class="chat">
        <div class="chatBox" id="chatBox" ref="chatBox">
            <h1 class="font-weight-light mb-5"  id="header" ref="header">Let's chat <v-icon x-large color="black">chat</v-icon></h1>
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
        </v-footer>
    </div>
</template>

<script>
    import $ from 'jquery'
    import moment from 'moment';

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
            }
        },
        mounted() {
            const self = this;
            window.addEventListener('keyup', function (event) {
                if (event.keyCode === 13) {
                    self.message();
                }
            });
            console.log(self.$refs);
            self.$refs["chatBox"].onscroll = function() {
                if (self.$refs["chatBox"].scrollTop > 0) {
                    self.$refs["header"].style.fontSize = "0px";
                } else {
                    self.$refs["header"].style.fontSize = "40px";
                }
            }
        },
        created() {
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
</style>
