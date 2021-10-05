<template>
  <v-container grid-list-xl fill-height fluid>
    <v-layout row wrap>
      <v-flex md12>
        <v-card>
          <v-card-title primary-title class="justify-center">
            CRAWLING JOBS
          </v-card-title>
          <v-data-table
            :headers="headers"
            :items="items"
            mobile-breakpoint="800"
            class="elevation-0"
            :loading="loading"
            loading-text="Loading... Please wait"
          >
            <template v-slot:item.createDate="{ item }">
              {{ formatDate(item.createDate) }}
            </template>

            <template v-slot:item.startDate="{ item }">
              {{ formatDate(item.startDate) }}
            </template>

            <template v-slot:item.endDate="{ item }">
              {{ formatDate(item.endDate) }}
            </template>

            <template v-slot:item.actions="{ item }">
              <div v-show="writeAccess" class="text-truncate">
                <v-icon
                  small
                  class="mr-2"
                  @click="changeStatus(item)"
                  color="primary"
                >
                  mdi-flag
                </v-icon>
                <v-icon small @click="deleteItem(item)" color="pink">
                  mdi-delete
                </v-icon>
              </div>
            </template>
          </v-data-table>
          <!-- this dialog is used for both create and update -->
          <v-dialog v-model="dialog" max-width="500px">
            <template v-if="writeAccess" v-slot:activator="{ on }">
              <div class="d-flex">
                <v-btn color="primary" dark class="ml-auto ma-3" v-on="on">
                  New
                  <v-icon small>mdi-plus-circle-outline</v-icon>
                </v-btn>
              </div>
            </template>
            <v-card>
              <v-card-title>
                <span v-if="editedItem.id">Edit {{ editedItem.id }}</span>
                <span v-else>Create</span>
              </v-card-title>
              <v-card-text>
                <v-row>
                  <v-col cols="12" sm="12">
                    <v-text-field
                      v-model="editedItem.summary"
                      label="Summary"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="4">
                    <v-select
                      v-model="editedItem.mode"
                      :items="modes"
                      label="Mode"
                    ></v-select>
                  </v-col>
                  <v-col cols="12" sm="8">
                    <v-text-field
                      v-model="editedItem.profileId"
                      label="Profile"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="4">
                    <v-select
                      v-model="editedItem.filterType"
                      :items="filterTypes"
                      label="Filter Type"
                    ></v-select>
                  </v-col>
                  <v-col cols="12" sm="8">
                    <v-text-field
                      v-model="editedItem.filter"
                      label="Filter"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="showEditDialog()">
                  Cancel
                </v-btn>
                <v-btn color="blue darken-1" text @click="saveItem(editedItem)">
                  Save
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
  name: "Job",
  components: {},
  data() {
    return {
      headers: [
        { text: "Id", value: "id" },
        { text: "Summary", value: "summary" },
        { text: "Mode", value: "mode" },
        { text: "Profile", value: "profileId" },
        { text: "Filter Type", value: "filterType" },
        { text: "Filter", value: "filter" },
        { text: "Status", value: "status" },
        { text: "Create Date", value: "createDate" },
        { text: "Start Date", value: "startDate" },
        { text: "End Date", value: "endDate" },
        { text: "Action", value: "actions", sortable: false }
      ],
      modes: ["PROFILE", "CORPORATE", "RELATED_PROFILE"],
      filterTypes: [
        "COUNTRY",
        "TITLE",
        "WORKPLACE",
        "SCHOOL",
        "EDUCATION_LEVEL",
        "EDUCATION_SECTION"
      ],
      items: [],
      dialog: false,
      loading: true,
      editedItem: {},
      writeAccess: false
    };
  },
  mounted() {
    this.writeAccess = routeUtil.checkUserComponentAccess("Job").includes("w");

    this.loadItems();
  },
  methods: {
    showEditDialog(item) {
      this.editedItem = item || {};
      this.dialog = !this.dialog;
    },
    changeStatus(item) {
      console.log(item);
      call
        .api({
          endpoint: "/management/crawling/" + item.id,
          body: item
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
    loadItems() {
      this.loading = true;
      call
        .api({
          endpoint: "/view/crawling",
          method: "GET"
        })
        .then(response => {
          this.items = response.crawlingJobs;
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
    saveItem(item) {
      call
        .api({
          endpoint: "/management/crawling/",
          body: item
        })
        .then(response => {
          console.log(JSON.stringify(response));
          this.loadItems();
          this.showEditDialog();
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
          endpoint: "/management/crawling/delete/" + item.id,
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
    }
  }
};
</script>

<style lang="css" scoped></style>
