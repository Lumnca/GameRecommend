import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import {createRouter,createWebHashHistory } from 'vue-router';
import HelloWorld from './components/HelloWorld.vue'
import Home from './components/Home.vue'
import Games from './components/Games.vue'
import Game from './components/Game.vue'
import Table from './components/Table.vue'
import Upload from './components/Upload.vue'
const routes = [
    {path:'/',name:'',component:HelloWorld},
    {path:'/home',name:'home', component:Home},
    {path:'/games',name:'games', component:Games},
    {path:'/game',name:'game', component:Game},
    {path:'/manger',name:'manger', component:Table},
    {path:'/upload',name:'upload', component:Upload},
]
const router = createRouter({
    history:createWebHashHistory(),
    routes:routes
})

createApp(App).use(ElementPlus).use(router).mount('#app')


export {router} ;
