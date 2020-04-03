<template>
    <div class = " Question and Solution  ">
        <h1 class = "subheading grey--text" > Customer list </h1>

         <v-container class="flex-col">
            <v-select v-model="selected" v style="width: 400px"
                :items="categories"
                outlined
                :menu-props="{ offsetY:true, openOnHover:true, openOnClick:false }"
                label="Searching Customer "
            ></v-select>
        </v-container>

        <v-container class = "my-5">
            <v-layout row class = "mb-3" >
                <v-btn small flat color = " white " @click="sortBy('ProductType')">
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

            <v-card flat class = "pa-3" v-for=" key in info" :key="key.info">
                <v-layout row>
                    <v-flex xs12 md4>
                        <div class = "caption grey--text">Name</div>
                        <div>{{key.Questions}} </div>
                    </v-flex>

                    <v-flex xs6 sm4 md2>
                        <div class = "caption grey--text">Service Product Type</div>
                        <div>{{key.ProductType}}</div>
                    </v-flex>

                    <v-flex xs6 sm4 md2>
                        <div class = "caption grey--text">Service Issue Type</div>
                        <div> {{key.IssueType}} </div>
                    </v-flex>

                    <v-flex xs6 sm4 md2>
                        <div class = "caption grey--text">Service Time</div>
                        <div> {{key.Time}} </div>
                    </v-flex>

                    <v-flex xs6 sm4 md2>
                        <div class = "caption grey--text">Feedback</div>
                        <div> {{key.MoreDetails}} </div>
                    </v-flex>
                    

                </v-layout>
                <v-divider></v-divider>

            </v-card>
            
        </v-container>

    </div>
</template>

<script>
export default {
    data(){
        return{
             info: [
                 {Questions: 'Alice ', ProductType:' Acorn Phnoe', IssueType:'Password',MoreDetails:'Feedback',Time:'21/03/2020 4:00 pm'},
                 {Questions: 'Bob', ProductType:'Acorn Laptop', IssueType:' Account Security',MoreDetails:'Feedback',Time:'21/03/2020 4:00 pm'},
                 {Questions: 'Agent3 ', ProductType:' Acornpad ', IssueType:'Application',MoreDetails:'Feedback',Time:'21/03/2020 4:00 pm'},
                 {Questions: 'Agent4', ProductType:'Acorn Phone', IssueType:'HardWare Issue',MoreDetails:'Feedback',Time:'21/03/2020 4:00 pm'},
                 {Questions: 'Agent5', ProductType:' Acorn Phnoe', IssueType:'Password',MoreDetails:'Feedback',Time:'21/03/2020 4:00 pm'},
                 {Questions: 'Agent6', ProductType:'Acorn Laptop', IssueType:' Account Security',MoreDetails:'Feedback',Time:'21/03/2020 4:00 pm'},
                 {Questions: 'Agent7', ProductType:' Acornpad ', IssueType:'Application',MoreDetails:'Feedback',Time:'21/03/2020 4:00 pm'},
                 {Questions: 'Agent9', ProductType:'Acorn Phone', IssueType:'HardWare Issue',MoreDetails:'Feedback',Time:'21/03/2020 4:00 pm'},
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