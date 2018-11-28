import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/pages/Login'
import AuthService from '@/utils/auth-service'

Vue.use(Router)
Vue.use(AuthService)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login,
    alias: '/login',
    mete: {
      requireAuth: false
    }
  },
  {
    path: '/error',
    name: 'Error',
    component: Error,
    mete: {
      requireAuth: false
    }
  }
]

const router = new Router({
  model: 'history',
  routes: routes
})

// 进入下一个路由前处理逻辑
router.beforeEach((to, from, next) => {
  // 叛段是否需要登录验证
  if (to.meta.requireAuth) {
    // 判断是否已经登录，如果登录则直接跳转到下个路由地址，否则跳转到登录页面
    if (AuthService.isAuthorized()) {
      next()
    } else {
      next({
        name: 'Login',
        query: { url: to.path }
      })
    }
  } else {
    next()
  }
})

export default router
