<template>
    <div style="display: flex; flex-direction: column; justify-content: center; min-height: 100vh">
        
        <v-container class="about">
            <h1 class="display-3 acorn-title pb-10">Welcome to Acorn</h1>
            <v-select 
                v-model="selected"
                style="width: 400px"
                :items="categories"
                outlined
                :menu-props="{ offsetY:true }"
                label="Choose a support category..."
            ></v-select>
            <div style="flex-direction: row">
                <v-btn :to="{name:'chat', params:{selectedIndex}}" @click.native="access" x-large depressed class="ma-5 green white--text">
                    <v-icon left>message</v-icon> Get Chat Support
                </v-btn>
                <v-btn :to="{name:'call', params:{selectedIndex}}" @click.native="access" x-large depressed class="ma-5 green white--text">
                    <v-icon left>phone</v-icon> Get Call Support
                </v-btn>
            </div>
        </v-container>
    </div>
</template>

<script>
    export default {
        name: "About",
        data: function(){
            return{
            selectedIndex:'',
            selected:'',
            categories: [
                'Acorn Products','Acorn Services','Acorn Applications'
            ],
             
        }
        },
        methods:{
            access: function(){
                console.log("clicked");
                this.selectedIndex = this.categories.indexOf(this.selected);
                console.log("Selected Category is:",this.selectedIndex);
            },
            // selected(){
            //     console.log(selected);
            
            // }
            
        }
    }
</script>

<style scoped>
.about {
    display: flex;
    flex-direction: column;
    align-items: center;
}
</style>
