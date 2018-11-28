// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from '@/router/router'
import Http from '@/utils/http'
import 'element-ui/lib/theme-chalk/index.css' /* 引入全局element-ui样式 */
import {Button, Input, Form, FormItem, Card, Loading, Dialog, Alert} from 'element-ui'
Vue.use(Button)
Vue.use(Input)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Card)
Vue.use(Loading)
Vue.use(Dialog)
Vue.use(Alert)
Vue.prototype.$http = Http
Vue.prototype.$get = Http.get
Vue.prototype.$post = Http.post

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
