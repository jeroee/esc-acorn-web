<template>
<!-- This is not a pop up page , is a full page for calling  -->
<div class= "call" id="app">
  <!-- step1 Add the transition Part  -->
        <transition name="fade">
            <CallWaitpage v-bind:connecting="connecting" v-bind:loading="loading" v-if="start"/>
        </transition>


  <!--  step2 Add the Call Page  -->
        <v-app id="inspire">
          <div class="text-xs-center">
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
                          <v-btn class="mx-10" fab dark small color="grey dark-2">
                          <v-icon>speaker_phone</v-icon>
                          </v-btn>
                          <br>
                          <br>
                      </v-flex>
                  </v-layout>

                  <v-layout row>
                      <v-flex xs12 md4>
                          <v-btn class="mx-10" fab dark small color="grey dark-2">
                          <v-icon>add_ic_call</v-icon>
                          </v-btn>
                          <br>
                          <br>
                      </v-flex>

                      <v-flex xs6 md4>
                          <v-btn class="mx-10" fab dark small color="grey dark-2">
                          <v-icon>volume_down</v-icon>
                          </v-btn>

                      </v-flex>

                      <v-flex xs6 md4>
                          <v-btn class="mx-10" fab dark small color="grey dark-2">
                          <v-icon>contact_phone</v-icon>
                          </v-btn>
                      </v-flex>
                  </v-layout>
                </v-container>

                <v-divider></v-divider>
                <v-card-actions><v-spacer></v-spacer>
                  
                  <v-btn to="/FeedBack" @click="alert" x-large depressed color =" red white--text" class="btn">
                          <v-icon left>call_end</v-icon> reject
                      </v-btn>  
<!-- Step3 when you click 'Call_end', you will go to the feedback page  -->
                  
                </v-card-actions>
              </v-card>
          </div>
        </v-app>
</div>
    
    
</template>

<script>
import CallWaitpage from "./CallWaitpage";
export default {
  components:{CallWaitpage},

  
}
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

/* .btn {
   height: 200px;
   width: 200px;
   background: pink;
   position: relative;

} */



</style>