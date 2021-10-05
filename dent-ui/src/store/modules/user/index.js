import getters from "./getters";
import setters from "./setters";
import actions from "./actions";
import mutations from "./mutations";

const state = {
  username: undefined,
  role: undefined
};

const namespaced = true;

const user = {
  namespaced,
  state,
  getters,
  setters,
  actions,
  mutations
};

export default user;
