// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import FastClick from 'fastclick'
import VueRouter from 'vue-router'
import App from './App'
import Index from './components/Index'
import Games from './components/Games'
import Game from './components/Game'
import Talk from './components/Talk'
import User from './components/User'
import { AjaxPlugin } from 'vux'
Vue.use(AjaxPlugin)
Vue.use(VueRouter)

const routes = [{
  path: '/',
  component: Index
},
{
  path: '/games',
  component: Games
},
{
  path: '/game',
  component: Game
},
{
  path: '/talk',
  component: Talk
},
{
  path: '/user',
  component: User
}]

const router = new VueRouter({
  routes
})

FastClick.attach(document.body)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  router,
  render: h => h(App)
}).$mount('#app-box')
