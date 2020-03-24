
<template>
    <div style="min-height: 100vh">
        <div class="hero mb-10">
            <v-container class="flex-col">
                <h1 class="display-3 font-weight-medium">Welcome to Acorn Support</h1>
            </v-container>
        </div>

        <div>
            <CallButton></CallButton>
        </div>

        <br>
        <br>
        <br>
            
        <v-container class="flex-col">
            <v-select  v-model="selected" v style="width: 400px"
                :items="categories"
                outlined
                :menu-props="{ offsetY:true, openOnHover:true, openOnClick:false }"
                label="Choose a support category..."
            ></v-select>

            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <div style="flex-direction: row">
                <div style="flex-direction: row">
                    <v-btn to="/chat" @click="getAgentId" x-large depressed class="ma-5 green white--text">
                        <v-icon left>message</v-icon> Get Chat Support
                    </v-btn>

                    <v-btn to="/QandAPage1" id="Call" @click="getAgentId" x-large depressed class="ma-5 green white--text">
                        <v-icon left>message</v-icon> FAQ
                    </v-btn>
                     <!-- This  is the full page for call -->
                    <v-btn to="/call" x-large depressed class="ma-5 green white--text">
                        <v-icon left>phone</v-icon> Get Call Support
                    </v-btn> 
                </div>
            </div>

            <!-- This is the pop up call Page    -->
            <popup></popup>
           
           
        </v-container>

    </div>
</template>

<script>
import axios from 'axios'
import popup from '../components/popup'
//import menu  from '../components/menu'
import CallButton from '../components/CallButton'


export default {
    components:{popup,CallButton},
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
