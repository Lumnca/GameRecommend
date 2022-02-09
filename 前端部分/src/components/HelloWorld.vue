<template>
  <div>
    <el-container style="height: 900px; border: 1px solid #eee;">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu >
          <el-menu-item
            v-for="item in navs"
            :key="item.index"
            :index="item.index"
            @click="navItemClick(item)"
          >
            <img :src="item.icon" width="32" height="32" />
            <span style="font-size:18px;margin-top: 4px;">{{item.name}}</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-container class="content">
        

        <el-main>
         <div>
            <Content :type="type">
          </Content>
         </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}
.content{
  background: #333;
}
.el-aside {
  color: #333;
}
</style>

<script>

import Content from './Content.vue';
export default {
  name: "HelloWorld",
  components: {Content},
  props: {
    msg: String
  },
  data() {
    const item = {
      date: "2016-05-02",
      name: "王小虎",
      address: "上海市普陀区金沙江路 1518 弄"
    };
    return {
      tableData: Array(20).fill(item),
      navs: [
        { name: "--主 页", icon: "src/assets/icons/index.svg", size: 32, index: "1",type:'home' },
        { name: "--游 戏", icon: "src/assets/icons/game.svg", size: 32, index: "2",type:'game' },
        { name: "--留 言", icon: "src/assets/icons/talk.svg", size: 32, index: "3",type:'talk' },
        { name: "--上 传", icon: "src/assets/icons/upload.svg", size: 32, index: "4",type:'upload' },
        { name: "--管 理", icon: "src/assets/icons/manger.svg", size: 32, index: "5",type:'manger' }
      ],
      type:'home'
    };
  },
  methods: {
    navItemClick(item) {
      this.type = item.type;
    }
  }
};
</script>
