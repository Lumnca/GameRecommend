<template>
  <div class="game-box">
    <el-row :gutter="20">
      <el-col :span="4"></el-col>
      <el-col :span="16">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <strong>
              网 站 公 告
              <i class="el-icon-message-solid"></i>
            </strong>
          </div>
          <el-divider></el-divider>
          <div>
            <div>
              <div v-for="o in notices" :key="o" style="color:#330066;">
                <el-row>
                  <el-col :span="22">
                    <li>
                      <span>{{o.content}}</span>
                    </li>
                  </el-col>
                  <el-col :span="2">
                    <span>
                      <i class="el-icon-date"></i>
                      {{o.date}}
                    </span>
                  </el-col>
                </el-row>

                <el-divider></el-divider>
              </div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="4"></el-col>
    </el-row>
  </div>
</template>
<style scoped>
h2 {
  color: #78b0dd;
}
.item {
  padding: 16px 0px;
}
.game-item {
  padding: 4px;
  margin: 20px;
}
.game-box{
  width: 100%;
}
.box-card {
  overflow: hidden;
}
</style>
<script>
import {HOST} from '../config.js';
import { router } from '../main.js';
export default {
  name: "Home",
  data() {
    return {
      activeIndex: "home",
      notices: [],
      webData: { visitorNumber: 1, gameNumber: 2 }
    };
  },
  methods: {
  },
  created() {
    axios
      .get(HOST + "/getNotices")
      .then(res => {
        this.notices = res.data;
      })
      .catch(() => {});
    axios
      .get(HOST + "/getWebData")
      .then(res => {
        this.webData = res.data;
      })
      .catch(() => {});
  }
};
</script>
