<template>
  <div class="gs">
    <divider>游戏列表</divider>
    <flexbox orient="vertical">
      <flexbox-item class="game-item" v-for="game in games" :key="game.id">
        <div class="flex-demo" @click="gameItemClick(game)">
          <flexbox>
            <flexbox-item :span="4">
              <div class="flex-demo">
                <img :src="game.imgUrl" width="120" height="80" />
              </div>
            </flexbox-item>
            <flexbox-item>
              <div class="flex-demo">
                <h4>{{game.name}}</h4>
                <div>类型:{{game.label}}</div>
                <div>平台:{{devs[game.devType]}}</div>
              </div>
            </flexbox-item>
          </flexbox>
        </div>
        <divider>-</divider>
      </flexbox-item>
    </flexbox>
  </div>
</template>

<script>
import {
  Grid,
  GridItem,
  GroupTitle,
  Group,
  Cell,
  Tabbar,
  TabbarItem,
  XHeader,
  Radio,
  Panel,
  Flexbox,
  FlexboxItem,
  Divider
} from "vux";
export default {
  name: "games",
  components: {
    Grid,
    Group,
    Cell,
    Tabbar,
    TabbarItem,
    XHeader,
    GridItem,
    GroupTitle,
    Radio,
    Panel,
    Flexbox,
    FlexboxItem,
    Divider
  },
  data() {
    return {
      // note: changing this line won't causes changes
      // with hot-reload because the reloaded component
      // preserves its current state and we are modifying
      // its initial state.
      games: [],
      list: [],
      devs: ["[安卓]", "[PC]", "[IOS]", "[安卓][PC]"]
    };
  },
  methods: {
    onItemClick() {
      console.log("on item click");
    },
    gameItemClick(game) {
      this.$router.push({
        path: "/game", //跳转的路径
        query: {
          //路由传参时push和query搭配使用 ，作用时传递参数
          game:game
        }
      });
    }
  },
  created() {
    this.$http.get("http://47.106.254.86:8868/getGames").then(({ data }) => {
      this.games = data;
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
.gs {
  overflow-y: scroll;
  overflow-x: hidden;
}
.flex-demo {
  height: 84px;
}
.game-item {
  padding: 0px;
}
</style>
