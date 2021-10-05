import Login from "@/components/Login";
import Proxy from "@/components/Proxy";
import Linkedin from "@/components/Linkedin";
import Data from "@/components/Data";
import Profile from "@/components/Profile";
import ProfileUser from "@/components/ProfileUser";
import Job from "@/components/Job";
import Home from "@/components/Home";

const routeConfig = {
  roles: {
    ADMIN: {
      access: "rwe",
      paths: [
        { name: "Home" },
        { name: "Proxy" },
        { name: "Job" },
        { name: "Linkedin" },
        { name: "Data" },
        { name: "Profile" },
        { name: "ProfileUser" }
      ]
    },
    USER: {
      access: "r",
      paths: [
        { name: "Home" },
        { name: "Proxy" },
        { name: "Job" },
        { name: "Linkedin" },
        { name: "Data" },
        { name: "Profile" },
        { name: "ProfileUser" }
      ]
    },
    API: {
      access: "r",
      paths: [{ name: "Home" }, { name: "Profile" }, { name: "ProfileUser" }]
    }
  },
  routes: [
    {
      name: "Login",
      path: "/login",
      component: Login
    },
    {
      path: "/",
      alias: "/h",
      component: Home,
      meta: {
        auth: true
      },
      children: [
        {
          name: "Home",
          path: "",
          component: Home,
          meta: {
            auth: true
          }
        },
        {
          name: "Proxy",
          path: "Proxy",
          component: Proxy,
          meta: {
            auth: true
          }
        },
        {
          name: "Job",
          path: "Job",
          component: Job,
          meta: {
            auth: true
          }
        },
        {
          name: "Linkedin",
          path: "Linkedin",
          component: Linkedin,
          meta: {
            auth: true
          }
        },
        {
          name: "Data",
          path: "Data",
          component: Data,
          meta: {
            auth: true
          }
        },
        {
          name: "Profile",
          path: "Profile",
          component: Profile,
          meta: {
            auth: true
          }
        },
        {
          name: "ProfileUser",
          path: "ProfileUser",
          component: ProfileUser,
          props: true,
          meta: {
            auth: true
          }
        }
      ]
    }
  ]
};

export default routeConfig;
