// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
/**引入路由管理 */
import router from './router'
/**引入element-ui*/
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
/*使用组件*/
Vue.use(ElementUI)
/**引入jquery*/
// import $ from 'jquery'
/**引入axios,并设置基础URL为后台接口地址 */
var axios=require('axios')
/**将默认的后台接口地址设置为后台Zuul网关地址*/
axios.defaults.baseURL='http://localhost:8090'
//axios默认不发送cookie,开启发送cookie
// axios.defaults.withCredentials = true
/**全局绑定api方法,将axios挂载在Vue实例原型上*/
Vue.prototype.$axios=axios

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
