<template>
  <nav>
    <v-card>
      <v-navigation-drawer permanent>
        <v-system-bar></v-system-bar>
        <v-list>
          <v-list-item>
            <v-list-item-avatar>
              <v-img src="https://listverse.com/wp-content/uploads/2018/05/9c-taylor-swift-1280x720.jpg"></v-img>
            </v-list-item-avatar>
          </v-list-item>
  
          <v-list-item link>
            <v-list-item-content>
              <v-list-item-title class="title">Chen Yan</v-list-item-title>
              <v-list-item-subtitle>chen_yan@mymail.sutd.edu.sg</v-list-item-subtitle>
            </v-list-item-content>
  
          </v-list-item>
        </v-list>
        <v-divider></v-divider>

        <v-list nav dense>
          <v-list-item-group v-model="item" color="primary" >
            <v-list-item v-for="(item, i) in items" :key="i" @click="items.route"  :to="item.link" link>
              <v-list-item-icon>
                <v-icon v-text="item.icon"></v-icon>
              </v-list-item-icon>
  
              <v-list-item-content>
                <v-list-item-title v-text="item.text"></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-item-group>
        </v-list> 
      </v-navigation-drawer>
    </v-card>
</nav>

</template>

<script>
export default {
  data: () => ({
    item: 0,
    items: [
      { text: 'My Personal Information', icon: 'perm_identity' ,link:'/PersonalInformation'},
      { text: 'Customers Information list', icon: 'mdi-account-multiple',link:'/CustomersList'},
      { text: 'Agent Informations list', icon: 'emoji_people',link:'AgentList' },
      { text: 'Calling Now', icon: 'call',link:'/Calling' },
      { text: 'Chatting Now', icon: 'chat',link:'/Chatting' },
      { text: 'Notification', icon: 'notification_important',link:'notification' },
    ],
  }),
}
</script>