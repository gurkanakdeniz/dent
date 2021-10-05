import cookieUtil from "../common/cookie.util.js";

let call = {};

const api = process.env.VUE_APP_API || "http://localhost:8081";

call.api = (parameters = {}) => {
  let requestParams = prepareParams(parameters);
  const requestOptions = prepareOptions(requestParams);

  return fetch(requestParams.url, requestOptions)
    .then(response => {
      console.log("response status " + response.status);

      if (response.status !== 204) {
        return response.json().then(json => ({ json, response }));
      } else {
        return { json: [], response };
      }
    })
    .then(({ json = [], response }) => {
      if (!response.ok) {
        // alert("error popup");
        if (response.status === 403) {
          cookieUtil.exit();
        }

        console.log("response not ok" + JSON.stringify(response));
        return Promise.reject(json);
      }

      try {
        cookieUtil.auth(response.headers);
      } catch (e) {
        // ignore
      }

      return json;
    });
};

function prepareParams(parameters) {
  let requestParams = {
    method: "POST",
    body: undefined,
    url: "",
    endpoint: null
  };

  requestParams = { ...requestParams, ...parameters };
  requestParams.url = api + requestParams.endpoint;

  return requestParams;
}

function prepareOptions(requestParams) {
  let requestOptions = {
    method: requestParams.method,
    headers: {
      "Content-Type": "application/json",
      ...requestParams.headers
    },
    credentials: "include",
    body: JSON.stringify(requestParams.body)
  };

  let token = cookieUtil.token();
  if (token) {
    requestOptions["headers"]["Authorization"] = "Bearer " + token;
  }

  return requestOptions;
}

export default call;
