<template>
    <div style="min-height: 100vh">
        <div class="hero mb-10">
            <v-container class="flex-col">
                <h1 class="display-3 font-weight-medium">Welcome to Acorn Support</h1>
            </v-container>
        </div>
        <v-container class="flex-col">
            <v-select v-model="selected" style="width: 400px"
                :items="categories"
                outlined
                :menu-props="{ offsetY:true, openOnClick:false }"
                label="Choose a support category..."
            ></v-select>
            <div style="flex-direction: row">
                <v-btn @click="setCategory" x-large depressed class="ma-5 green white--text" :disabled="isSelected">
                    <v-icon left>message</v-icon> Get Chat Support
                </v-btn>
                <v-btn @click="alert" x-large depressed class="ma-5 green white--text" :disabled="isSelected">
                    <v-icon left>phone</v-icon> Get Call Support
                </v-btn>
            </div>
        </v-container>

    </div>
</template>

<script>
// import axios from 'axios'

export default {
    name: "Home",
    data: () => ({
        categories: [
            'Acorn Products','Acorn Services','Acorn Applications'
        ],
        selected: ''
    }),
    computed: {
        isSelected: function () {
            return this.selected==="";
        }
    },
    methods: {
        alert: function () {
            alert('Coming Soon')
        },
        setCategory: function () {
            if (this.selected) {
                this.$store.state.categoryIndex = this.categories.indexOf(this.selected);
                this.$store.state.categoryName = this.selected;
                this.$router.push({path: "/chat"});
            }
        }
    },
}
</script>

<style scoped>
.flex-col {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100%;
}
.hero{
    background-image: url("../assets/acorn_bg.png");
    height: 400px;
    background-position: center;
    background-size: cover;
}
</style>
