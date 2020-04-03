<template>
    <div id="app">
      <v-app id="inspire">
        <v-container fluid>
            <v-row justify="center">
                <v-subheader>Calling Now </v-subheader>
        
                <v-expansion-panels popout>
                <v-expansion-panel v-for="(message, i) in messages" :key="i" hide-actions>
                    <v-expansion-panel-header>
                      <v-row align="center" class="spacer" no-gutters>
                        
                        <v-col cols="4" sm="2" md="1">
                        <v-avatar size="36px">
                            <img v-if="message.avatar" alt="Avatar" src="https://icdn5.digitaltrends.com/image/digitaltrends/young-people-having-fun-outdoor-and-making-selfie-with-smart-pho-416x416.jpg">
                            <v-icon v-else :color="message.color" v-text="message.icon"
                            ></v-icon>
                        </v-avatar>
                        </v-col>
        
                        <v-col class="hidden-xs-only" sm="5" md="3">
                        <strong v-html="message.name"></strong>
                        <!-- <span v-if="message.total" class="grey--text"> &nbsp;({{ message.total }})
                        </span> -->
                        </v-col>
        
                        <v-col class="text-no-wrap" cols="5" sm="3">
                        <v-chip v-if="message.new" :color="`${message.color} lighten-4`"
                            class="ml-0 mr-2 black--text" label small>{{ message.new }} calling  
                        </v-chip>
                        <strong v-html="message.title"></strong>
                        </v-col>
        
                        <v-col v-if="message.excerpt" class="grey--text text-truncate hidden-sm-and-down">&mdash;
                        {{ message.excerpt }}
                        </v-col>
                    </v-row>

                    </v-expansion-panel-header>
        
                    <v-expansion-panel-content>
                    <v-divider></v-divider>
                    <v-card-text v-text="lorem"></v-card-text>
                    </v-expansion-panel-content>
                </v-expansion-panel>
                </v-expansion-panels>
            </v-row>
            </v-container>
  </v-app>
</div>
</template>


<script>
export default {
    data: () => ({
    messages: [
      {
        icon: 'people',
        name: 'John ',
        new: '5:00 pm',
        title: 'Product type : ',
        excerpt: 'Thank you for joining our community...',
      },
      {
        color: 'red',
        icon: 'people',
        //avatar: 'https://miro.medium.com/max/4096/1*xfdVlcBKvmqeF4SOPPtMoQ.jpeg',
        name: 'Bob',
        new: "4:54 pm",
        title: 'Product type :',
      },
      {
        icon: 'people',
        //avatar: 'https://miro.medium.com/max/4096/1*xfdVlcBKvmqeF4SOPPtMoQ.jpeg',
        color: 'teal',
        name: 'Alice',
        new: "4:46 pm",
        title: 'Product type :',
        // exceprt: 'New deals available, Join Today',
      },
    ],
    lorem: 'Feed back put here , Here can put some notice or other information.hgggggggggggggggggggggggghghghj',
  })}
</script>