<template>
    <div class="chat">
        <h1>Hey, lets chat</h1>
        <li v-for= "post in posts" :key="post.body">
            {{post.id}}
            <br>
            {{post.body}}
        </li>
    </div>
</template>

<script>
    import axios from 'axios'
    export default {
        name: "Chat",
        data(){
            return {
                posts:[]
            }
        },
        mounted(){
            axios.get(`http://jsonplaceholder.typicode.com/posts`)
            .then(response => {this.posts = response.data})
            
        }
    }
</script>

<style scoped>

</style>