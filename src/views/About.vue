<template>
    <div style="display: flex; flex-direction: column; justify-content: center; min-height: 100vh">
        <v-container class="about">
            <h1 class="display-3 acorn-title pb-10">Welcome to Acorn</h1>
            <v-select style="width: 400px"
                :items="categories"
                outlined
                :menu-props="{ offsetY:true }"
                label="Choose a support category..."
            ></v-select>
            <div style="flex-direction: row">
                <v-btn to="/home" x-large depressed class="ma-5 green white--text">
                    <v-icon left>message</v-icon> Get Chat Support
                </v-btn>
                <v-btn x-large depressed class="ma-5 green white--text">
                    <v-icon left>phone</v-icon> Get Call Support
                </v-btn>
            </div>
        </v-container>
    </div>
</template>

