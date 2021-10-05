import Vue from "vue";
import Router from "vue-router";
import Store from "../store";
import RouteConfig from "./route.config.js";
import cookieUtil from "../common/cookie.util.js";

Vue.use(Router);

const router = new Router({
  mode: "history",
  base: __dirname,
  routes: RouteConfig.routes
});

router.beforeEach((to, from, next) => {
  console.log(
    "to -> " + to.name + " from -> " + from.name + " next -> " + next.name
  );

  const { loginRequired, authRequired } = check(to);
  if (loginRequired) {
    if (autoLogin()) {
      next();
    } else {
      exit();
    }
  } else {
    if (authRequired) {
      console.log("reject request access");
    } else {
      next();
    }
  }
});

router.afterEach(() => {});

const autoLogin = () => {
  if (!Store.state.user.username) {
    let user = cookieUtil.user();
    try {
      if (user) {
        if (user.username && user.authRole) {
          Store.state.user.role = user.authRole;
          Store.state.user.username = user.username;

          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    } catch (e) {
      return false;
      // ignore
    }
  } else {
    return true;
  }
};

const check = to => {
  const { loginRequired, authRequired } = checkUser(to);

  return {
    loginRequired: loginRequired,
    authRequired: authRequired
  };
};

const checkUser = to => {
  if (to.matched.some(record => record.meta.auth)) {
    try {
      if (!Store.state.user.username) {
        return {
          loginRequired: true,
          authRequired: true
        };
      } else {
        if (!checkUserAccess(to)) {
          return {
            loginRequired: false,
            authRequired: true
          };
        } else {
          return {
            loginRequired: false,
            authRequired: false
          };
        }
      }
    } catch (e) {
      console.log(e);
      return {
        loginRequired: true,
        authRequired: true
      };
    }
  } else {
    return {
      loginRequired: false,
      authRequired: false
    };
  }
};

const checkUserAccess = to => {
  if (to.name) {
    let accessConfig = RouteConfig.roles[Store.state.user.role];
    let result = accessConfig.paths.filter(e => e.name === to.name);

    return result && result.length > 0;
  }

  return true;
};

const exit = () => {
  try {
    cookieUtil.exit();
    Store.state.user.role = null;
    Store.state.user.username = null;
  } catch (e) {
    // ignore
  }
};

export default router;
