<template>
    <div id="app">
    <v-carousel
      cycle
      height="400"
      hide-delimiter-background
      hide-delimiters
      :show-arrows="false"
      interval="5000"
    >
      <v-carousel-item
        v-for="(item,i) in items"
          :key="i"
          :src="item.src"
          reverse-transition="fade-transition"
          transition="fade-transition"
      >
      </v-carousel-item>
    </v-carousel>

</div>
</template>

<script>
export default {
    data () {
    return {
      colors: [
        'indigo',
        'warning',
        'pink darken-2',
        'red lighten-1',
        'deep-purple accent-4',
      ],
      slides: [
        'Welcome to Acorn',
        'New Products',
        'New Service',
        'Fourth',
        'Fifth',
      ],

       items: [
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
        },
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
        },
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
        },
        {
          src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
        },
      ],
    }
  }}

</script>

<style scoped>
.text{
  align-items: center;
  justify-content: center;
  color:white;
  font-size:20px;
  position:relative;
  width:400px;
  height:200px;
}
</style>
