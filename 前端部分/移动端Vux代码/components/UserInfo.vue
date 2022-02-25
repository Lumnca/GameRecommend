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
              <span>Name : Lumnca</span>
            </div>
            <div class="flex-demo">Age : 23</div>
            <div class="flex-demo">DG : 2584</div>
          </flexbox-item>
        </flexbox>
      </div>
    </card>
    <card>
      <div slot="content" class="card-demo-flex card-demo-content01">
        <div class="vux-1px-r">
          <span>1130</span>
          <br />Item1
        </div>
        <div class="vux-1px-r">
          <span>15</span>
          <br />Item2
        </div>
        <div class="vux-1px-r">
          <span>0</span>
          <br />Item3
        </div>
        <div>
          <span>88</span>
          <br />Item4
        </div>
      </div>
    </card>
    <card>
      <p slot="content" class="card-padding">Tiele</p>
    </card>
    <card>
      <div slot="content" class="card-padding">
        <p style="color:#999;font-size:12px;">Posted on January 21, 2015</p>
        <p
          style="font-size:14px;line-height:1.2;"
        >Quisque eget vestibulum nulla. Quisque quis dui quis ex ultricies efficitur vitae non felis. Phasellus quis nibh hendrerit..</p>
        <group>
          <cell title="名称" value="Item"></cell>
          <cell title="日期" value="Item"></cell>
          <cell title="大小" value="Item"></cell>
          <cell title="类型" value="Item"></cell>
        </group>
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
