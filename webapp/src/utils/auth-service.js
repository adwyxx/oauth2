'use strict'
/* 权限认证服务，认证用户是否登录，以及获取token和刷新token功能 */
import Vue from 'vue'
import Cookies from 'vue-cookies'
Vue.use(Cookies)

export default {
  name: 'auth-service',
  // 验证是否登录
  isAuthorized () {
    return true
  },
  // 获取token信息
  getAccessToken () {

  },
  // 刷新token
  refreshToken () {

  }
}
