<template>
  <div>
    <el-container>
      <div class="nav">
        <el-menu
          :default-active="activeIndex"
          mode="horizontal"
          @select="handleSelect"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
        >
          <el-menu-item v-for="item in navs" :key="item.index" :index="item.index">
            <img :src="item.icon" width="16" />
            &nbsp; {{' '+item.name}}
          </el-menu-item>
        </el-menu>
      </div>
      <el-main>
        <div class="content">
          <router-view></router-view>
        </div>
      </el-main>
      <el-footer></el-footer>
    </el-container>
  </div>
</template>

<style>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}
.el-aside {
  color: #333;
}
.nav {
  position: fixed;
  width: 110%;
  left: -8px;
  top: 0px;
  z-index: 99;
}
body {
  margin: 0;
  padding: 0;
  background-image: url("../assets/images/map.png");
  width: 100%;
}
.content {
  margin-top: 34px;
  padding: 8px;
  display: flex;
  justify-content: center;
}

</style>

<script>
import {HOST} from '../config.js';
import { router } from '../main.js';
export default {
  name: "HelloWorld",
  data() {
    return {
      activeIndex: "home",
      navs: [
        {
          name: "主 页",
          icon: "src/assets/icons/index.svg",
          size: 32,
          index: "home",
        },
        {
          name: "游 戏",
          icon: "src/assets/icons/game.svg",
          size: 32,
          index: "games",
        },
        {
          name: "留 言",
          icon: "src/assets/icons/talk.svg",
          size: 32,
          index:"talk"
        },
        {
          name: "上 传",
          icon: "src/assets/icons/upload.svg",
          size: 32,
          index:"upload"
        },
        {
          name: "管 理",
          icon: "src/assets/icons/set.svg",
          size: 32,
          index:"manger"
        }
      ],
      type: "home",
      notices: [],
      webData: { visitorNumber: 1, gameNumber: 2 }
    };
  },
  methods: {
    navItemClick(item) {
      this.type = item.type;
    },
    handleSelect(key, keyPath) {
      router.push(key);
    }
  },
  created() {
    axios
      .get(HOST + "/getNotices")
      .then(res => {
        this.notices = res.data;
      })
      .catch(() => {});
    axios
      .get(HOST + "/getWebData")
      .then(res => {
        this.webData = res.data;
      })
      .catch(() => {});
       router.push('/home');
  }
};
</script>
