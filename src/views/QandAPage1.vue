<template>
    <div class = " Question and Solution  ">
        <h1 class = "subheading grey--text" > Question and Solution </h1>

         <v-container class="flex-col">
            <v-select v-model="selected" v style="width: 400px"
                :items="categories"
                outlined
                :menu-props="{ offsetY:true, openOnHover:true, openOnClick:false }"
                label="Searching For your questions "
            ></v-select>

        </v-container>

        <v-container class = "my-5">
            <v-layout row class = "mb-3" >
                <v-btn small flat color = " white " @click="sortBy('ProductType')">
                 <!-- <v-btn outline block class = "caption grey--text">More Details</v-btn> -->
                    <v-icon left small >list </v-icon>
                    <span class = " caption text-lowercase "> By Product Type </span>
                </v-btn>

                <br>
                <br>
        
                <v-btn small flat color = " white " @click="sortBy('IssueType')">
                    <v-icon left small >list </v-icon>
                    <span class = " caption text-lowercase "> By Issue Type </span>
                </v-btn>
            </v-layout>

            <v-card flat class = "pa-3" v-for=" Questions in Questions" :key="Questions.Questions">
                <!-- <v-layout row wrap : class = "pa-3 Questions ${Questions.ProductType}"> -->
                <v-layout row>
                    <v-flex xs12 md6>
                        <div class = "caption grey--text">Questions</div>
                        <div>{{Questions.Questions}} </div>
                    </v-flex>

                    <v-flex xs6 sm4 md2>
                        <div class = "caption grey--text">Product Type</div>
                        <div>{{Questions.ProductType}}</div>
                    </v-flex>

                    <v-flex xs6 sm4 md2>
                        <div class = "caption grey--text">Issue Type</div>
                        <div> {{Questions.IssueType}} </div>
                    </v-flex>

                     <v-flex xs6 sm4 md2>
                     <v-btn outline block class = "caption grey--text">More Details</v-btn>
                     </v-flex>

                </v-layout>
                <v-divider></v-divider>

            </v-card>
            
        </v-container>


        <v-container>
             <div style="flex-direction: row">
                <v-btn to="/wait" @click="getAgentId" x-large depressed class="ma-5 green white--text">
                    <v-icon left>message</v-icon> Get Chat Support
                </v-btn>
                <v-btn @click="alert" x-large depressed class="ma-5 green white--text">
                    <v-icon left>phone</v-icon> Get Call Support
                </v-btn>
            </div>
        </v-container>
    </div>
</template>

<script>
export default {
    data(){
        return{
             Questions: [
                 {Questions: 'If I forgot my Acorn ID, how can I sove this problem? ', ProductType:' Acorn Phnoe', IssueType:'Password',MoreDetails:'More Detail'},
                 {Questions: 'If My Acorn ID can not access any of my devices', ProductType:'Acorn Laptop', IssueType:' Account Security',MoreDetails:'More Detail'},
                 {Questions: 'If My Acornpad lost , can I find it by some technical methods ', ProductType:' Acornpad ', IssueType:'Application',MoreDetails:'More Detail'},
                 {Questions: 'The touch screen of Acorn Phone is not sensitive', ProductType:'Acorn Phone', IssueType:'HardWare Issue',MoreDetails:'More Detail'},
                //  {Questions: '', ProductType:'', IssueType:'', MoreDetails:''},
                //  {Questions: '', ProductType:'', IssueType:'', MoreDetails:''},
                //  {Questions: '', ProductType:'', IssueType:'', MoreDetails:''},
                 {Questions: 'If I forgot my Acorn ID, how can I sove this problem? ', ProductType:' Acorn Phnoe', IssueType:'Password',MoreDetails:'More Detail'},
                 {Questions: 'If My Acorn ID can not access any of my devices', ProductType:'Acorn Laptop', IssueType:' Account Security',MoreDetails:'More Detail'},
                 {Questions: 'If My Acornpad lost , can I find it by some technical methods ', ProductType:' Acornpad ', IssueType:'Application',MoreDetails:'More Detail'},
                 {Questions: 'The touch screen of Acorn Phone is not sensitive', ProductType:'Acorn Phone', IssueType:'HardWare Issue',MoreDetails:'More Detail'},
                //  {Questions: '', ProductType:'', IssueType:'', MoreDetails:''},
                //  {Questions: '', ProductType:'', IssueType:'', MoreDetails:''},
                //  {Questions: '', ProductType:'', IssueType:'', MoreDetails:''},

             ]
        }
       
    },
    methods:{
        sortBy(prop){
            this.Questions.sort((a,b) => a[prop] <b[prop] ? -1:1)
        }
    }
    
}
</script>



<style>
.Questions.Acorn Phnoe{
    border-left: 4-px solid #3cd1c2;
}

.Questions.Acorn Laptop{
    border-left: 4-px solid orange ;
}

.Questions.Acorn Phnoe{
    border-left: 4-px solid tomato;
}
</style>