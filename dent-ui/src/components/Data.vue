<template>
  <v-container grid-list-xl fill-height fluid>
    <v-layout row wrap>
      <v-flex md12>
        <v-card>
          <v-card-title primary-title class="justify-center">
            RAW PROFILES
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

            <template v-slot:item.profileId="{ item }">
              <a @click="showUser(item)">
                {{ item.profileId }}
              </a>
            </template>

            <template v-slot:item.processingDate="{ item }">
              {{ formatDate(item.processingDate) }}
            </template>

            <template v-slot:item.crawlingDate="{ item }">
              {{ formatDate(item.crawlingDate) }}
            </template>

            <template v-slot:item.actions="{ item }">
              <div class="text-truncate">
                <v-icon
                  small
                  class="mr-2"
                  @click="showEditDialog(item)"
                  color="primary"
                >
                  mdi-account
                </v-icon>

                <span v-show="writeAccess">
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
                </span>
              </div>
            </template>
          </v-data-table>
          <!-- this dialog is used for both create and update -->
          <v-dialog v-model="dialog" max-width="1000px">
            <v-card>
              <v-card-title>
                <span>{{ editedItem.profileId }}</span>
              </v-card-title>
              <v-card-text>
                <v-row>
                  <v-col cols="12" sm="12">
                    <pre>{{
                      JSON.stringify(editedItem.crawlingData, null, "\t")
                    }}</pre>
                  </v-col>
                </v-row>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="showEditDialog()">
                  Cancel
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
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
  name: "Data",
  components: {},
  data() {
    return {
      // extraction bileşeni tarafından process edilmesini trigger edebilmeli ya da yeniden crawl edilmesini
      // trigger edebilmelidir.

      headers: [
        { text: "Name", value: "name" },
        { text: "Profile", value: "profileId" },
        { text: "Crawling Date", value: "crawlingDate" },
        { text: "Processing Date", value: "processingDate" },
        { text: "Processing Status", value: "processingStatus" },
        { text: "Action", value: "actions", sortable: false }
      ],
      items: [],
      dialog: false,
      loading: true,
      editedItem: {},
      writeAccess: false
    };
  },
  mounted() {
    this.writeAccess = routeUtil.checkUserComponentAccess("Data").includes("w");

    this.loadItems();
  },
  methods: {
    showEditDialog(item) {
      this.editedItem = item || {};
      this.dialog = !this.dialog;
    },
    loadItems() {
      this.loading = true;
      call
        .api({
          endpoint: "/view/data",
          method: "GET"
        })
        .then(response => {
          this.items = response.crawlings;
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
          endpoint: "/management/data/crawling/" + item.id,
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
          endpoint: "/management/data/extraction/" + item.id,
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
          endpoint: "/management/data/delete/" + item.id,
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
    showUser(item) {
      var win = window.open(
        "https://www.linkedin.com/in/" + item.profileId,
        "_blank"
      );
      win.focus();
    },
    getName(item) {
      try {
        if (item) {
          if (item.profileModel) {
            if (item.profileModel.info) {
              if (item.profileModel.info["NAME"]) {
                return item.profileModel.info["NAME"][0];
              }
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
