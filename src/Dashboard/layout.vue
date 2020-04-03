<template>
<div>
    <!-- Top bar -->
    <div>
    <v-toolbar flat app>
            <v-toolbar-side-icon class="grey--text" @click="drawer = !drawer"></v-toolbar-side-icon>
            <v-toolbar-title class="text-uppercase grey--text">
                <span class ="font-weight-light">Admin panel</span>
                <span>Acorn</span>
            </v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn flat color="grey">
                <span>Sign Out</span>
                <v-icon right>exit_to_app</v-icon> 
            </v-btn>
        </v-toolbar>
    </div>
    
    <!-- Contain -->
    <div id="app">
    <v-app id="inspire">
        <v-container fluid grid-list-md>
        <v-layout row wrap>
            <!-- 1st Column -->
            <v-flex d-flex xs12 sm4 md2 xl3>
            <Sidebar></Sidebar>
            </v-flex>

            <!-- Second Column -->
            <v-flex d-flex xs12 sm6 md3 xl3>
            <v-layout row wrap>
                <v-flex d-flex>
                <v-card color="indigo" dark>
                    <v-card-text>{{ lorem.slice(0, 70) }}</v-card-text>
                </v-card>
                </v-flex>
                <v-flex d-flex>
                
                <v-layout row wrap>
                    <v-flex
                    v-for="n in 2"
                    :key="n"
                    d-flex
                    xs12
                    >
                    <v-card
                        color="red lighten-2"
                        dark
                    >
                        <v-card-text>{{ lorem.slice(0, 70) }}</v-card-text>
                    </v-card>
                    </v-flex>
                </v-layout>
                </v-flex>
            </v-layout>
            </v-flex>

            <!-- Third Column -->
            <v-flex d-flex xs12 sm6 md2 xl 3 child-flex>
            <v-card color="green lighten-2" dark>
                <v-card-text>{{ lorem.slice(0, 90) }}</v-card-text>
            </v-card>
            </v-flex>

            <!-- Fouth column -->
            <v-flex d-flex xs12 sm6 md3 xl3>
            <v-card color="blue lighten-2" dark>
                <v-card-text>{{ lorem.slice(0, 100) }}</v-card-text>
            </v-card>
            </v-flex>
        </v-layout>
        <!-- Bottom Part -->
        <!-- <Bottom></Bottom> -->
        </v-container>
    </v-app>
    </div>
</div>
</template>

<script>

import Sidebar from './Sidebar'
//import Bottom from './Bottom'


export default {
    components:{Sidebar},
    data: () => ({
    lorem: `Put somethings herevhjfhjsdjhgfiwkdgvscavsdkhcbhsdbvckdgvhgdvhdgvckdgvudggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg.`
  })
}
</script>