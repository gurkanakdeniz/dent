import RouteConfig from "../router/route.config.js";
import Store from "../store";

let routeUtil = {};

routeUtil.checkUserAccess = path => {
  try {
    let accessConfig = RouteConfig.roles[Store.state.user.role];
    let result = accessConfig.paths.filter(e => e.name === path);

    return result && result.length > 0;
  } catch (e) {
    // ignore
  }

  return true;
};

routeUtil.checkUserComponentAccess = () => {
  try {
    let accessConfig = RouteConfig.roles[Store.state.user.role];
    return accessConfig.access;
  } catch (e) {
    // ignore
  }

  return "";
};

export default routeUtil;
