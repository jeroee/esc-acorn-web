<template>
    <v-container class="feedback px-5">
        <h1 class="font-weight-light mb-10 text-center" >{{this.headertext}}</h1>
        <transition name="bounce">
            <v-card v-if="feedback">
                <v-toolbar dark color="blue">
                    <v-toolbar-title>Rate your experience with {{agentName}}</v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-toolbar-items>
                        <v-btn to="/" text>
                            Skip
                            <v-icon right>redo</v-icon>
                        </v-btn>
                    </v-toolbar-items>
                </v-toolbar>

                <!-- Write your opinions  -->
                <v-card>
                    <v-card-text>
                        <v-container>
                            <v-row>
                                <v-list-item cols="100" sm="6">
                                    <h3 class="font-weight-regular">How helpful was this session?</h3>
                                    <v-rating
                                            style="position: absolute; right: 0"
                                            hover
                                            :size="30"
                                            clearable
                                            v-model="rating1"
                                    />
                                </v-list-item>
                                <v-list-item cols="12" sm="6">
                                    <h3 class="font-weight-regular">How was our service?</h3>
                                    <v-rating
                                            style="position: absolute; right: 0"
                                            hover
                                            :size="30"
                                            clearable
                                            v-model="rating2"
                                    />
                                </v-list-item>
                                <v-list-item cols="12" sm="6">
                                    <h3 class="font-weight-regular">How was the quality of the chat/call experience?</h3>
                                    <v-rating
                                            style="position: absolute; right: 0"
                                            hover
                                            :size="30"
                                            clearable
                                            v-model="rating3"
                                    />
                                </v-list-item>
                                <v-alert type="error" v-if="hasError" dense style="width: 100%" class="mt-5">
                                    Please fill up all rating fields above!
                                </v-alert>
                                <v-col cols="12">
                                    <v-textarea
                                            background-color="white"
                                            :rows="1"
                                            auto-grow
                                            label="Additional Comments"
                                            v-model="comments"
                                            hint="Any additional feedback will be greatly appreciated!"
                                    />
                                </v-col>
                                <v-col cols="12">
                                    <v-text-field
                                            v-model="email"
                                            label="Email"
                                            hint="Leave us your email if you would like us to get back to you."
                                    ></v-text-field>
                                </v-col>
                                <v-btn @click="sendDetails" outlined color="blue" text x-large width="100%">
                                    <h3>Submit</h3>
                                </v-btn>
                            </v-row>
                        </v-container>
                    </v-card-text>
                </v-card>
            </v-card>
        </transition>
    </v-container>

</template>


<script>
    import axios from "axios";
    export default {
        data: () => ({
            rating1: -1,
            rating2: -1,
            rating3: -1,
            comments: "",
            email: "",
            headertext: "Help us improve our customer service",
            feedback: true,
            hasError: false
        }),
        computed: {
            agentName() {
                return this.$store.state.agentName;
            },
            agentId() {
                return this.$store.state.agentId;
            }
        },
        created() {
            console.log(this.agentName);
            console.log(this.agentId);
        },
        mounted() { //to prevent back button from working to access chats/calls
            history.pushState(null, null, location.href);
            window.onpopstate = function() {
                history.go(1);
            };
        },

        methods: {
            sendDetails: async function() {
                let self=this;
                self.hasError=false;
                if (self.rating1 !==-1 && self.rating2 !==-1 && self.rating3 !==-1) {
                    self.feedback=!self.feedback;
                    self.headertext="Thanks for the feedback!";
                    try {
                        await axios.patch(
                            `https://still-sea-41149.herokuapp.com/api/review?agentId=${self.agentId}&rating1=${self.rating1}&rating2=${self.rating2}&rating3=${self.rating3}&email=${self.email}&comment=${self.comments}`
                        );
                    } catch (e) {
                        console.log(e.message());
                    }
                    finally {
                        self.$store.state.connectionType = true;
                        self.$store.state.agentId="";
                        await this.$router.push({ path: "/" });
                    }
                } else self.hasError=true;
            },
        }
    };
</script>

<style scoped>
    .feedback{
        min-height: 100vh;
        display: flex;
        flex-direction: column;
        justify-content: center;
        transition: height 500ms;
    }

    .required {
        border: red 2px !important;
    }

    .bounce-enter-active {
        animation: bounce-in .5s;
    }
    .bounce-leave-active {
        animation: bounce-in .5s reverse;
    }
    @keyframes bounce-in {
        0% {
            transform: scale(0);
        }
        50% {
            transform: scale(1.1);
        }
        100% {
            transform: scale(1);
        }
    }

</style>
