<template>
    <div class="topNav" id="myTopnav">
        <a class="logo"></a>
        <a class="responsive">Wac</a>
        <a class="responsive">aPad</a>
        <a class="responsive">aPhone</a>
        <a class="responsive">Watch</a>
        <a class="responsive">TV</a>
        <a class="responsive">Music</a>
        <a href="/" class="active">Support</a>
        <a><v-icon style="color: #f5f5f7">search</v-icon></a>
    </div>
</template>
<script>
    export default {
        name: "Menu"
    }
</script>
<style scoped>
    .topNav {
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: center;
        background-color: #312E2F;
        overflow: hidden;
    }

    a {
        display: flex;
        flex-direction: column;
        opacity: 0.7 !important;
        color: white;
        text-align: center;
        padding: 8px;
        text-decoration: none;
        font-size: 14px;
        font-weight: 400;
        width: 100px;
        cursor: pointer;
        transition: opacity 200ms;
    }

    a:hover {
        opacity: 1.0 !important;
    }

    .active {
        opacity: 1.0 !important;
    }

    .logo {
        height: 20px;
        background-image: url("../assets/acornlogo.svg");
        background-size: contain;
        background-position: center;
        background-repeat: no-repeat;
    }

    @media only screen and (max-width: 600px) {
        .responsive {
            display: none;
        }

        .topNav{
            justify-content: space-between;
        }
    }
</style>





