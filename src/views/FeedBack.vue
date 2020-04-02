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
                    <v-btn to="/" text>Submit</v-btn>
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
                            </v-col>
                            <v-col cols="12" sm="6">
                                <h3 class="font-weight-regular">How was your service?</h3>
                                <v-rating hover :size="64" clearable v-model="rating2" />
                            </v-col>
<!--                            <v-col cols="12" sm="6" md="4">-->
<!--                                <v-text-field label="Legal first name*" required></v-text-field>-->
<!--                            </v-col>-->
<!--                            <v-col cols="12" sm="6" md="4">-->
<!--                                <v-text-field label="Legal middle name" hint="example of helper text only on focus"></v-text-field>-->
<!--                            </v-col>-->
<!--                            <v-col cols="12" sm="6" md="4">-->
<!--                                <v-text-field-->
<!--                                        label="Legal last name*"-->
<!--                                        persistent-hint-->
<!--                                        required-->
<!--                                ></v-text-field>-->
<!--                            </v-col>-->
                            <v-col cols="12">
                                <v-text-field label="Email" hint="Leave us your email if you would like us to get back to you."></v-text-field>
                            </v-col>
                            <v-col cols="12">
                                <v-textarea
                                    filled
                                    :rows="1"
                                    auto-grow
                                    label="Additional Comments"
                                    v-model="comments"
                                />
                            </v-col>
<!--                            <v-col cols="12" sm="6">-->
<!--                                <v-select-->
<!--                                        :items="['bad', 'average', 'good', 'very good']"-->
<!--                                        label="Attentiveness"-->
<!--                                        required-->
<!--                                ></v-select>-->
<!--                            </v-col>-->
<!--                            <v-col cols="12" sm="6">-->
<!--                                <v-select-->
<!--                                        :items="['bad', 'average', 'good', 'very good']"-->
<!--                                        label="Attitude"-->
<!--                                        required-->
<!--                                ></v-select>-->
<!--                            </v-col>-->

                        </v-row>
                    </v-container>
                </v-card-text>
            </v-card>
        </v-card>
    </div>
</template>


<script>
export default {
    data: () => ({
        rating1: -1,
        rating2: -1,
        comments:""
    }),
    computed: {
        agentName() {
            return this.$store.state.agentName;
        }
    },
}
</script>

<style scoped>
    .flex-col {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100vh;
    }
</style>
