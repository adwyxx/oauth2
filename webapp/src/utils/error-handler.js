'use strict'
import router from '@/router/router'

const errorHandler = {
  install: function (Vue) {
    let handlError = function (error, vm) {
      router.push({
        name: 'Error',
        params: {
          message: error.message,
          stack: error.stack
        }
      })
    }

    Vue.config.errorHandler = handlError

    Vue.prototype.$thows = (error) => handlError(error, Vue)
  }
}

export default errorHandler
