import cookieUtil from "../../../common/cookie.util.js";

import LogRocket from "logrocket";

let actions = {};

actions.login = (context, user) => {
  context.commit("auth", user);

  try {
    LogRocket.identify(user.username, {
      name: user.username,
      role: user.role,
      token: cookieUtil.token()
    });
  } catch (e) {
    console.log(e);
  }
};

export default actions;
