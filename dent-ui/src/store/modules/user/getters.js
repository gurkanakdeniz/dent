let getters = {};

getters.user = state => {
  return state;
};

getters.username = state => {
  return state.username;
};

getters.role = state => {
  return state.role;
};

export default getters;
