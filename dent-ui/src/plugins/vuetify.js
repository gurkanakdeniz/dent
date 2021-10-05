import Vue from "vue";
import Vuetify from "vuetify/lib/framework";
import colors from "vuetify/lib/util/colors";

Vue.use(Vuetify);

export default new Vuetify({
  theme: {
    themes: {
      light: {
        primary: colors.indigo,
        secondary: colors.indigo.lighten2,
        accent: colors.deepOrange,
        error: colors.red.darken4
      },
      dark: {
        primary: colors.amber.darken3,
        secondary: colors.grey.darken1,
        accent: colors.shades.black,
        error: colors.red.accent4,
        background: "#1e1e1e"
      }
    },
    treeShake: true,
    defaultAssets: {
      font: {
        family: "Libre Baskerville"
      }
    },
    dark: true
  }
});
