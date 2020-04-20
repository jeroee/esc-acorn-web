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
            categories: ["Acorn Products", "Acorn Services", "Acorn Applications", "Acorn Pay", "Acorn ID"],
            limit: 20,
            selected: "",
            firstName: "",
            lastName: "",
            name1:"",
            name2:"",
        }),
        computed: {
            /*
            * This computed function provides visual feedback to the user on whether all conditions are necessary to fill the form.
            */
            rules () {
                return [
                    value => !!value || 'Please enter this field to proceed',
                    value => (value && /^[a-zA-Z]+$/.test(value)) || 'Only alphabetical characters allowed',
                    value => value.length <= this.limit || `A maximum of ${this.limit} characters is allowed`,
                ]
            },
            /*
            * This computed function creates the functional guard to prevent users from proceeding without the proper conditions. Namely:
            * First name field must be strictly alphabetical and not empty
            * Last name field must be strictly alphabetical and not empty
            * A category must be selected
            */
            rulesFailed() {
                return !/^[a-zA-Z]+$/.test(this.name2)  || !/^[a-zA-Z]+$/.test(this.name1) || this.name2 === "" || this.name2.length > this.limit || this.name1 === "" || this.selected === "" || this.name1.length > this.limit;
            }
        },
        methods: {
            submit() {
                this.$store.state.categoryIndex = this.categories.indexOf(this.selected);
                this.$store.state.categoryName = this.selected;
                this.$store.state.firstName = this.name1.charAt(0).toUpperCase() + this.name1.slice(1);
                this.$store.state.lastName = this.name2.charAt(0).toUpperCase() + this.name2.slice(1);
                this.$store.state.support = true;
            },

            requestChat: function () {
                if (!this.rulesFailed) {
                    this.submit();
                    this.$router.push({path: "/chat"});
                }
            },

            requestCall: function () {
                if (!this.rulesFailed) {
                    this.submit();
                    this.$router.push({path: "/call"});
                }
            },


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
</style>
