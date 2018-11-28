'use strict'
/* 封装Axios,处理http请求 */
import Axios from 'axios'
import qs from 'qs'
import AppConfig from '@/app-config'

/* http请求webapi前缀设置 */
Axios.defaults.baseURL = AppConfig.apiBaseUrl
/* http请求拦截设置 */
Axios.interceptors.request.use(config => {
  return config
}, error => {
  return Promise.reject(error)
})

/* http请求结果返回拦截设置 */
Axios.interceptors.response.use(config => {
  return config
}, error => {
  return Promise.reject(error.response)
})

const requestHeaders = {
  'X-Requested-With': 'XMLHttpRequest',
  'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
}

function checkStatus (response) {
  // 如果http状态码正常，则直接返回数据
  if (response && (response.status === 200 || response.status === 304 || response.status === 400)) {
    return response
    // 如果不需要除了data之外的数据，可以直接 return response.data
  } else if (response.status === 401) {
    // 如果是401错误，跳转至登录页面
    // router.push({ path: 'Login' })
  }
  // 异常状态下，把错误信息返回去
  return {
    status: -404,
    msg: '网络异常,您请求的页面不存在。'
  }
}

export default{
  name: 'http',
  post (url, data) {
    return Axios({
      method: 'POST',
      url,
      data: qs.stringify(data),
      headers: requestHeaders
    }).then(function (response) {
      return checkStatus(response)
    })
  },
  get (url, params) {
    return Axios({
      method: 'GET',
      url,
      params,
      headers: requestHeaders
    }).then((response) => {
      return checkStatus(response)
    })
  }
}
