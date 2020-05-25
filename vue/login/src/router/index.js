import Vue from 'vue'
/**引入路由管理 */
import Router from 'vue-router'
/**引入组件 */
import Login from '@/components/Log_in/login.vue'
import Sigin from '@/components/Sig_in/sigin.vue'
// import NavBar from '../utils/NavBar.vue'
import Page_404 from '../utils/404.vue'
import UnDifind from '../utils/undifind.vue'
import AllUserInfo from '../components/userinfo/AllUserInfo.vue'
import Home from '../components/Home/home.vue'
import yq from '../components/Yq/Yq.vue'
// import side from '../utils/side.vue'
import newAside from '@/components/homr.vue'

/**使用路由管理 */
Vue.use(Router)
/**配置跳转 */
export default new Router({
  routes: [{ // 自动跳转路由
      path: '/',
      redirect: '/newAside'
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/sigin',
      component: Sigin
    },
    {
      path: '/newAside',
      component: newAside
    },
    {
      path: '/newAside',
      component: newAside,
      //重定向
      redirect: '/home',
      children: [{
          path: '/home',
          component: Home,
          hidden: true
        },
        {
          path:'/404',
          component: Page_404,
          hidden:true
        },
        {
          path:'/undifind',
          component:UnDifind,
          hidden: true
        },
        {
          path: '/alluserinfo',
          component: AllUserInfo,
          hidden: true
        },
        {
          path: '/login',
          component: Login,
          hidden: true
        },
        {
          path: '/sigin',
          component: Sigin,
          hidden: true
        },
        {
          path: '/yq',
          component: yq,
          hidden: true
        }
      ]
    }
  ]
})
