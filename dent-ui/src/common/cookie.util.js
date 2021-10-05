let cookieUtil = {};

let authCookieName = "Dent-Auth";

cookieUtil.auth = headers => {
  try {
    const authToken = headers.get(authCookieName);
    if (authToken) {
      localStorage.setItem(authCookieName, authToken);
    }
  } catch (e) {
    // ignore
  }

  return null;
};

cookieUtil.cookie = value => {
  try {
    const cookieValue = document.cookie
      .split("; ")
      .find(row => row.startsWith(value))
      .split("=")[1];

    if (cookieValue) {
      return cookieValue;
    }
  } catch (e) {
    // ignore
  }

  try {
    const storageCookie = localStorage.getItem(value);
    if (storageCookie) {
      return storageCookie;
    }
  } catch (e) {
    // ignore
  }

  return null;
};

cookieUtil.remove = value => {
  try {
    if (value) {
      let cookie = cookieUtil.cookie(value);
      if (cookie) {
        document.cookie =
          value + "=; Path=/; expires=Thu, 01 Jan 1970 00:00:00 GMT";
      }
      //document.cookie = name +`=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;Domain=.${document.domain.split('.').splice(1).join('.')}`;
      //document.domain.split('.')[0]
    }
  } catch (e) {
    // ignore
  }

  try {
    localStorage.removeItem(value);
  } catch (e) {
    // ignore
  }
};

cookieUtil.token = () => {
  try {
    return cookieUtil.cookie(authCookieName);
  } catch (e) {
    // ignore
  }

  return null;
};

cookieUtil.exit = () => {
  try {
    cookieUtil.remove(authCookieName);
  } catch (e) {
    // ignore
  }
};

cookieUtil.user = () => {
  try {
    let user = JSON.parse(
      atob(cookieUtil.cookie(authCookieName).split(".")[1])
    )["sub"];
    if (user) {
      let userObj = JSON.parse(user);
      if (userObj) {
        return userObj;
      }
    }
  } catch (e) {
    // ignore
  }

  return null;
};

export default cookieUtil;
