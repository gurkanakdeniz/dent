import Vue from "vue";
import Vuex from "vuex";
import user from "./modules/user";
import createPlugin from "logrocket-vuex";
import LogRocket from "logrocket";

const logrocketPlugin = createPlugin(LogRocket);

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    user
  },
  plugins: [logrocketPlugin]
});

export default store;
