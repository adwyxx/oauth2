// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from '@/router/router'
import AuthService from '@/utils/auth-service'
import Http from '@/utils/http'
import ErrorHandler from '@/utils/error-handler'

import 'element-ui/lib/theme-chalk/index.css' /* 引入全局element-ui样式 */
import {Button, Input, Form, FormItem, Card, Loading, Dialog, Alert, Row, Col, Collapse, CollapseItem} from 'element-ui'
Vue.use(Button)
Vue.use(Input)
Vue.use(Form)
Vue.use(FormItem)
Vue.use(Card)
Vue.use(Loading)
Vue.use(Dialog)
Vue.use(Alert)
Vue.use(Row)
Vue.use(Col)
Vue.use(Collapse)
Vue.use(CollapseItem)
Vue.use(AuthService)
Vue.use(ErrorHandler)
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
