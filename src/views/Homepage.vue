<template>
    <div style="display: flex; flex-direction: column; justify-content: center; min-height: 100vh">
        <v-container class="home">
            <h1 class="display-3 acorn-title pb-10">Welcome to Acorn</h1>
            <v-select v-model="selected" v style="width: 400px"
                :items="categories"
                outlined
                :menu-props="{ offsetY:true }"
                label="Choose a support category..."
            ></v-select>
            <div style="flex-direction: row">
                <v-btn to="/wait" @click="getAgentId" x-large depressed class="ma-5 green white--text">
                    <v-icon left>message</v-icon> Get Chat Support
                </v-btn>
                <v-btn @click="alert" x-large depressed class="ma-5 green white--text">
                    <v-icon left>phone</v-icon> Get Call Support
                </v-btn>
            </div>
        </v-container>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: "Home",
    data: () => ({
        categories: [
            'Acorn Products','Acorn Services','Acorn Applications'
        ],
        selected: 'Acorn Products'
    }),
    methods: {
        alert: function () {
            // `this` inside methods points to the Vue instance
            alert('Coming Soon')
        },
        getAgentId: function () {
            this.loading = true;
            axios.get('https://esc-acorn-backend.herokuapp.com/api/agents', {
                params: {
                    category:this.categories.indexOf(this.selected)+1
                }
            })
            .then(function (response) {
                console.log(response.data);
            })
            .catch(function (error) {
                console.log(error.response.data.error);
            });
        }
    },
}
</script>

<style scoped>
.home {
    display: flex;
    flex-direction: column;
    align-items: center;
}
</style>
