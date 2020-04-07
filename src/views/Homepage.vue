<template>
    <div>
        <Menu/>
        <div class="hero mb-10">
            <v-container class="flex-col">
                <h1 class="display-2 font-weight-medium">Welcome to Acorn Support</h1>
                <div class="flex-row">
                    <v-text-field
                            id="FirstName"
                            v-model="name1"
                            class="name-field"
                            color="green"
                            :counter="20"
                            label="Please provide your first name"
                            solo
                            flat
                            required
                            @input="$v.name1.$touch()"
                            @blur="$v.name1.$touch()"/>

                    <v-text-field
                            id="LastName"
                            v-model="name2"
                            class="name-field"
                            color="green"
                            :counter="20"
                            label="Please provide your last name"
                            solo
                            flat
                            required
                            @input="$v.name2.$touch()"
                            @blur="$v.name2.$touch()"/>
                </div>

            </v-container>

        </div>

<!--        <FirstPagePic></FirstPagePic>-->

        <v-container class="flex-col">
            <v-select
                    id="category"
                    v-model="selected"
                    style="width: 400px"
                    :items="categories"
                    outlined
                    color="green"
                    :menu-props="{ offsetY:true, openOnClick:false }"
                    label="Choose a support category..."
            ></v-select>

            <div style="flex-direction: row">
                <v-btn
                        @click="requestChat"
                        x-large
                        depressed
                        class="ma-5 green white--text"
                        :disabled="isSelected"
                        style="transition: all 500ms"
                >
                    <v-icon left>message</v-icon>Get Chat Support
                </v-btn>

                <v-btn
                        @click="requestCall"
                        x-large
                        depressed
                        class="ma-5 green white--text"
                        :disabled="isSelected"
                        style="transition: all 500ms"
                >
                    <v-icon left>phone</v-icon>Get Call Support
                </v-btn>

            </div>
<!--            <ManyCard/>-->
        </v-container>
    </div>
</template>

<script>
    // import popup from '../components/popup' // this is the popup table
    import { required, maxLength} from 'vuelidate/lib/validators'
    import Vue from 'vue'
    import Vuelidate from 'vuelidate'
    import { validationMixin } from 'vuelidate'
    Vue.use(Vuelidate)
    import Menu from "../components/Menu";
    // import ManyCard from "../components/ManyCard";
    // import FirstPagePic from "../components/FirstPagePic";


    export default {
        components: {
            Menu,
            // ManyCard,
            // FirstPagePic
        },

        name: "Home",
        mixins: [validationMixin],

        validations: {
            name1: { required, maxLength: maxLength(30) },
            name2: { required, maxLength: maxLength(30) },
        },



        data: () => ({
            categories: ["Acorn Products", "Acorn Services", "Acorn Applications"],
            selected: "",
            firstName: "",
            lastName: "",
            name1:"",
            name2:""

        }),
        computed: {
            isSelected() {
                if(this.name2 ==="" || this.name1 === "" || this.selected === ""){
                    return true
                }
                return false;
            },
            name1Errors() {
                const errors = [];
                if (!this.$v.name1.$dirty) return errors;
                !this.$v.name1.maxLength && errors.push('Name must be at most 10 characters long');
                !this.$v.name1.required && errors.push('Name is required.');
                return errors;
            },
            name2Errors() {
                const errors = [];
                if (!this.$v.name2.$dirty) return errors;
                !this.$v.name2.maxLength && errors.push('Name must be at most 30 characters long');
                !this.$v.name2.required && errors.push('Name is required.');
                return errors;
            }

        },
        methods: {
            alert: function() {
                alert("Coming Soon");
            },

            submit() {
                // this.$v.$touch();
                this.$store.state.categoryIndex = this.categories.indexOf(this.selected);
                this.$store.state.categoryName = this.selected;
                this.$store.state.firstName = this.name1.charAt(0).toUpperCase() + this.name1.slice(1);
                this.$store.state.lastName = this.name2.charAt(0).toUpperCase() + this.name2.slice(1);
            },


            requestChat: function () {
                if (this.selected) {
                    this.submit();
                    this.$router.push({path: "/chat"});
                }
            },

            requestCall: function () {
                if (this.selected) {
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
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100%;
    }

    .flex-row {
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: center;
    }

    .hero{
        background-image: url("../assets/acorn_bg.png");
        height: 400px;
        background-position: center;
        background-size: cover;
    }

    .name-field {
        font-size: 1.25rem;
        width: 300px;
        padding: 16px;
    }

</style>
