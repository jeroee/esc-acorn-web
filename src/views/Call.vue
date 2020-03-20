<template>
  <div class="call">
    <h1>Hey, Call me pls</h1>
    <p>Category selected is {{selectedIndex}}</p>
    <video id="minivideo" autoplay muted></video>
    <video id="largevideo" autoplay></video>
    <video id="globalVideoTag" autoplay style="display:none;"></video>
    <audio id="globalAudioTag" autoplay style="display:none;"></audio>
    <v-btn @click.native="calling">Click me to Call</v-btn>
  </div>
</template>

<script>
import rainbowSDK from "rainbow-web-sdk";
import axios from "axios";

export default {
  name: "Call",
  data: () => ({
    selectedIndex: 0,
    contact: ""
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

      let response = await axios.get(
        `http://still-sea-41149.herokuapp.com/api/agentss?category=${this.selectedIndex}` //obtain agent through category
      );
      let agent_id = response.data.agent.rainbowId; //get agent id
      let agent_name = response.data.agent.name; //get agent name
      let token = response.data.token; //get guest token
      // let agent_id = "5e4950b6e9f12730636972b5";
      // let token =
      //   "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb3VudFJlbmV3ZWQiOjAsIm1heFRva2VuUmVuZXciOjcsInVzZXIiOnsiaWQiOiI1ZTc0N2JmMGY0M2ZjMzZhYjBmM2I1NzIiLCJsb2dpbkVtYWlsIjoiMjRubTdqdzZxNXBxN3BnNGNuNzd4eWU4NzU1YjB4bmxkYWFtMzg3Y0BhNThjZmFjMDViMDcxMWVhYmY3ZTc3ZDE0ZTg3YjkzNi5zYW5kYm94Lm9wZW5yYWluYm93LmNvbSJ9LCJhcHAiOnsiaWQiOiJhNThjZmFjMDViMDcxMWVhYmY3ZTc3ZDE0ZTg3YjkzNiIsIm5hbWUiOiJhY29ybi1iYWNrZW5kIn0sImlhdCI6MTU4NDY5MjIwOCwiZXhwIjoxNTg1OTg4MjA4fQ.t8myU5bOjL1RSDT5FKO4cr8k6CihLQCEyxjSFHfbYPIx2dOZcQxcN4to4EFNFSpnKryc9YmMImLbi7nKpLUQt-RN7NTCH33YtjH5mQbisxE3K0uSgCBUU2gHbRaH-4z7NTW-1TPuEc1AVATWxTOUuiubntJdl4OfsHrHvFi3paWhIk4JGBFIjSCbSNSeJkrqKGpoLuATZ5pEOZwfi4YZi-MQQsCX_5V2ys5tsWl7fxO4Bpn3OaF_7HobIzfsJA8wxC7zfkcMtMZwaL4_YlbY7_NXdd5QXoAPT7cKIYpZEbBHwYGDWHZ9p1RbcdhLEC-WfpYre3gHEKu81nJ7GpPZKA";
      console.log("agent ID is: ", agent_id);
      console.log("agent name is: ", agent_name);
      console.log("token is: ", token);
      let account = await rainbowSDK.connection.signinSandBoxWithToken(token); //login to rainbow server with guest token
      if (account) {
        console.log("sign in success");
        this.contact = await rainbowSDK.contacts.searchById(agent_id); //get contact from agent id
        console.log(this.contact);
        // var call = rainbowSDK.webRTC.callInAudio(this.contact);
        // if (call.label === "OK") {
        //   console.log("your call has been correctly initialised");
        // }
      }
    },
    calling: function() {
      var call = rainbowSDK.webRTC.callInAudio(this.contact);
      if (call.label === "OK") {
        console.log("your call has been correctly initialised");
      }
    }
  }
};
</script>

<style scoped>
</style>