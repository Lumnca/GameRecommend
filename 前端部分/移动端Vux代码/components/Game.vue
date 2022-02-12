<template>
  <div>
    <swiper auto :interval="1000" :list="gameImgs" class="text-scroll" :show-dots="false"></swiper>
    <div>
      <group>
        <cell title="名称" :value="game.name"></cell>
        <cell title="日期" :value="dateFormat(new Date(game.date), 'YYYY-MM-DD HH:mm')"></cell>
        <cell title="大小" :value="game.size+' MB'"></cell>
        <cell title="类型" :value="game.label"></cell>
        <cell title="支持设备" :value="devs[game.devType]"></cell>
        <cell title="游戏简介"></cell>
      </group>
      <div class="info" v-html="game.info"></div>
    </div>
  </div>
</template>
<style scoped>
.info {
  padding: 16px;
  overflow: scroll;

  height: 240px;
}
</style>
<script>
import {
  Group,
  Cell,
  Tabbar,
  TabbarItem,
  XHeader,
  XImg,
  Swiper,
  SwiperItem
} from "vux";
import { dateFormat } from "vux";

export default {
  name: "game",
  props: {},
  components: {
    Group,
    Cell,
    Tabbar,
    TabbarItem,
    XHeader,
    XImg,
    Swiper,
    SwiperItem
  },
  data() {
    return {
      // note: changing this line won't causes changes
      // with hot-reload because the reloaded component
      // preserves its current state and we are modifying
      // its initial state.
      game: {
        name: "nn"
      },
      devs: ["[安卓]", "[PC]", "[IOS]", "[安卓][PC]"],
      gameImgs: []
    };
  },
  methods: {
    dateFormat(data, s) {
      return dateFormat(data, s);
    },
    imgChilk(item) {
      console.log(item);
    }
  },
  created() {
    this.game = this.$route.query.game;
    let imgs = JSON.parse(this.game.imgs);
    imgs.forEach(e => {
      this.gameImgs.push({
        url: "javascript:",
        img: e
      });
    });
  }
};
</script>

<style>
.vux-demo {
  text-align: center;
}
.logo {
  width: 100px;
  height: 100px;
}
</style>
