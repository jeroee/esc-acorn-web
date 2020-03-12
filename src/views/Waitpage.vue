<template>
    <div class="wait">
        <h1 class="font-weight-light ma-10">Connecting you with our agent...</h1>
        <v-progress-circular
                :size="200"
                :width="7"
                color="green"
                indeterminate
        ></v-progress-circular>
        <v-btn to="/" x-large depressed class="ma-10 green white--text">
            <v-icon large left>close</v-icon>
            <h2 class="font-weight-medium">Cancel</h2>
        </v-btn>
    </div>
</template>

<script>
    export default {
        name: "Waitpage",
        mounted() {
            this.interval = setTimeout(
                function () {
                    this.$router.push({path: "/chat"});
                }.bind(this),
                3000
            );
        }
    }
</script>

<style scoped>
.wait {
    display:flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
}
</style>
