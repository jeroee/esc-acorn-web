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
        document.addEventListener(rainbowSDK.RAINBOW_ONREADY, function () {
            console.log('Rainbow SDK Ready');
        });
        document.addEventListener(rainbowSDK.RAINBOW_ONLOADED, function () {
            var applicationID = "a58cfac05b0711eabf7e77d14e87b936";
            var applicationSecret = "JnjQaOpCW9Pc3u2IUQAvyjyiAEINpBo47Vb5S3jSUxHdgQkc3pqFFXGHJPojXbGu";
            rainbowSDK
                .initialize(applicationID, applicationSecret)
                .then(() => {
                    console.log('Rainbow SDK Initialized');
                })
                .catch(err => {
                    console.log('Something went wrong with the Rainbow SDK.', err);
                });
        });
        rainbowSDK.start();
        rainbowSDK.load();

    },
};
</script>

<style>
</style>
