<template>
  <v-container grid-list-xl fill-height fluid>
    <v-layout row wrap>
      <v-flex md12>
        <v-card>
          <v-card-title primary-title class="justify-center"> </v-card-title>
          <v-card-text>
            <div v-for="(item, i) in info" :key="i">
              <v-row>
                <v-col cols="2">
                  <v-subheader>{{ i }}</v-subheader>
                </v-col>
                <v-col cols="10">
                  <span v-if="i === 'PHOTO'">
                    <v-row>
                      <v-col cols="12">
                        <v-container grid-list-xl fill-height fluid>
                          <v-layout row wrap>
                            <v-row
                              style="margin-right:1em;"
                              v-for="(value, key, j) in item"
                              :key="j"
                            >
                              <v-img
                                :lazy-src="value"
                                max-height="250"
                                max-width="250"
                                :src="value"
                              ></v-img>
                            </v-row>
                          </v-layout>
                        </v-container>
                      </v-col>
                    </v-row>
                    <br />
                  </span>
                  <span v-else>
                    <span v-if="i === 'SKILL' || i === 'INTEREST'">
                      <v-row>
                        <v-col cols="12">
                          <v-container grid-list-xl fill-height fluid>
                            <v-layout row wrap>
                              <v-row
                                style="margin-right:1em;"
                                v-for="(value, key, j) in item"
                                :key="j"
                              >
                                <v-textarea
                                  :value="value"
                                  no-resize
                                  readonly
                                  solo
                                  rows="1"
                                  auto-grow
                                />
                              </v-row>
                            </v-layout>
                          </v-container>
                        </v-col>
                      </v-row>
                    </span>
                    <span v-else>
                      <v-row v-for="(value, key, j) in item" :key="j">
                        <v-col style="padding-left: 0;" cols="12">
                          <v-textarea
                            :value="value"
                            no-resize
                            readonly
                            solo
                            rows="1"
                            auto-grow
                          />
                        </v-col> </v-row
                    ></span>
                  </span>
                </v-col>
              </v-row>
            </div>
          </v-card-text>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import dateUtil from "../common/date.util.js";

export default {
  name: "ProfileUser",
  props: ["profileUser"],
  components: {},
  data() {
    return {
      info: []
    };
  },
  mounted() {
    // console.log(JSON.stringify(this.profileUser));
    if (this.profileUser) {
      if (this.profileUser.info) {
        this.info = this.profileUser.info;
      }
    }
  },
  methods: {
    formatDate(value) {
      return dateUtil.format(value);
    }
  }
};
</script>

<style lang="css" scoped></style>
