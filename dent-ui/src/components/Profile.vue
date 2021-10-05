<template>
  <v-container grid-list-xl fill-height fluid>
    <v-layout row wrap>
      <v-flex md12>
        <v-card>
          <v-card-title primary-title class="justify-center">
            PROFILES
          </v-card-title>
          <v-data-table
            :headers="headers"
            :items="items"
            mobile-breakpoint="800"
            class="elevation-0"
            :loading="loading"
            loading-text="Loading... Please wait"
          >
            <template v-slot:item.name="{ item }">
              <a @click="showUser(item)">
                {{ getName(item) }}
              </a>
            </template>

            <template v-slot:item.username="{ item }">
              <a @click="showUser(item)">
                {{ item.username }}
              </a>
            </template>

            <template v-slot:item.crawlingDate="{ item }">
              {{ formatDate(item.crawlingDate) }}
            </template>

            <template v-slot:item.infoDate="{ item }">
              {{ formatDate(item.infoDate) }}
            </template>

            <template v-slot:item.actions="{ item }">
              <div v-show="writeAccess" class="text-truncate">
                <v-icon
                  small
                  class="mr-2"
                  @click="extraction(item)"
                  color="primary"
                >
                  mdi-refresh
                </v-icon>
                <v-icon
                  small
                  class="mr-2"
                  @click="crawling(item)"
                  color="primary"
                >
                  mdi-play
                </v-icon>
                <v-icon small @click="deleteItem(item)" color="pink">
                  mdi-delete
                </v-icon>
              </div>
            </template>
          </v-data-table>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import call from "../api/call.js";
import dateUtil from "../common/date.util.js";
import routeUtil from "../common/route.util.js";

export default {
  name: "Profile",
  components: {},
  data() {
    return {
      headers: [
        { text: "Name", value: "name" },
        { text: "Profile", value: "username" },
        { text: "Crawling Date", value: "crawlingDate" },
        { text: "Info Date", value: "infoDate" },
        { text: "Action", value: "actions", sortable: false }
      ],
      items: [],
      loading: true,
      userItem: {},
      writeAccess: false
    };
  },
  mounted() {
    this.writeAccess = routeUtil
      .checkUserComponentAccess("Profile")
      .includes("w");
    this.loadItems();
  },
  methods: {
    showUser(item) {
      this.userItem = item || {};

      this.$router.push({
        name: "ProfileUser",
        params: { profileUser: item }
      });
    },
    loadItems() {
      this.loading = true;

      call
        .api({
          endpoint: "/api/profile",
          method: "GET"
        })
        .then(response => {
          this.items = response.users;
          this.loading = false;
        })
        .catch(error => {
          console.log(JSON.stringify(error));
          this.$notify({
            group: "info",
            title: "Error",
            text: JSON.stringify(error),
            type: "error"
          });

          this.loading = false;
        });
    },
    crawling(item) {
      call
        .api({
          endpoint: "/management/profile/crawling/" + item.username,
          method: "GET"
        })
        .then(response => {
          console.log(JSON.stringify(response));
          this.loadItems();
          this.$notify({
            group: "info",
            title: "Success",
            text: "Transaction Successful",
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
    },
    extraction(item) {
      call
        .api({
          endpoint: "/management/profile/extraction/" + item.username,
          method: "GET"
        })
        .then(response => {
          console.log(JSON.stringify(response));
          this.loadItems();
          this.$notify({
            group: "info",
            title: "Success",
            text: "Transaction Successful",
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
    },
    deleteItem(item) {
      call
        .api({
          endpoint: "/management/profile/delete/" + item.username,
          method: "GET"
        })
        .then(response => {
          console.log(JSON.stringify(response));
          this.loadItems();
          this.$notify({
            group: "info",
            title: "Success",
            text: "Transaction Successful",
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
    },
    formatDate(value) {
      return dateUtil.format(value);
    },
    getName(item) {
      try {
        if (item) {
          if (item.info) {
            if (item.info["NAME"]) {
              return item.info["NAME"][0];
            }
          }
        }
      } catch (e) {
        console.log(e);
      }
      return "";
    }
  }
};
</script>

<style lang="css" scoped></style>
