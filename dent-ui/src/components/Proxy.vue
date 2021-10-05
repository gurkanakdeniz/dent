<template>
  <v-container grid-list-xl fill-height fluid>
    <v-layout row wrap>
      <v-flex md12>
        <v-card>
          <v-card-title primary-title class="justify-center">
            PROXY ACCOUNTS
          </v-card-title>
          <v-data-table
            :headers="headers"
            :items="items"
            mobile-breakpoint="800"
            class="elevation-0"
            :loading="loading"
            loading-text="Loading... Please wait"
          >
            <template v-slot:item.lastAccessDate="{ item }">
              {{ formatDate(item.lastAccessDate) }}
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
                  <v-col v-if="editedItem.id" cols="12" sm="3">
                    <v-text-field
                      v-model="editedItem.status"
                      label="Status"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" sm="12" v-else>
                    <v-col cols="12" sm="12">
                      <v-text-field
                        v-model="editedItem.hostname"
                        label="Hostname"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="12">
                      <v-text-field
                        v-model="editedItem.port"
                        label="Port"
                      ></v-text-field>
                    </v-col>

                    <v-col cols="12" sm="12">
                      <v-select
                        v-model="editedItem.type"
                        :items="types"
                        label="Type"
                      ></v-select>
                    </v-col>

                    <v-col cols="12" sm="12">
                      <v-text-field
                        v-model="editedItem.username"
                        label="Username"
                      >
                      </v-text-field>
                    </v-col>
                    <v-col cols="12" sm="12">
                      <v-text-field
                        v-model="editedItem.password"
                        label="Password"
                      ></v-text-field>
                    </v-col>
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
  name: "Proxy",
  components: {},
  data() {
    return {
      headers: [
        { text: "Id", value: "id" },
        { text: "Host", value: "hostname" },
        { text: "Port", value: "port" },
        { text: "Type", value: "type" },
        { text: "Username", value: "username" },
        { text: "Last Access Date", value: "lastAccessDate" },
        { text: "Status", value: "status" },
        { text: "Action", value: "actions", sortable: false }
      ],
      types: ["SOCKS5", "SOCKS4"],
      items: [],
      loading: true,
      dialog: false,
      editedItem: {},
      writeAccess: false
    };
  },
  mounted() {
    this.writeAccess = routeUtil
      .checkUserComponentAccess("Proxy")
      .includes("w");

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
          endpoint: "/view/proxy",
          method: "GET"
        })
        .then(response => {
          this.items = response.accounts;
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
          endpoint: "/management/proxy/",
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
    changeStatus(item) {
      call
        .api({
          endpoint: "/management/proxy/" + item.id,
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
    deleteItem(item) {
      call
        .api({
          endpoint: "/management/proxy/delete/" + item.id,
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
