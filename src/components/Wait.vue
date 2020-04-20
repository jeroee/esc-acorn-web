<template>
    <div class="wait">
        <h1 v-if="!connecting" class="font-weight-light text-center ma-10">Finding you an {{categoryName}} agent...</h1>
        <h1 v-if="connecting" class="font-weight-light text-center ma-10">We're connecting you with our agent, please hold on.</h1>
        <v-progress-circular
                v-if="!connecting"
                :size="200"
                :width="7"
                :color="color"
                indeterminate
        ></v-progress-circular>
        <v-progress-circular
                v-if="connecting"
                :size="200"
                :width="7"
                :value="loading"
                :rotate="-90"
                :color="color"
        >{{loading}}</v-progress-circular>

        <v-btn id="cancel" @click="cancel" v-if="!connecting" x-large depressed :color="color" class="ma-10 white--text">
            <v-icon large left>close</v-icon>
            <h2 class="font-weight-medium">Cancel</h2>
        </v-btn>
    </div>
</template>

<script>

export default {
    name: "Wait",
    props:{
        connecting: Boolean,
        loading: Number,
        color: String
    },
    methods: {
        cancel: function () {
            this.$store.state.support=false;
            this.$router.push({path: "/"});
            this.$socket.disconnect();
            console.log("Session Ended")
        }
    },
    computed: {
        categoryName() {
            return this.$store.state.categoryName;
        }
    },
}
</script>

<style scoped>
.wait {
    position: fixed;
    display:flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
    width: 100vw;
    z-index: 100;
    background-color: white;
}

@media only screen and (max-width: 768px) {
    h1{

    }
}
</style>
