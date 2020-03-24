<template>
    <div class="wait">
        <h1 v-if="!connecting" class="font-weight-light ma-10">Finding you an {{categoryName}} agent...</h1>
        <h1 v-if="connecting" class="font-weight-light ma-10">Connecting you with our agent now!</h1>
        <v-progress-circular
                v-if="!connecting"
                :size="200"
                :width="7"
                color="green"
                indeterminate
        ></v-progress-circular>
        <v-progress-circular
                v-if="connecting"
                :size="200"
                :width="7"
                :value="loading"
                :rotate="-90"
                color="green"
        >{{loading}}</v-progress-circular>
        <v-btn @click="cancel" v-if="!connecting" x-large depressed class="ma-10 green white--text">
            <v-icon large left>close</v-icon>
            <h2 class="font-weight-medium">Cancel</h2>
        </v-btn>
    </div>
</template>

<script>

export default {
    name: "Waitpage",
    props:{
        connecting: Boolean,
        loading: Number
    },
    // data: () => ({
    //     category: 0
    // }),
    methods: {
        cancel: function () {
            this.$router.push({path: "/"});
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
</style>
