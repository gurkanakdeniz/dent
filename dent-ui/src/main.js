// import "bootstrap-css-only/css/bootstrap.min.css";
// import "mdbvue/build/css/mdb.css";

import Vue from "vue";
import LogRocket from "logrocket";
import notifications from "vue-notification";
import App from "./App";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";

import "material-design-icons-iconfont/dist/material-design-icons.css";

if (process.env.VUE_APP_LOGROCKET) {
  LogRocket.init(process.env.VUE_APP_LOGROCKET);
}

Vue.config.productionTip = false;
Vue.use(notifications);

new Vue({
  render: h => h(App),
  router,
  store,
  vuetify,
  components: { App }
}).$mount("#app");
