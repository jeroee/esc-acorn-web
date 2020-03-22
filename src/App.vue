<template>
    <v-app>
        <v-content>
            <vue-page-transition name="fade-in-up">
              <router-view/>
            </vue-page-transition>
        </v-content>
    </v-app>
</template>

<script>
import rainbowSDK from "rainbow-web-sdk";

export default {      //declaring the page so that u can import later
    name: 'App',

    components: {
    },

    data:()=>({
    //
    }),
    created() {
        document.addEventListener(rainbowSDK.RAINBOW_ONLOADED, this.onLoaded);
        rainbowSDK.load();
    },
    methods:{
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
    }
};
</script>

<style>
</style>
