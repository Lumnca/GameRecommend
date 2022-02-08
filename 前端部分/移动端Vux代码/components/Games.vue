<template>
  <div>
    <grid :cols="2">
      <grid-item  v-for="g in games" :key="g.id" :label="g.name" :link="{path:'/game',query:{game:g}}">
        <img slot="icon" :src="g.imgUrl" width="200" height="80" />
      </grid-item>
    </grid>
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
  XHeader
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
    GroupTitle
  },
  data() {
    return {
      // note: changing this line won't causes changes
      // with hot-reload because the reloaded component
      // preserves its current state and we are modifying
      // its initial state.
      games : []
    };
  },
  methods: {
    onItemClick() {
      console.log("on item click");
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
</style>
