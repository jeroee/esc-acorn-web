<template>
  <div class="call">
    <transition name="fade">
      <Waitpage v-bind:connecting="connecting" v-bind:loading="loading" v-if="start" />
    </transition>
    <div class="callbox" id="app">
      <audio id="globalAudioTag" autoplay style="display:none;"></audio>     <!--to allow customer to receive audio from agent-->
      <v-app id="inspire">
        <div class="text-xs-center">
          <v-dialog max-width="1000" v-model="dialog" width="500">
            <template v-slot:activator="{ on }">
              <div class="page1">
                <v-btn @click="startCall" color="red lighten-2" dark v-on="on">Start Call</v-btn>
                <v-btn @click="endCall"> End Call </v-btn>
              </div>
            </template>

            <v-card>
              <v-card-title class="headline grey lighten-2" primary-title>Customer Name</v-card-title>
              <v-card-text class="headline grey lighten-2" process>Calling .....</v-card-text>
              <v-card-text>Here should put some functionality. Input Layout</v-card-text>

              <v-divider></v-divider>
              <v-container class="my-5">
                <v-layout row>
                  <v-flex xs12 md4>
                    <v-btn class="mx-10" fab dark small color="grey dark-2">
                      <v-icon>mic_off</v-icon>
                    </v-btn>
                  </v-flex>

                  <br />

                  <v-flex xs6 md4>
                    <v-btn class="mx-10" fab dark small color="grey dark-2">
                      <v-icon>keyboard</v-icon>
                    </v-btn>
                    <br />
                    <br />
                  </v-flex>

                  <v-flex xs6 md4>
                    <!-- <button color="grey lighten-2" flat @click="dialog = false" depressed  class=" btn2"> 
                    <v-icon>speaker_phone</v-icon>
                    </button>-->
                    <v-btn class="mx-10" fab dark small color="grey dark-2">
                      <v-icon>speaker_phone</v-icon>
                    </v-btn>
                    <br />
                    <br />
                  </v-flex>
                </v-layout>

                <v-layout row>
                  <v-flex xs12 md4>
                    <!-- <button color="grey lighten-2" flat @click="dialog = false" depressed  class=" btn2"> 
                    <v-icon>add_ic_call</v-icon>
                    </button>-->
                    <v-btn class="mx-10" fab dark small color="grey dark-2">
                      <v-icon>add_ic_call</v-icon>
                    </v-btn>
                    <br />
                    <br />
                  </v-flex>

                  <v-flex xs6 md4>
                    <!-- <button color="grey lighten-2" flat @click="dialog = false" depressed  class=" btn2"> 
                    <v-icon>volume_down</v-icon>
                    </button>-->

                    <v-btn class="mx-10" fab dark small color="grey dark-2">
                      <v-icon>volume_down</v-icon>
                    </v-btn>
                  </v-flex>

                  <v-flex xs6 md4>
                    <!-- <button color="grey lighten-2" flat @click="dialog = false" depressed  class=" btn2"> 
                    <v-icon>contact_phone</v-icon>
                    </button>-->

                    <v-btn class="mx-10" fab dark small color="grey dark-2">
                      <v-icon>contact_phone</v-icon>
                    </v-btn>
                  </v-flex>
                </v-layout>
              </v-container>

              <v-divider></v-divider>
              <v-card-actions>
                <v-spacer></v-spacer>
                <button color="red lighten-2" flat @click="dialog = false" depressed class="btn">
                  <v-icon>call_end</v-icon>
                </button>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </div>
      </v-app>
    </div>
  </div>
</template>

<script>
import rainbowSDK from "rainbow-web-sdk";
//import axios from "axios";
import Waitpage from "./Waitpage";

export default {
  name: "Call",
  components: { Waitpage },
  data: () => ({
    selectedIndex: 0,
    contact: "",
    call:"",
    start: true,
    connecting: false,
    loading: 0
  }),
  created() {
    document.addEventListener(rainbowSDK.RAINBOW_ONLOADED, this.onLoaded);
    rainbowSDK.load();
    this.getConnection();
  },
  methods: {
    onLoaded: function() {
      var applicationID = "a58cfac05b0711eabf7e77d14e87b936";
      var applicationSecret =
        "JnjQaOpCW9Pc3u2IUQAvyjyiAEINpBo47Vb5S3jSUxHdgQkc3pqFFXGHJPojXbGu";
      rainbowSDK.setVerboseLog(false);
      rainbowSDK
        .initialize(applicationID, applicationSecret)
        .then(function() {
          console.log("[DEMO] :: Rainbow SDK is initialized!");
        })
        .catch(function(err) {
          console.log("[DEMO] :: Something went wrong with the SDK...", err);
        });

      console.log("I'm in onLoaded");
    },

    getConnection: async function() {
      console.log("connecting: ", this.connecting);
      console.log("start:", this.start);
      if (rainbowSDK.webRTC.canMakeAudioVideoCall()) {
        //check if browser is compatible for audio calls
        console.log("Browser supports calls");
      } else {
        console.log("Browser does not support calls");
      }
      console.log("requesting microphone access");
      navigator.mediaDevices //authoerise the application to access media device
        .getUserMedia({ audio: true })
        .then(function(stream) {
          stream.getTracks().forEach(function(track) {
            track.stop();
          });
          navigator.mediaDevices
            .enumerateDevices()
            .then(function(devices) {
              devices.forEach(function(device) {
                if (device.deviceId == "default") {
                  console.log(device);
                  console.log(device.label, "is available");
                }
              });
            })
            .catch(function(error) {
              console.log(error);
            });
          rainbowSDK.webRTC.useMicrophone("default");
          rainbowSDK.webRTC.useSpeaker("default");
        })
        .catch(function(error) {
          console.log(error);
        });

      // let response = await axios.get(
      //   `http://still-sea-41149.herokuapp.com/api/agentss?category=${this.selectedIndex}` //obtain agent through category
      // );
      // let agent_id = response.data.agent.rainbowId; //get agent id
      // let agent_name = response.data.agent.name; //get agent name
      // let token = response.data.token; //get guest token
      let agent_id = "5e4950b6e9f12730636972b5";
      let token =
        "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb3VudFJlbmV3ZWQiOjAsIm1heFRva2VuUmVuZXciOjcsInVzZXIiOnsiaWQiOiI1ZTc2Y2I5ZGY0M2ZjMzZhYjBmM2MxOTEiLCJsb2dpbkVtYWlsIjoieDZvOTV3ZGw4NnU0a2czOGI0MTl5MjIydHJoNHBibXM3dGc1ZmozNkBhNThjZmFjMDViMDcxMWVhYmY3ZTc3ZDE0ZTg3YjkzNi5zYW5kYm94Lm9wZW5yYWluYm93LmNvbSJ9LCJhcHAiOnsiaWQiOiJhNThjZmFjMDViMDcxMWVhYmY3ZTc3ZDE0ZTg3YjkzNiIsIm5hbWUiOiJhY29ybi1iYWNrZW5kIn0sImlhdCI6MTU4NDg0MzY3OCwiZXhwIjoxNTg2MTM5Njc4fQ.j_R7Cac07qYDZCDC8DbGctEK49ep8mM8VbiK2wpFQpWvUy9kmvpDvoRtCRYPHxpneBBQVjWIvvv7x4d1_BO3L8IO1GxcjW8WLx6kljhuieToBo5JuzN5udDdMVM7XcasbirMXnd2MxuwpTspUip25_CcCp4XwansFtwxBIrybHUyo6LZA42w1_dlr6zcdRuslv-gTSoJ35P18C28xJJe7LxFGzkLqYvcMGBD4ln-3XdclXX5Gp10h42n0xBdAKvBMd8SF37DmAdAuN1wpAxFYCN6ogak-Xu67jfh9o7fEIYQ7hfRDcnaI06T1PccD6O1TUbjDZtVp69ET0ymr1Yq9Q";
      console.log("agent ID is: ", agent_id);
      //console.log("agent name is: ", agent_name);
      console.log("token is: ", token);
      let account = await rainbowSDK.connection.signinSandBoxWithToken(token); //login to rainbow server with guest token
      if (account) {
        console.log("sign in success");
        this.contact = await rainbowSDK.contacts.searchById(agent_id); //get contact from agent id
        console.log(this.contact);
        this.start = false;
        console.log(this.start);
        // var call = rainbowSDK.webRTC.callInAudio(this.contact);
        // if (call.label === "OK") {
        //   console.log("your call has een correctly initialised");
        // }`
      }
    },
    startCall: function() {
      this.call = rainbowSDK.webRTC.callInAudio(this.contact);
      if (this.call.label === "OK") {
        console.log("your call has been correctly initialised");
      }
    },
    endCall: function() {
      rainbowSDK.webRTC.release(this.call);
    }
  }
};
</script>


<style>
.btn {
  background-color: red;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  margin-bottom: 10px;
  opacity: 0.8;
}

.btn2 {
  background-color: grey;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  margin-bottom: 10px;
  opacity: 0.8;
}

/* .btn {
   height: 200px;
   width: 200px;
   background: pink;
   position: relative;

} */
</style>