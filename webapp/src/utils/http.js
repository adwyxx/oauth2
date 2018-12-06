'use strict'
/* 封装Axios,处理http请求 */
import Axios from 'axios'
import qs from 'qs'
import AppConfig from '@/app-config'

/* http请求webapi前缀设置 */
Axios.defaults.baseURL = AppConfig.apiBaseUrl
/* http请求拦截设置 */
Axios.interceptors.request.use(config => {
  // 根据请求方法，序列化传来的参数，根据后端需求是否序列化
  // if (config.method.toLocaleLowerCase() === 'post' || config.method.toLocaleLowerCase() === 'put' || config.method.toLocaleLowerCase() === 'delete') {
  //   config.data = qs.stringify(config.data)
  // }
  return config
}, error => {
  return Promise.reject(error)
})

/* http请求结果返回拦截设置 */
Axios.interceptors.response.use(response => {
  // IE9时response.data是undefined，因此需要使用response.request.responseText(Stringify后的字符串)
  if (response.data === undefined) {
    response.data = response.request.responseText
  }

  return response
}, error => {
  console.log(error)
  return Promise.reject(error.response)
})

const http = {
  requestHeaders: {
    'X-Requested-With': 'XMLHttpRequest',
    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
  },
  checkStatus (response) {
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
  },
  // Get Http 请求
  get (url, params) {
    return Axios({
      method: 'GET',
      url,
      params,
      headers: this.requestHeaders
    }).then((response) => {
      return this.checkStatus(response)
    })
  },
  post (url, data) {
    return Axios({
      method: 'POST',
      url,
      data: qs.stringify(data),
      headers: this.requestHeaders
    }).then(function (response) {
      return this.checkStatus(response)
    })
  }
}

export default http
