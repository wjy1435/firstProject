import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from  '../views/Home/Home'
import CourseDetail from '../views/CourseDetail/CourseDetail'
import Login from '../views/Login/Login'
import Order from '../views/Order/Order'
import Pay from  '../views/Pay/Pay'
import Personal from '../views/Personal/Personal'
import Register from '../views/Register/Register'

Vue.use(VueRouter)

  const routes = [
    {
      path:"/",
      name:"Home",
      component:Home
    },
    {
      path:"/CourseDetail",
      name:"CourseDetail",
      component:CourseDetail
    },
    {
      path:"/Login",
      name:"Login",
      component:Login
    },
    {
      path:"/Order",
      name:"Order",
      component:Order,
      meta:{requiresAuth:true}
    },
    {
      path:"/Pay",
      name:"Pay",
      component:Pay,
      meta:{requiresAuth:true}

    },
    {
      path:"/Personal",
      name:"Personal",
      component:Personal,
      meta:{requiresAuth:true}
    },
    {
      path:"/Register",
      name:"Register",
      component:Register
    },
]

const router = new VueRouter({
  routes
})

export default router
