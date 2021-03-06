'use strict'
/* 权限认证服务，认证用户是否登录，以及获取token和刷新token功能 */
import VueCookies from 'vue-cookies'
import AppConfig from '@/app-config'
import http from '@/utils/http'

const AuthService = {}

AuthService.install = function (Vue) {
  let COOKIES_KEY_TOKEN = 'access_token'
  // 登录
  Vue.prototype.$login = function (username, password, redirctUrl) {
    var params = {username: username,
      password: password,
      grant_type: AppConfig.authorizedGrantType,
      client_id: AppConfig.clientId,
      client_secret: AppConfig.clientSecret
    }
    return http.get(AppConfig.tokenUrl, params).then(response => {
      if (response.status === 200 && response.data !== null) {
        VueCookies.set(COOKIES_KEY_TOKEN, response.data)
        console.log(VueCookies.get(COOKIES_KEY_TOKEN))
        if (redirctUrl) {
          window.location.href = redirctUrl
        }
      } else {
        console.log(response)
      }
    })
  }
  // 验证是否登录
  Vue.prototype.$isAuthorized = function () {
    var token = this.getAccessToken()
    if (token === null) {
      return false
    }
    return true
  }
  // 获取token信息
  Vue.prototype.$getAccessToken = function () {
    if (VueCookies.isKey(COOKIES_KEY_TOKEN)) {
      return VueCookies.get(COOKIES_KEY_TOKEN)
    }
    return null
  }
  // 刷新token
  Vue.prototype.$refreshToken = function () {
  }
}

export default AuthService
