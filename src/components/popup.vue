
<template>
<div class= "call" id="app">
  <v-app id="inspire">
    <div class="text-xs-center">
      <v-dialog max-width = "1000" v-model="dialog" width="500">
        
        <template v-slot:activator="{ on }">
          <v-btn class="ma-5 green white--text" x-large depressed  v-on="on"> 
               <v-icon left>phone</v-icon> Start Call </v-btn>
        </template>
        <v-card>
          <v-card-title class="headline grey lighten-2" primary-title> Customer Name</v-card-title>
          <v-card-text  class="headline grey lighten-2" process> Calling .....</v-card-text>
          <v-card-text>
            Here should put some functionality. Input Layout 
          </v-card-text>

          <v-divider></v-divider>
          <v-container class = "my-5">
            <v-layout row>
                <v-flex xs12 md4>
                    <v-btn class="mx-10" fab dark small color="grey dark-2">
                        <v-icon>mic_off</v-icon>
                    </v-btn>

                </v-flex>

                <br>

                <v-flex xs6 md4>
                    <v-btn class="mx-10" fab dark small color="grey dark-2">
                    <v-icon>keyboard</v-icon>
                    </v-btn>
                    <br>
                    <br>
                </v-flex>

                <v-flex xs6 md4>
                    <!-- <button color="grey lighten-2" flat @click="dialog = false" depressed  class=" btn2"> 
                    <v-icon>speaker_phone</v-icon>
                    </button> -->
                    <v-btn class="mx-10" fab dark small color="grey dark-2">
                    <v-icon>speaker_phone</v-icon>
                    </v-btn>
                    <br>
                    <br>
                </v-flex>
            </v-layout>

             <v-layout row>
                <v-flex xs12 md4>
                    <!-- <button color="grey lighten-2" flat @click="dialog = false" depressed  class=" btn2"> 
                    <v-icon>add_ic_call</v-icon>
                    </button> -->
                    <v-btn class="mx-10" fab dark small color="grey dark-2">
                    <v-icon>add_ic_call</v-icon>
                    </v-btn>
                    <br>
                    <br>
                </v-flex>

                <v-flex xs6 md4>
                    <!-- <button color="grey lighten-2" flat @click="dialog = false" depressed  class=" btn2"> 
                    <v-icon>volume_down</v-icon>
                    </button> -->

                    <v-btn class="mx-10" fab dark small color="grey dark-2">
                    <v-icon>volume_down</v-icon>
                    </v-btn>

                </v-flex>

                <v-flex xs6 md4>
                    <!-- <button color="grey lighten-2" flat @click="dialog = false" depressed  class=" btn2"> 
                    <v-icon>contact_phone</v-icon>
                    </button> -->

                     <v-btn class="mx-10" fab dark small color="grey dark-2">
                    <v-icon>contact_phone</v-icon>
                    </v-btn>
                </v-flex>
            </v-layout>
          </v-container>

          <v-divider></v-divider>
          <v-card-actions><v-spacer></v-spacer>
            <button color="red lighten-2" flat @click="dialog = false" depressed  class=" btn"> 
                 <v-icon>call_end</v-icon>
            </button>

          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </v-app>
</div>
    
</template>

<script>
// export default {
//      data () {
//     return {
//       dialog: false
//     }
//   }
    
// }

</script>


<style>
.btn {
  background-color: red;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  margin-bottom:10px;
  opacity: 0.8;
}

.btn2 {
  background-color: grey;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  margin-bottom:10px;
  opacity: 0.8;
}

</style>
