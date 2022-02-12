<template>
  <div id="app">
    <x-header >{{headerMsg}}</x-header>
    <router-view></router-view>
    <tabbar style="position: fixed;">
      <tabbar-item v-for="item in tabs" :key="item.key" @on-item-click="changeItem(item)" :link="item.path">
        <img slot="icon" :src="item.icon" />
        <span slot="label">{{item.name}}</span>
      </tabbar-item>
    </tabbar>
  </div>
</template>

<script>
import { Group, Cell, Tabbar, TabbarItem,  XHeader } from "vux";
export default {
  name: 'app',
    components: {
    Group,
    Cell,
    Tabbar,
    TabbarItem,
     XHeader
  },
  data() {
    return {
      // note: changing this line won't causes changes
      // with hot-reload because the reloaded component
      // preserves its current state and we are modifying
      // its initial state.
      tabs : [
        {name:'主页',icon:'src/assets/icons/index.svg',key:0,path:'/'},
        {name:'游戏',icon:'src/assets/icons/game.svg',key:1,path:'/games'},
        {name:'谈论',icon:'src/assets/icons/talk.svg',key:2,path:'/talk'},
        {name:'用户',icon:'src/assets/icons/user.svg',key:3},
      ],
      msg: "Hello World!",
      headerMsg : ''
    };
  },
  methods: {
    changeItem(item){
      this.headerMsg = item.name;
      //this.$router.push(item.path);
    }
  },
}
iswap();
function iswap() {
    var uA = navigator.userAgent.toLowerCase();
    var ipad = uA.match(/ipad/i) == "ipad";
    var iphone = uA.match(/iphone os/i) == "iphone os";
    var midp = uA.match(/midp/i) == "midp";
    var uc7 = uA.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
    var uc = uA.match(/ucweb/i) == "ucweb";
    var android = uA.match(/android/i) == "android";
    var windowsce = uA.match(/windows ce/i) == "windows ce";
    var windowsmd = uA.match(/windows mobile/i) == "windows mobile"; 
    if (!(ipad || iphone || midp || uc7 || uc || android || windowsce || windowsmd)) {
        // PC 端
        console.log('PC')
        return true;
    }else{
        // 移动端
        console.log('Mobile')
        return false;
    }
}
</script>

<style lang="less">
@import '~vux/src/styles/reset.less';

body {
  background-color: #fbf9fe;

}
</style>
