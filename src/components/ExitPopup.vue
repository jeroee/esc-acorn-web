<template>
    <div id="app">
<v-app id="inspire">
    <v-row justify="center">
      <v-btn
        color="primary"
        dark
        @click.stop="dialog = true"
      >
        Cancel
      </v-btn>
  
      <v-dialog
        v-model="dialog"
        max-width="290"
      >
        <v-card>
          <v-card-title class="headline">Do you want to leave now ? </v-card-title>
  
          <v-card-text>
           
          </v-card-text>
  
          <v-card-actions>
            <v-spacer></v-spacer>
  
            <v-btn to="/"
              color="green darken-1"
              text
              @click="dialog = false"
            >
              Leave
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