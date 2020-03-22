<template>
    <div>
                 <div id="app">
                            <v-app id="inspire">
                                <v-carousel
                                cycle
                                height="400"
                                hide-delimiter-background
                                show-arrows-on-hover
                                >
                                <v-carousel-item
                                    v-for="(slide, i) in slides"
                                    :key="i"
                                >
                                    <v-sheet
                                    :color="colors[i]"
                                    height="100%"
                                    >
                                    <v-row
                                        class="fill-height"
                                        align="center"
                                        justify="center"
                                    >
                                        <div class="display-3">{{ slide }} Slide</div>
                                    </v-row>
                                    </v-sheet>
                                </v-carousel-item>
                                </v-carousel>
                            </v-app>
                </div>

                <div>
                    <CallButton></CallButton>
                </div>

                 <v-container class="flex-col">
            <v-select v-model="selected" v style="width: 400px"
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

                    <v-btn to="/QandAPage1" @click="getAgentId" x-large depressed class="ma-5 green white--text">
                        <v-icon left>message</v-icon> Question and solution
                    </v-btn>
                </div>
            </div>
            <popup></popup>
           

                <!-- <v-btn to="/call" @click="alert" x-large depressed class="ma-5 green white--text">
                    <v-icon left>phone</v-icon> Get Call Support
                </v-btn> -->   
        </v-container>

      
    </div>
</template>


<script>
import CallButton from '../components/CallButton'
export default {
    components:{CallButton},

     data() {
    return {
      colors: [
      'indigo',
      'warning',
      'pink darken-2',
      'red lighten-1',
      'deep-purple accent-4'],

      slides: [
      'First',
      'Second',
      'Third',
      'Fourth',
      'Fifth'] };


  } }
    
</script>