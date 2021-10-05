import Vue from "vue";

let mutations = {};

mutations.auth = (state, user) => {
  Vue.set(state, "username", user.username);
  Vue.set(state, "role", user.role);
};

export default mutations;
