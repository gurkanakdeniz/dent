let dateUtil = {};

dateUtil.format = value => {
  try {
    if (value) {
      return new Date(value).toLocaleString();
    }
  } catch (e) {
    console.log(e);
  }

  return "";
};

export default dateUtil;
