<template>
  <nav>
    <v-toolbar flat app>
        <v-toolbar-side-icon class="grey--text" @click="drawer = !drawer"></v-toolbar-side-icon>
        <v-toolbar-title class="text-uppercase grey--text">
            <span class ="font-weight-light">Todo</span>
            <span>Acorn</span>
        </v-toolbar-title>
        <v-spacer></v-spacer>
        <v-btn flat color="grey">
            <span>Sign Out</span>
            <v-icon right>exit_to_app</v-icon> 
        </v-btn>
    </v-toolbar>
    
    <v-navigation-drawer app v-model ="drawer" class="primary">
        <v-list>
            <!-- <div class="col">
            <v-list-tile  v-for="link in links" :key="link.text" router :to="link.route">
                <v-list-tile-action>
                    <v-icon class="white--text">{{link.icon}}</v-icon>
                </v-list-tile-action>
                <v-list-tile-content>
                    <v-list-tile-title class="white--text">{{ link.text}}</v-list-tile-title>
                </v-list-tile-content>
            </v-list-tile>
            </div> -->
            <div class="col">
            <v-list-title >
                <v-list-title-content>
                    <v-list-title-action>
                        <v-icon>dashboard</v-icon>
                    </v-list-title-action>
                    <v-list-title-title> DashBoard </v-list-title-title>
                </v-list-title-content>
            </v-list-title>
            </div>

            <div class="col">
             <v-list-title>
                <v-list-title-content>
                    <v-list-title-action>
                        <v-icon>folder</v-icon>
                    </v-list-title-action>
                    <v-list-title-title> Custom in queue </v-list-title-title>
                </v-list-title-content>
            </v-list-title>
            </div>


             <div class="col">
             <v-list-title>
                <v-list-title-content>
                    <v-list-title-action>
                        <v-icon>dashboard</v-icon>
                    </v-list-title-action>
                    <v-list-title-title> DashBoard </v-list-title-title>
                </v-list-title-content>
            </v-list-title>
             </div>

        </v-list>
    </v-navigation-drawer>

  </nav>
</template>

<script>
export default {
    data(){
        return{
            drawer:true,
            links:[
                {icon:'dashboard',text:'DashBoard',route:'/'},
                {icon:'folder',text:'Customers List',route:'/'},
                {icon:'person',text:'Agents List',route:'/'},

            ]
        }
    }
    
}
</script>
