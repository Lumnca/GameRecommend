<template>
  <div>
    <group title="公告">
      <cell v-for="item in notices" :key="item.id" :title="item.date" :inline-desc="item.content"></cell>
    </group>
  </div>
</template>

<script>
import { Group, Cell, Tabbar, TabbarItem, XHeader } from "vux";
export default {
  name: "index",
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
      notices : [{id:1,content:'Test',date:'1/1'}]
    };
  },
  methods: {},
  created() {
    this.$http.get("http://47.106.254.86:8868/getNotices").then(({ data }) => {
      this.notices = data;
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
