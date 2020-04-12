<template>
    <div>
        <Menu/>
        <div class="hero mb-10">
            <v-container class="flex-col">
                <h1 class="display-2 font-weight-medium">Welcome to Acorn Support</h1>
            </v-container>

        </div>

<!--        <FirstPagePic></FirstPagePic>-->

        <v-container class="flex-col">
            <div class="flex-row">
                <v-text-field
                        id="FirstName"
                        outlined
                        v-model="name1"
                        class="name-field"
                        color="green"
                        :counter="limit"
                        label="Please provide your first name"
                        solo
                        flat/>

                <v-text-field
                        id="LastName"
                        outlined
                        v-model="name2"
                        class="name-field"
                        color="green"
                        :counter="limit"
                        label="Please provide your last name"
                        solo
                        flat/>
            </div>
            <v-select
                    id="category"
                    v-model="selected"
                    style="width: 400px"
                    :items="categories"
                    outlined
                    color="green"
                    :menu-props="{ offsetY:true, openOnClick:false }"
                    label="Choose a support category..."
            />

            <div style="flex-direction: row">
                <v-btn
                        id="Chat"
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
                        id="Call"
                        @click="requestCall"
                        x-large
                        depressed
                        class="ma-5 blue white--text"
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
            isSelected() {
                return this.name2 === "" || this.name2.length > this.limit || this.name1 === "" || this.selected === "" || this.name1.length > this.limit;
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
                if (!this.isSelected) {
                    this.submit();
                    this.$router.push({path: "/chat"});
                }
            },

            requestCall: function () {
                if (!this.isSelected) {
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
