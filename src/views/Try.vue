<template>
  <nav>
    
    <v-toolbar flat app>
        <v-toolbar-side-icon class="grey--text" @click="drawer = !drawer"></v-toolbar-side-icon>
        <v-toolbar-title class="text-uppercase grey--text">
            <span class ="font-weight-light">Admin panel</span>
            <span>Acorn</span>
        </v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn flat color="grey">
            <span>Sign Out</span>
            <v-icon right>exit_to_app</v-icon> 
        </v-btn>
    </v-toolbar>

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
  
            <!-- <v-list-item-action>
              <v-icon>mdi-menu-down</v-icon>
            </v-list-item-action> -->
          </v-list-item>

        </v-list>
        <v-divider></v-divider>
        <v-list
          nav
          dense
        >
          <v-list-item-group v-model="item" color="primary">
            <v-list-item
              v-for="(item, i) in items"
              :key="i"
            >
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
      
      <div id="app">
  <v-app id="inspire">
    <v-card
      class="mx-auto"
      max-width="400"
    >
      <v-img
        class="white--text align-end"
        height="200px"
        src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
      >
        <v-card-title>Acorn phone 11 pro max </v-card-title>
      </v-img>
  
      <v-card-subtitle class="pb-0">The best </v-card-subtitle>
  
      <v-card-text class="text--primary">
        <div>26mm focal length
              ƒ/1.8 aperture
              6-element lens
              Optical image stabilisation
              100% Focus Pixels
              New 12MP sensor
        </div>
  
        <div>iPhone 11 Pro lets you capture videos that are beautifully true to life, with greater detail and smoother motion. Epic processing power means it can shoot 4K video with extended dynamic range and cinematic video stabilisation — all at 60 fps. You get more creative control too, with 4x more scene and powerful new editing tools to play with.
The highest‑quality</div>
      </v-card-text>
  
      <v-card-actions>
        <v-btn
          color="orange"
          text
        >
          Learn More 
        </v-btn>
  
        <v-btn
          color="orange"
          text
        >
          Explore
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-app>
  
</div>
</nav>

</template>

<script>
export default {
  data: () => ({
    item: 0,
    items: [
      { text: 'My Files', icon: 'mdi-folder' },
      { text: 'Shared with me', icon: 'mdi-account-multiple' },
      { text: 'Starred', icon: 'mdi-star' },
      { text: 'Recent', icon: 'mdi-history' },
      { text: 'Offline', icon: 'mdi-check-circle' },
      { text: 'Uploads', icon: 'mdi-upload' },
      { text: 'Backups', icon: 'mdi-cloud-upload' },
    ],
  }),
}
</script>