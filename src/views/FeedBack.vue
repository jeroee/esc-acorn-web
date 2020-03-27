<template>
    <div id="app">
  <v-app id="inspire">
    <v-row justify="center">
        <v-card>
          <v-toolbar dark color="primary">
            <v-toolbar-title>FeedBack</v-toolbar-title>
            <v-spacer></v-spacer>

            <v-toolbar-items>
              <v-btn to="/" dark text @click="dialog = false" id="SubmitButton">Submit</v-btn>
            </v-toolbar-items>
          </v-toolbar>
          
          <!-- Write your opinions  -->
                  <v-card>
                    <v-card-text>
                        <v-container>
                        <v-row>
                            <v-col cols="12" sm="6" md="4">
                            <v-text-field id="FirstName" label="Legal first name*" required></v-text-field>
                            </v-col>
                            <v-col cols="12" sm="6" md="4">
                            <v-text-field label="Legal middle name" hint="example of helper text only on focus" id="MiddleName"></v-text-field>
                            </v-col>
                            <v-col cols="12" sm="6" md="4">
                            <v-text-field id="LastName"
                                label="Legal last name*"
                                hint="example of persistent helper text"
                                persistent-hint
                                required
                            ></v-text-field>
                            </v-col>
                            <v-col cols="12">
                            <v-text-field id="Email" label="Email*" required></v-text-field>
                            </v-col>
                            <v-col cols="12">
                            <v-text-field id="comments" label="comments" required></v-text-field>
                            </v-col>

                            <v-col cols="12" sm="6">
                            <v-select id="SelectVoice"
                                :items="['bad', 'average', 'good', 'very good']"
                                label=" Voice"
                                required
                            ></v-select>
                            </v-col>


                            <v-col cols="12" sm="6">
                            <v-select id="SelectAttitude"
                                :items="['bad', 'average', 'good', 'very good']"
                                label=" attitude"
                                required
                            ></v-select>
                            </v-col>

                        </v-row>
                        </v-container>
                        </v-card-text>
                    </v-card>
        </v-card>
    </v-row>
  </v-app>
</div>
</template>


<script>
export default {
    
}
</script>