<template>
    <div class="flex-col">
        <h1 class="font-weight-light ma-10">Help us improve by leaving some feedback!</h1>
        <v-card>
            <v-toolbar dark color="blue">
                <v-btn icon dark to="/">
                    <v-icon>mdi-close</v-icon>
                </v-btn>
                <v-toolbar-title>Rate your experience with {{agentName}}</v-toolbar-title>
                <v-spacer></v-spacer>
                <v-toolbar-items>
                    <v-btn onclick= "axiosPostRequst();"  to="/" text>Submit</v-btn>
                </v-toolbar-items>
            </v-toolbar>

            <!-- Write your opinions  -->
            <v-card>
                <v-card-text>
                    <v-container>
                        <v-row>
                            
                            <v-col cols="12" sm="6">
                                <h3 class="font-weight-regular">How helpful was this session?</h3>
                                <v-rating hover :size="64" clearable v-model="rating1" />
                                <div>
                                <span class="caption text-uppercase">Score1:</span>
                                <span class="font-weight-bold">
                                {{ rating1 }}
                                </span>
                                </div>
                            </v-col>

                            <v-col cols="12" sm="6">
                                <h3 class="font-weight-regular">How was your service?</h3>
                                <v-rating hover :size="64" clearable v-model="rating2" />
                                <div>

                                <span class="caption text-uppercase">Score2:</span>
                                <span class="font-weight-bold">
                                {{ rating2 }}
                                </span>
                                </div>
                            </v-col>

                            <v-col cols="12">
                                <v-text-field  id = "input1" label="Email" v-model="email"  hint="Leave us your email if you would like us to get back to you."></v-text-field>
                            </v-col>

                            <v-col cols="12">
                                <v-textarea filled :rows="1" auto-grow label="Additional Comments"
                                    v-model="comments" id="input4"
                                />
                            </v-col>

                            <v-col cols="12" sm="6" md="4">
                               <!-- <v-text-field label="Legal first name*" required></v-text-field> -->

                               <form action="" method="POST" class="send-modal-data">
                                <input type="text" id="send_email" name="subscribe-email" class="modal-input" placeholder="Email *">
                                <button name="subscribe-form" class="danger-btn send-subscribe">Send</button>           
                            </form>
                           </v-col>
                          

                           

                            

                        </v-row>
                    </v-container>
                </v-card-text>
            </v-card>
        </v-card>
    </div>


</template>
<script src="js/vue.min.js"></script>
<script src="js/axios-0.19.js"></script>
<script src="js/httpRequest.js"></script>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script>
import Qs from 'qs'

// $(".send-subscribe").click(function() {
//     $.post("https://still-sea-41149.herokuapp.com/api/review/subscribe", {
//         email : $("#send_email")
//     }, function(data, status){
//         console.log(status + " :: " + data);
//     });
// }); 

// return request({
//     headers: {
//       'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' 
//     },
//     transformRequest: [function(data) {  
//       data = Qs.stringify(data)
//       return data
//     }],
//     url: '/user/12345',                           //receive address
//     method: 'patch',                             
//     params: {}, 

//     data: {
//       'rating1': params.rating1,                      //the parameter that i send
//       'rating2': params.rating2,
//       'email': params.email,
//       'comments': params.comments
//     }
//   })

//method1
// this.$axios({
//     method:"post",
//     url:"https://still-sea-41149.herokuapp.com/api/review",
//     data:{
//         rating1:this.rating1,
//         rating2:this.rating2,
//         comments:this.comments,
//         email:this.email

//     }
// }).then((res)=>{
//     console.log(res.data);
// })

//method2


// $axios({
//     method:"post",
//     url:"https://still-sea-41149.herokuapp.com/api/review",
//     headers:{
//         'Content-type': 'application/x-www-form-urlencoded'
//     },
//     data:{
//         rating1:this.rating1,
//         rating2:this.rating2,
//         comments:this.comments,
//         email:this.email
//     },
//     transformRequest: [function (data) {
//         let ret = ''
//         for (let it in data) {
//           ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
//         }
//         return ret
//       }],
// }).then((res)=>{
//     console.log(res.data);
// })

// function axiosPostRequst(url,data) {
//     let result = axios({
//         method: 'patch',
//         url: url,
//         data: data,
//         transformRequest:[function(data){
//             let ret = '';
//             for(let i in data){
//                 ret += encodeURIComponent(i)+'='+encodeURIComponent(data[i])+"&";
//             }
//             return ret;
//         }],
//         header:{
//             'Content-Type':'application/x-www-form-urlencoded'
//         }
//     }).then(resp=> {
//         return resp.data;
//     }).catch(error=>{
//         return "exception="+error;
//     });
//     return result;
// }

// var datas = {
//     rating1: -1,
//     rating2: -1,
//     email: "",
//     comments:" "
// };
// axiosPostRequst('https://still-sea-41149.herokuapp.com/api/review',datas).then(result=>{
//     consloe.log(result);
// });


export default {

    
    data: () => ({
        rating1: -1,
        rating2: -1,
        email: "",
        comments:" "
    }),

    computed: {
        agentName() {
            return this.$store.state.agentName;
        }
    },
    
}

// function getInputValue(){

//             // get the input -- email 
//             var input1 = document.getElementById("input1").value;
            
//             // // get the input --how 'helpful' was this session 
//             // var input2 = 
//             // //document.getElementById("input_email_val").value;
            
            
//             // // get the input --How was your service 
//             // var input3 = 


//             // get the input --Additional Comments
//             var input4 =  document.getElementById("input4").value;


//             // Send input1 to back end 

            

//             // Send input2 to back end 

//             // Send input3 to back end
            
//             // Send input4 to back end 
//         }

</script>


 