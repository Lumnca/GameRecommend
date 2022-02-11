<template>
  <div>
    <panel :list="games" type="1"></panel>

    <grid></grid>
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
  Panel
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
    Panel
  },
  data() {
    return {
      // note: changing this line won't causes changes
      // with hot-reload because the reloaded component
      // preserves its current state and we are modifying
      // its initial state.
      games: [],
      list: []
    };
  },
  methods: {
    onItemClick() {
      console.log("on item click");
    }
  },
  created() {
    this.$http.get("http://47.106.254.86:8868/getGames").then(({ data }) => {
      this.games = [];
      data.forEach(e => {
        this.games.push({
          src: e.imgUrl,
          title: e.name,
          desc:
            e.info,
          url: {
            path: "/game",
            query: {game:e}
          }
        });
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
