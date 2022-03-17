<template>
  <div>
    <card>
      <div slot="content" class="card-padding">
        <flexbox>
          <flexbox-item :span="4">
           <FaceImg></FaceImg>
          </flexbox-item>
          <flexbox-item :span="8">
            <div class="flex-demo">
              <span>姓名 : Lumnca</span>
            </div>
            <div class="flex-demo">地区 : Acop</div>
            <div class="flex-demo">身份 : 剑士</div>
          </flexbox-item>
        </flexbox>
      </div>
    </card>
    <card>
      <div slot="content" class="card-demo-flex card-demo-content01">
        <div class="vux-1px-r">
          <span>1130</span>
          <br />金币
        </div>
        <div class="vux-1px-r">
          <span>0</span>
          <br />好友
        </div>
        <div class="vux-1px-r">
          <span>0</span>
          <br />卡片
        </div>
        <div>
          <span>0</span>
          <br />收藏
        </div>
      </div>
    </card>
    <card>
      <p slot="content" class="card-padding">
        <a href="#">进入耶特兰特</a>
      </p>
    </card>
    <card>
      <div slot="content" class="card-padding">
        <p style="color:#999;font-size:12px;">个人简介</p>
        <p
          style="font-size:14px;line-height:1.2;">
           暂无介绍
           >
           >
           >
           >
          </p>
       
      </div>
    </card>
  </div>
</template>

<script>
import FaceImg  from './FaceImg.vue';
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
    FlexboxItem,
     FaceImg
  },
  data() {
    return {
      // note: changing this line won't causes changes
      // with hot-reload because the reloaded component
      // preserves its current state and we are modifying
      // its initial state.
    };
  },
  methods: {}
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
.card-demo-flex {
  display: flex;
}
.card-demo-content01 {
  padding: 10px 0;
}
.card-padding {
  padding: 15px;
}
.card-demo-flex > div {
  flex: 1;
  text-align: center;
  font-size: 12px;
}
.card-demo-flex span {
  color: #f74c31;
}
.vux-1px-r {
  border-right: 1px solid #cccccc;
}
</style>
