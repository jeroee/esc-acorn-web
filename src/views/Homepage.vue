<template>
    <div>
        <Menu/>
        <div class="hero mb-5 pa-8 flex-col">
            <span class="display-2 font-weight-medium mb-5">Welcome to <span>Acorn Support</span></span>
            <h3 class="white--text font-weight-medium pa-2" style="background-color: #4CAF50">the best support in the world. probably.</h3>
        </div>

        <v-container class="flex-col pa-8">
            <h2 class="font-weight-medium pb-5" >Please enter your details to get started.</h2>
            <div class="flex-row">
                <v-text-field
                    id="FirstName"
                    outlined
                    v-model="name1"
                    class="name-field responsive"
                    color="green"
                    :maxlength="limit"
                    :counter="limit"
                    :rules="rules"
                    label="Please provide your first name"/>

                <v-text-field
                    id="LastName"
                    outlined
                    v-model="name2"
                    class="name-field responsive"
                    color="green"
                    :maxlength="limit"
                    :counter="limit"
                    :rules="rules"
                    label="Please provide your last name"/>
            </div>
            <div class="flex-row">
                <v-select
                    id="category"
                    v-model="selected"
                    class="select-field"
                    :items="categories"
                    outlined
                    color="green"
                    :menu-props="{ offsetY:true, openOnClick:false }"
                    label="Choose a support category..."
                />
            </div>
            <div class="flex-row">
                <v-btn
                        id="Chat"
                        @click="requestChat"
                        x-large
                        depressed
                        class="ma-5 green white--text responsive"
                        :disabled="rulesFailed"
                        style="transition: all 500ms"
                >
                    <v-icon left>message</v-icon>Get Chat Support
                </v-btn>

                <v-btn
                        id="Call"
                        @click="requestCall"
                        x-large
                        depressed
                        class="ma-5 blue white--text responsive"
                        :disabled="rulesFailed"
                        style="transition: all 500ms"
                >
                    <v-icon left>phone</v-icon>Get Call Support
                </v-btn>
                <div class="recaptchabg" v-if="showCaptcha">
                    <div class="recaptcha" :class="{'shake': shake}">
                        <h1 class="display-2 pb-5">reCAPTCHA!</h1>
                        <v-img src="https://picsum.photos/300/200" max-height="200px"></v-img>
                        <v-text-field
                            id="recaptcha"
                            class="mt-2"
                            color="orange"
                            v-model="captcha"
                            label="Are you human?"
                            block/>
                        <v-btn
                                id="recaptchaCheck"
                                ref="recaptchaCheck"
                                @click="recaptchaCheck"
                                x-large
                                depressed
                                class=" mb-1 white--text"
                                :class="{'red accent-4': shake, 'orange': !shake}"
                                block
                                style="transition: all 500ms">
                            Verify Me<v-icon right>verified_user</v-icon>
                        </v-btn>
                        <span id="captchaHint">You have {{captchaCount}} tries left</span>
                    </div>
                </div>
            </div>
        </v-container>
    </div>
</template>

<script>
    import Menu from "../components/Menu";

    export default {
        components: {
            Menu,
        },

        name: "Home",
        data: () => ({
            categories: ["Acorn Products", "Acorn Services", "Acorn Applications", "Acorn Pay", "Acorn ID"],    //displays the list of Support Categories
            limit: 20,          //limit of input text fields for First Name and Last Name
            selected: "",       //takes in name of selected choice for Support Category
            name1:"",           //takes in text input for first name
            name2:"",           //tales in text input for last name
            showCaptcha: false, //to display reCaptcha Check implementation after selecting request, currently set at false not hide implementation
            captcha: "",        //takes in text input in reCaptcha Check
            captchaCount: 3,    //limit the number of reCaptcha tries to 3
            shake: false,       //does not allow shaking of component when set to false
            destination: ""     //Determines which route destination to take after passing reCaptcha Check
        }),
        computed: {
            /*
            * Provides visual feedback to the user on whether all conditions are necessary to fill the form.
            */
            rules () {
                return [
                    value => !!value || 'Please enter this field to proceed',
                    value => (value && /^[a-zA-Z]+$/.test(value)) || 'Only alphabetical characters allowed',
                    value => value.length <= this.limit || `A maximum of ${this.limit} characters is allowed`,
                ]
            },
            /*
            * Creates the functional guard to prevent users from proceeding without the proper conditions. Namely:
            * First name field must be strictly alphabetical and not empty
            * Last name field must be strictly alphabetical and not empty
            * A category must be selected
            */
            rulesFailed() {
                return !/^[a-zA-Z]+$/.test(this.name2)  || !/^[a-zA-Z]+$/.test(this.name1) || this.name2 === "" || this.name2.length > this.limit || this.name1 === "" || this.selected === "" || this.name1.length > this.limit;
            }
        },
        methods: {
            /**
             * Stores selected data in the current Vue component into the Vuex Store
             * Need category index, category name, firstname, lastname data to be sent over to other components to establish chat/call connection
             * Support data indicates leaving of Home component and  serves as boolean logic for Routing guard.
             */
            submit() {
                this.$store.state.categoryIndex = this.categories.indexOf(this.selected);
                this.$store.state.categoryName = this.selected;
                this.$store.state.firstName = this.name1.charAt(0).toUpperCase() + this.name1.slice(1);
                this.$store.state.lastName = this.name2.charAt(0).toUpperCase() + this.name2.slice(1);
                this.$store.state.support = true;
            },
            /**
             * Does Recaptcha check upon selecting Chat request
             */
            requestChat: function () {
                let self=this;
                if (!this.rulesFailed) {            //if conditions for the support page form is met
                    self.showCaptcha=true;          //display reCaptcha
                    self.destination="chat";        //set route destination
                }
            },
            /**
             * Does recaptcha check upon selecting Call request
             */
            requestCall: function () {
                let self=this;
                if (!this.rulesFailed) {            //if conditions for the support page form is met
                    self.showCaptcha=true;          //display reCaptcha
                    self.destination="call";        //set route destination
                }
            },
            /**
             * Runs the recaptcha test and proceeds to Chat/Call support when customer has passed the test
             * Provides 3 tries to pass the test, customer will be sent to the Homepage if he/she exceeds 3 tries
             */
            recaptchaCheck: function() {
                let self=this;
                console.log(self.$refs["recaptchaCheck"]);
                if (self.captcha==="yes") { //more interesting or real captcha logic can be implemented here
                    self.submit();          //submit all the required data to the Vuex store
                    self.$router.push({path: `/${self.destination}`});  //routes to the destination previously stored in the function which calls reCaptchaCheck
                } else if (self.captchaCount>1) {
                    self.captchaCount--;                                    //decrement of counter
                    self.shake=true;                                        //enable shaking of reCaptcha 
                    setTimeout(function(){ self.shake = false; }, 300);     //disable shake 
                } else {
                    self.captchaCount--;
                    window.location.href="/";                               //Send to homepage (windows reload to reset count)
                    setTimeout(function(){alert("You failed the recaptcha!")}, 1);  //alert customer (or bot) of failed reCaptcha
                }
            }

        }
    };
</script>

<style scoped>
    .flex-col {
        display: flex;
        flex-wrap: wrap;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100%;
    }

    .flex-row {
        display: flex;
        flex-wrap: wrap;
        flex-direction: row;
        align-items: center;
        justify-content: center;
        width: 100%;
    }

    .hero{
        background-image: url("../assets/acorn_bg.jpg");
        height: 400px;
        background-position: 50% 40%;
        background-size: cover;
        background-color: black;
    }

    .name-field {
        font-size: 1.25rem;
        max-width: 300px;
        margin: 8px 16px 8px 16px;
    }

    .select-field {
        font-size: 1.25rem;
        max-width: 400px;
        margin: 8px 16px 8px 16px;
    }

    .responsive{
        width: 250px;
        margin: 8px 16px 8px 16px;
    }

    .recaptchabg {
        position: fixed;
        display: flex;
        justify-content: center;
        align-items: center;
        top:0;
        bottom: 0;
        height: 100vh;
        width: 100vw;
        z-index: 10;
        background-color: rgba(0,0,0,0.3);
    }

    .recaptcha {
        position: fixed;
        background-color: white;
        width: 90%;
        max-width: 500px;
        padding: 32px;
        border-radius: 10px;
        -webkit-animation: shake 0.3s;
        -o-animation: shake 0.3s;
    }

    #captchaHint {
        color: red;
        font-size: small;
    }

    .shake {
        animation: shake 0.3s;
        animation-iteration-count: infinite;
    }

    @media only screen and (max-width: 768px) {
        .hero {
            align-items: flex-start;
        }
        .responsive{
            max-width: 400px;
            width: 100%;
            margin: 8px 16px 8px 16px;
        }

    }

    @keyframes shake {
        0% { transform: translate(1px, 1px) rotate(0deg); }
        10% { transform: translate(-1px, -2px) rotate(-1deg); }
        20% { transform: translate(-3px, 0px) rotate(1deg); }
        30% { transform: translate(3px, 2px) rotate(0deg); }
        40% { transform: translate(1px, -1px) rotate(1deg); }
        50% { transform: translate(-1px, 2px) rotate(-1deg); }
        60% { transform: translate(-3px, 1px) rotate(0deg); }
        70% { transform: translate(3px, 1px) rotate(-1deg); }
        80% { transform: translate(-1px, -1px) rotate(1deg); }
        90% { transform: translate(1px, 2px) rotate(0deg); }
        100% { transform: translate(1px, -2px) rotate(-1deg); }
    }
</style>
