<template>
    <div id="app">
  <v-app id="inspire">
    <form>
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
                              <v-text-field 
                              id="FirstName" 
                              v-model="name1"
                              :error-messages="name1Errors"
                              :counter="10"
                              label="FirstName"
                              required
                              @input="$v.name1.$touch()"
                              @blur="$v.name1.$touch()"></v-text-field>
                              </v-col>
                              
                              <v-col cols="12" sm="6" md="4">
                              <v-text-field 
                              v-model="name2"
                              :error-messages="name2Errors"
                              :counter="10"
                              required
                              @input="$v.name2.$touch()"
                              @blur="$v.name2.$touch()"
                              label="Legal middle name" 
                              hint="example of helper text only on focus" 
                              id="MiddleName">
                              </v-text-field>
                              </v-col>


                              <v-col cols="12" sm="6" md="4">
                              <v-text-field id="LastName"
                                  label="Legal last name*"
                                  hint="example of persistent helper text"
                                  persistent-hint
                                  v-model="name3"
                                  :error-messages="name3Errors"
                                  :counter="10"
                                  required
                                  @input="$v.name3.$touch()"
                                  @blur="$v.name3.$touch()"
                              ></v-text-field>
                              
                              </v-col>
                              <v-col cols="12">
                              <v-text-field 
                              id="Email" 
                              label="Email*" 
                              v-model="email"
                              :error-messages="emailErrors"
                              @input="$v.email.$touch()"
                              @blur="$v.email.$touch()"
                              required>
                              </v-text-field>
                              </v-col>
                              
                              
                              <v-col cols="12">
                              <v-text-field 
                              id="comments" 
                              label="comments"
                              v-model="comments"
                              :error-messages="commentsErrors"
                              :counter="10"
                              @input="$v.comments.$touch()"
                              @blur="$v.comments.$touch()" 
                              required>
                              </v-text-field>
                              </v-col>

                              <v-col cols="12" sm="6">
                              <v-select id="SelectVoice"
                                  v-model="selectVoice"
                                  :error-messages="selectVoiceErrors"
                                  @change="$v.selectVoice.$touch()"
                                  @blur="$v.selectVoice.$touch()"
                                  :items="['bad', 'average', 'good', 'very good']"
                                  label=" Voice"
                                  required
                              ></v-select>
                              </v-col>


                              <v-col cols="12" sm="6">
                              <v-select id="SelectAttitude"
                                  :items="['bad', 'average', 'good', 'very good']"
                                  label=" attitude"
                                  v-model="selectAttitude"
                                  :error-messages="selectAttitudeErrors"
                                  required
                                  @change="$v.selectAttitude.$touch()"
                                  @blur="$v.selectAttitude.$touch()"
                              ></v-select>
                              </v-col>

                              <v-col>
                                <v-btn class="mr-4" @click="submit">submit2</v-btn>
                                <v-btn @click="clear">clear</v-btn>
                              </v-col>

                          </v-row>
                          </v-container>
                          </v-card-text>
                      </v-card>
          </v-card>
      </v-row>
    </form>
  </v-app>
</div>
</template>


<script>

import { required, maxLength,email } from 'vuelidate/lib/validators'
import Vue from 'vue'
import Vuelidate from 'vuelidate'
import { validationMixin } from 'vuelidate'
Vue.use(Vuelidate)


export default {
  mixins: [validationMixin],

  validations: {
    comments: { required, maxLength: maxLength(10) },
    name1: { required, maxLength: maxLength(10) },
    name2: { required, maxLength: maxLength(10) },
    name3: { required, maxLength: maxLength(10) },
    email: { required, email },
    selectVoice: { required },
    selectAttitude: { required },
    checkbox: {
      checked(val) {
        return val;
      } } },



  data: () => ({
    name1: '',
    name2: '',
    name3: '',
    comments: '',
    email: '',
    selectAttitude: null,
    selectVoice: null,
    checkbox: false }),


  computed: {
    checkboxErrors() {
      const errors = [];
      if (!this.$v.checkbox.$dirty) return errors;
      !this.$v.checkbox.checked && errors.push('You must agree to continue!');
      return errors;
    },
    selectAttitudeErrors() {
      const errors = [];
      if (!this.$v.selectAttitude.$dirty) return errors;
      !this.$v.selectAttitude.required && errors.push('Item is required');
      return errors;
    },
    selectVoiceErrors() {
      const errors = [];
      if (!this.$v.selectVoice.$dirty) return errors;
      !this.$v.selectVoice.required && errors.push('Item is required');
      return errors;
    },
    commentsErrors() {
      const errors = [];
      if (!this.$v.comments.$dirty) return errors;
      !this.$v.comments.maxLength && errors.push('Name must be at most 10 characters long');
      !this.$v.comments.required && errors.push('Name is required.');
      return errors;
    },
    name1Errors() {
      const errors = [];
      if (!this.$v.name1.$dirty) return errors;
      !this.$v.name1.maxLength && errors.push('Name must be at most 10 characters long');
      !this.$v.name1.required && errors.push('Name is required.');
      return errors;
    },
    name2Errors() {
      const errors = [];
      if (!this.$v.name2.$dirty) return errors;
      !this.$v.name2.maxLength && errors.push('Name must be at most 10 characters long');
      !this.$v.name2.required && errors.push('Name is required.');
      return errors;
    },
    name3Errors() {
      const errors = [];
      if (!this.$v.name3.$dirty) return errors;
      !this.$v.name3.maxLength && errors.push('Name must be at most 10 characters long');
      !this.$v.name3.required && errors.push('Name is required.');
      return errors;
    },
    emailErrors() {
      const errors = [];
      if (!this.$v.email.$dirty) return errors;
      !this.$v.email.email && errors.push('Must be valid e-mail');
      !this.$v.email.required && errors.push('E-mail is required');
      return errors;
    } },


  methods: {
    submit() {
      this.$v.$touch();
    },
    clear() {
      this.$v.$reset();
      this.comments = '';
      this.name1 = '';
      this.name2 = '';
      this.name3 = '';
      this.email = '';
      this.selectAttitude = null;
      this.selectVoice = null;
      this.checkbox = false;
    } } }
</script>