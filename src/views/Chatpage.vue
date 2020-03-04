<template>
    <div class="chat">
        <h1 class="font-weight-light ma-10">Let's chat</h1>
    </div>
</template>

<script>
    export default {
        name: "Chatpage",
        mounted() {
            console.log("Page entered")
        }
    }
</script>

<style scoped>
.chat {
    display:flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
}
</style>
