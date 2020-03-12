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
export default {
  name: 'App',

  components: {
  },

  data: () => ({
    //
  }),
};
</script>

<style>
</style>
