<template>
  <v-container grid-list-xl fill-height fluid>
    <v-layout row wrap>
      <v-flex md4 offset-md4>
        <v-card
          dark
          color="grey darken-3"
          elevation-24
          style="padding: 1em; border: 1em; border-radius: 2em;"
        >
          <v-card-title primary-title class="justify-center">
            <div>
              <h4 class="display-2 font-weight-bold"></h4>
              <div>
                Sign in to Dent
              </div>
            </div>
          </v-card-title>

          <v-card-text>
            <v-form>
              <v-text-field
                clearable
                v-model="loginUsername"
                label="Username"
                prepend-icon="account_circle"
                type="text"
                color="white"
                counter="20"
                @keydown.enter="signInClicked"
              >
              </v-text-field>
              <v-text-field
                clearable
                v-model="password"
                label="Password"
                prepend-icon="lock"
                type="password"
                color="white"
                @keydown.enter="signInClicked"
              >
              </v-text-field>
            </v-form>
          </v-card-text>
          <v-card-actions class="justify-center">
            <v-btn @click="signInClicked" color="grey darken-2" large
              >Sign in</v-btn
            >
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapActions } from "vuex";
import call from "../api/call.js";

export default {
  name: "Login",
  components: {},
  data() {
    return {
      loginUsername: "",
      password: ""
    };
  },
  computed: {},
  methods: {
    ...mapActions("user", ["login", "user"]),
    signInClicked() {
      call
        .api({
          endpoint: "/login",
          body: { username: this.loginUsername, password: this.password }
        })
        .then(response => {
          this.login({
            username: response.username,
            role: response.role
          });

          console.log(JSON.stringify(response));
          this.$notify({
            group: "info",
            title: "Success",
            text: " Successful",
            type: "success"
          });
        })
        .catch(error => {
          console.log(JSON.stringify(error));
          this.$notify({
            group: "info",
            title: "Error",
            text: JSON.stringify(error),
            type: "error"
          });
        });
    }
  }
};
</script>

<style></style>
