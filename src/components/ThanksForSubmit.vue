<template>
    <div id="app">
<v-app id="inspire">
    <v-row justify="center">
      <v-btn
        color="primary"
        dark
        @click.stop="dialog = true"
      >
        Submit
      </v-btn>
  
      <v-dialog
        v-model="dialog"
        max-width="290"
      >
        <v-card>
          <v-card-title class="headline">Thank you for your wonderful advice !</v-card-title>
  
          <v-card-text>
            Thanks for the feedback on your experience with our customer support team. We sincerely appreciate your insight because it helps us build a better customer experience.
If you have any more questions, comments, or concerns or compliments, please feel welcome to reach back out as we would be more than happy to assist.
          </v-card-text>
  
          <v-card-actions>
            <v-spacer></v-spacer>
  
            <v-btn to="/"
              color="green darken-1"
              text
              @click="dialog = false"
            >
              HomePage
            </v-btn>
  
          </v-card-actions>
        </v-card>
      </v-dialog>


    </v-row>
  </v-app>
</div>
</template>

<script>
export default {
  data() {
    return {
      dialog: false };

}
}
</script>