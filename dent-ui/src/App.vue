<template>
  <v-app
    id="main"
    :style="{ background: $vuetify.theme.themes[theme].background }"
  >
    <v-app-bar app color="primary" dark>
      <span>{{ username }}</span>

      <a @click="exit" v-if="username"> <v-icon> mdi-close</v-icon> </a>

      <v-flex v-if="username" style="margin:10px;" xs1>
        <router-link style="margin:10px;" to="/">
          <span style="color: white !important;" class="mr-2">HOME</span>
        </router-link>
        <router-link
          v-show="checkUserAccess('Job')"
          style="margin:10px;"
          to="/h/job"
        >
          <span style="color: white !important;" class="mr-2">JOB</span>
        </router-link>
        <router-link
          v-show="checkUserAccess('Data')"
          style="margin:10px;"
          to="/h/data"
        >
          <span style="color: white !important;" class="mr-2">DATA</span>
        </router-link>
        <router-link
          v-show="checkUserAccess('Profile')"
          style="margin:10px;"
          to="/h/profile"
        >
          <span style="color: white !important;" class="mr-2">PROFILE</span>
        </router-link>
        <router-link
          v-show="checkUserAccess('Linkedin')"
          style="margin:10px;"
          to="/h/linkedin"
        >
          <span style="color: white !important;" class="mr-2">LINKEDIN</span>
        </router-link>
        <router-link
          v-show="checkUserAccess('Proxy')"
          style="margin:10px;"
          to="/h/proxy"
        >
          <span style="color: white !important;" class="mr-2">PROXY</span>
        </router-link>
      </v-flex>

      <v-spacer></v-spacer>
      <v-switch
        style="margin:5px;"
        color="grey darken-4"
        v-model="$vuetify.theme.dark"
        hide-details
      ></v-switch>
      <a
        style="margin:5px;"
        href="https://github.com/gurkanakdeniz/dent-ui"
        target="_blank"
        text
      >
        <v-icon> mdi-open-in-new </v-icon>
      </a>
    </v-app-bar>
    <v-main>
      <transition name="fade" mode="out-in">
        <router-view class="view"></router-view>
      </transition>
    </v-main>
    <notifications group="info" position="left center" :max="3" :width="400" />
    <!-- <notifications group="info" position="bottom center">
      <template slot="body" slot-scope="props">
        <template>
          <v-card>
            <v-toolbar color="primary" dark> {{ props.item.title }}</v-toolbar>
            <v-card-text>
              <div class="text-h2 pa-12">Hello world!</div>
            </v-card-text>
            <v-card-actions class="justify-end">
              <v-btn text @click="dialog.value = false">Close</v-btn>
            </v-card-actions>
          </v-card>
        </template>
      </template>
    </notifications> -->
  </v-app>
</template>

<script>
import cookieUtil from "./common/cookie.util.js";
import routeUtil from "./common/route.util.js";

import { mapActions, mapGetters } from "vuex";

export default {
  name: "App",
  computed: {
    ...mapGetters("user", ["username"]),
    theme() {
      return this.$vuetify.theme.dark ? "dark" : "light";
    }
  },
  mounted() {
    let user = cookieUtil.user();
    try {
      if (user) {
        if (user.username && user.authRole) {
          this.login({
            username: user.username,
            role: user.authRole
          });
        } else {
          this.routeLoginCheck();
        }
      } else {
        this.routeLoginCheck();
      }
    } catch (e) {
      // ignore
    }
  },
  data: () => ({
    //
  }),
  watch: {
    username: function(val) {
      if (val) {
        this.routeHome();
      } else {
        this.routeLogin();
      }
    }
  },
  methods: {
    ...mapActions("user", ["login", "user"]),
    exit() {
      cookieUtil.exit();
      this.login({
        username: null,
        role: null
      });
    },
    routeLoginCheck() {
      if (this.username) {
        this.login({
          username: null,
          role: null
        });
      } else {
        this.routeLogin();
      }
    },
    routeLogin() {
      if ("Login" !== this.$route.name) {
        this.$router.push({
          name: "Login"
        });
      }
    },
    routeHome() {
      if ("Login" === this.$route.name) {
        this.$router.push({
          name: "Home"
        });
      }
    },
    checkUserAccess(path) {
      return routeUtil.checkUserAccess(path);
    }
  }
};
</script>
