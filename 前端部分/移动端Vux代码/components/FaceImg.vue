<template>
  <div>
    <img src="src/assets/Actor1.png" width="72" height="72">
  </div>
</template>

<script>
import {
  Group,
  Cell,
  Tabbar,
  TabbarItem,
  XHeader,
  Divider,
  Card,
  Flexbox,
  FlexboxItem
} from "vux";
export default {
  components: {
    Group,
    Cell,
    Tabbar,
    TabbarItem,
    XHeader,
    Divider,
    Card,
    Flexbox,
    FlexboxItem
  },
  data() {
    return {
      // note: changing this line won't causes changes
      // with hot-reload because the reloaded component
      // preserves its current state and we are modifying
      // its initial state.
    };
  },
  methods: {},
  mounted() {

  }
};
</script>

<style>
</style>
