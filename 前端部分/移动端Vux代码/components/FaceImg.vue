<template>
  <div>
    <div>
      <canvas id="faceimg1" style="display: none;"></canvas>
    </div>
    <div>
      <canvas id="faceimg2" width="72" height="72"></canvas>
    </div>
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
    var canvas = document.getElementById("faceimg1");
    var context = canvas.getContext("2d");
    var img = new Image();
    var px = 72;
    img.src = "src/assets/Actor1.png";
    var canvas2 = document.getElementById("faceimg2");
    var context2 = canvas2.getContext("2d");
    img.onload = function() {
      context.drawImage(img, 0, 0, px * 4, px * 2);
      var imgData = context.getImageData(px*3, px, px, px);
      context.clearRect(0, 0, px * 4, px * 2);
      context2.putImageData(imgData, 0, 0);
    };
  }
};
</script>

<style>
</style>
