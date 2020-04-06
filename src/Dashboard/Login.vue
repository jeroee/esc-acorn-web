<template>
  <div id="app">
    <v-app id="inspire">
      <v-content>
        <v-container fluid fill-height>
          <v-layout align-center justify-center>
            <v-flex xs12 sm8 md4>
              <v-card class="elevation-20">
                <v-toolbar color="green" dark flat>
                  <v-spacer></v-spacer>
                  <v-toolbar-title align="center" prepend-icon="acorn">
                    ACORN
                    <br />Admin Dashboard
                  </v-toolbar-title>
                  <v-spacer></v-spacer>
                </v-toolbar>
                <v-card-text>
                  <v-form>
                    <v-text-field
                      id="login"
                      label="Login"
                      name="login"
                      prepend-icon="person"
                      type="text"
                      v-model="input.username"
                    ></v-text-field>

                    <v-text-field
                      id="password"
                      label="Password"
                      name="password"
                      prepend-icon="lock"
                      type="password"
                      v-model="input.password"
                    ></v-text-field>
                  </v-form>
                  <p style="color: red" align="center">{{invalid}}</p>
                  <P style="color: green" align="center">{{valid}}</p>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn to="/layout" @click="enter" style="color: white" color="green" id="login">Login</v-btn>
                </v-card-actions>
              </v-card>
            </v-flex>
          </v-layout>
        </v-container>
      </v-content>
    </v-app>
  </div>
</template>

<script>
export default {
  name: "LoginPage",
  data: () => ({
    input: {
      username: "",
      password: ""
    },
    invalid: "",
    valid:""
  }),
  mounted(){
      const self = this;
      window.addEventListener('keyup',function(event){
          if(event.keyCode === 13){
              console.log("enter pressed");
              self.enter();
          }
      })
  },
  methods: {
    enter: function() {
      if (this.input.username != "" || this.input.password != "") {
        if (
          this.input.username == "admin1" &&
          this.input.password == "password1"
        ) {
          this.valid = "Successful login";
          var self0 = this;
          setTimeout(function() {
            self0.$router.push("Dashboard")
          },2000);
        } else {
          this.invalid = "incorrect username/password input";
          var self1 = this;
          setTimeout(function() {
            self1.invalid = "";
          }, 2000);
        }
      } else {
        this.invalid = "please enter valid username and password";
        var self2 = this;
        setTimeout(function() {
          self2.invalid = "";
        }, 2000);
      }
    }
  }
};
</script>