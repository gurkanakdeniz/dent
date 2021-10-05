<template>
  <v-container grid-list-xl fill-height fluid>
    <v-layout row wrap>
      <v-flex md12>
        <div class="parent">
          <transition :name="transitionName">
            <router-view class="child-view"></router-view>
          </transition>
        </div>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  name: "Home",
  components: {},
  data() {
    return {
      transitionName: "slide-left"
    };
  },
  beforeRouteUpdate(to, from, next) {
    const toDepth = to.path.split("/").length;
    const fromDepth = from.path.split("/").length;
    this.transitionName = toDepth < fromDepth ? "slide-right" : "slide-left";
    next();
  },
  mounted() {},
  methods: {
    test() {
      alert("jedi");
    }
  }
};
</script>

<style lang="css" scoped></style>
