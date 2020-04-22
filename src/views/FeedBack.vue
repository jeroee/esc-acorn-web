<template>
    <v-container class="feedback">
        <h1 class="font-weight-light mb-5 text-center" >{{headerText}}</h1>
        <transition name="bounce">
            <v-card v-if="!submitted" class="feedbackCard">
                <v-toolbar dark flat color="blue">
                    <v-toolbar-title>Provide your feedback</v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-toolbar-items>
                        <v-btn id="skip" to="/" text>
                            Skip
                            <v-icon right>redo</v-icon>
                        </v-btn>
                    </v-toolbar-items>
                </v-toolbar>
                <div class="pa-5">
                    <div class="item-row">
                        <h3 class="font-weight-regular">How helpful was {{agentName}} during this session?</h3>
                        <v-rating
                            id="rating1"
                            hover
                            :size="40"
                            clearable
                            v-model="rating1"
                        />
                    </div>
                    <div class="item-row">
                        <h3 class="font-weight-regular">How friendly was {{agentName}} during this session?</h3>
                        <v-rating
                                id="rating2"
                                hover
                                :size="40"
                                clearable
                                v-model="rating2"
                        />
                    </div>
                    <div class="item-row">
                        <h3 class="font-weight-regular">How was the quality of the chat/call experience?</h3>
                        <v-rating
                                id="rating3"
                                hover
                                :size="40"
                                clearable
                                v-model="rating3"
                        />
                    </div>
                    <div class="item-row">
                        <v-textarea
                                id="comments"
                                background-color="white"
                                :rows="1"
                                :maxlength="240"
                                :counter="240"
                                auto-grow
                                label="Additional Comments"
                                v-model="comments"
                                autofocus
                                :rules="rulesComments"
                                hint="Any additional feedback will be greatly appreciated!"
                        />
                    </div>
                    <div class="item-row">
                        <v-text-field
                                id="email"
                                :maxlength="40"
                                :counter="40"
                                v-model="email"
                                autofocus
                                :rules="rulesEmail"
                                label="Email"
                                hint="Leave us your email if you would like us to get back to you."
                        ></v-text-field>
                    </div>
                    <v-btn id="Submit"
                        v-if="!hasError"
                        @click="sendDetails"
                        depressed
                        outlined
                        class="white--text mt-4"
                        color="blue"
                        x-large
                        block>
                        <h3>Submit</h3>
                    </v-btn>
                    <v-alert type="error" v-if="hasError" style="width: 100%; margin:16px 0 0 0">{{errorMessage}}</v-alert>
                </div>
            </v-card>
        </transition>
    </v-container>

</template>


<script>
    import axios from "axios";
    /*
    * Determines the the structure of a valid email and what it should ideally contain
    */
    const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    export default {
        data: () => ({
            rating1: 0,                                             //Rating for first Question which takes in rating provided by the customer
            rating2: 0,                                             //Rating for second Question which takes in rating provided by the customer
            rating3: 0,                                             //Rating for third Question which takes in rating provided by the customer
            comments: "",                                           //Text input field which takes in comments provided by the customer
            email: "",                                              //Text input field which takes in emails provided by the customer
            headerText: "Help us improve our customer service",     //Header display at the top of the Vue component
            submitted: false,                                       //boolean logic to indicate valid feedback submission entry
            hasError: false,                                        //boolean logic to indicate signs of error in submission entry
            errorMessage:"",                                        //display of error message depending the type of error indicated during submission
        }),
        computed: {
            /*
            * To retrieve agent name for the Vuex Store to display it in Vue component
            */ 
            agentName() {
                return this.$store.state.agentName;
            },
            /*
            * To retrieve the agent ID from the Vuex Store to send request to API to submit agent feedback
            */
            agentId() {
                return this.$store.state.agentId;
            },
            /*
            * Prompts customer to provide an email when submitting feedback
            * Prompts customer to input a valid email which follows the email pattern if an invalid email is inserted into the text field
            */
            rulesEmail () {
                let self=this;
                if (self.comments) {
                    return [
                        value => !!value || 'Please leave your email so we can follow up with your comments',
                        value => pattern.test(value) || 'Please submit a valid email.'
                    ]
                } else {
                    return []
                }
            },
            /*
            * Prompts customer to provide some comments if only email is provided
            */
            rulesComments () {
                let self=this;
                if (self.email) {
                    return [
                        value => !!value || 'Please leave some feedback with your email'
                    ]
                } else {
                    return []
                }
            },
            /*
            * works if all ratings are filled or if all ratings filled and both comments and emails are filled validly
            */
            rulesFailed () {
                let self=this;
                return (self.rating1===0 || self.rating2 ===0 || self.rating3 ===0)
                   || ((self.email!=="" || self.comments!=="") && (self.comments==="" || self.email==="" || !pattern.test(self.email)))
                // return (pattern.test(self.email))
            }
        },
        beforeDestroy() {
            let self=this;
            //set all the data stored in the Vuex store to its default values
            self.$store.state.agentId="";
            self.$store.state.agentName="";
            self.$store.state.token="";
            self.$store.state.feedback = false;
        },
        methods: {
            /*
            * To submit feedback details which are provided by the customer which will be sent as a patch request to update the agent's ratings.
            * The Submission of feedback will only deem successful if the following conditions are met:
            * All raitings have to be filled
            * Addtional comments and a valid email address is required
            * 
            * send customer back to the Homepage after successful submission
            */
            sendDetails: async function() {
                let self=this;
                self.hasError=false;       
                if (!self.rulesFailed) {                                    //filled details meet the conditions
                    var replacement = self.comments.replace(/&/g, 'and');   //text sanitsation: replace all "&" symbol to and (might affect mongo db query)
                    console.log(replacement);
                    self.submitted=true;                                    //indicate that feedback submission entry is valid                     
                    self.headerText="Thanks for the feedback!";
                    try {
                        //sending patch request to API via axios to update agent's rating
                        await axios.patch(
                            `https://still-sea-41149.herokuapp.com/api/review?agentId=${self.agentId}&rating1=${self.rating1}&rating2=${self.rating2}&rating3=${self.rating3}&email=${self.email}&comment=${replacement}`
                        ).then(response => console.log(response));
                    } catch (e) {
                        console.log(e.message());
                    }
                    finally {
                        await this.$router.push({ path: "/" }); //navigate back to home page
                    }
                } else if (self.rating1 !==0 && self.rating2 !==0 && self.rating3 !==0){    //if only ratings are filled
                    this.errorMessage="Please leave a valid email with any comments, and vice-versa";
                    self.hasError=true;                         //prompts error message
                    setTimeout(this.closePopup,2000);
                }
                else {      //if not all raiting are filled
                    self.hasError=true;                         //prompts error mesage
                    self.errorMessage="Please fill up all rating fields above!";
                    setTimeout(this.closePopup,2000);
                }
            },
            /*
            * dismissal of the error message
            */
            closePopup: function(){
                this.hasError=false;
                this.errorMessage="";
            }
        },
    };
</script>

<style scoped>
    .feedback{
        min-height: 100vh;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        transition: height 400ms;
    }

    .feedbackCard{
        max-width: 960px;
    }

    .item-row {
        width: 100%;
        display: flex;
        flex-wrap: nowrap;
        padding: 0 12px 0 12px;
    }

    .item-row h3 {
        width: 100%;
        display: flex;
        align-items: center;
        margin-right: 32px;
    }


    @media only screen and (max-width: 700px) {
        .feedbackCard{
            width: 90vw;
        }
        .item-row {
            justify-content: center;
            flex-wrap: wrap;
            padding-top: 32px;
        }
        .item-row h3 {
            justify-content: center;
            flex-wrap: wrap;
            text-align: center;
            margin-right: 0;
        }

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
