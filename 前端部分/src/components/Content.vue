<template>
  <div>
    <div v-if="type==='home'">
      <el-row :gutter="20">
        <el-col :span="12">
          <div class="grid-content bg-purple">
            <el-card class="box-card">
              <h2 style="color:;">{{hello()}}</h2>
              <div>
                <b>最近更新</b>
                <br />
                <div v-for="o in notices" :key="o" class="text item">
                  <div>日期: {{o.date}}</div>
                  <div>{{o.content}}</div>
                </div>
              </div>
            </el-card>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="grid-content bg-purple">
            <el-card class="box-card">
              <div class="item">
                <b>数据统计</b>
                <br />
                <div class="text item">
                  <div><strong>访问数量: </strong>{{webData.gameNumber}}</div>
                </div>
                <div class="text item">
                  <div><strong>游戏数量: </strong>{{webData.visitorNumber}}</div>
                </div>
              </div>
            </el-card>
          </div>
        </el-col>
      </el-row>
    </div>
    <div v-else-if="type==='game'">
      <Games></Games>
    </div>
    <div v-else-if="type==='upload'">
      <Upload></Upload>
    </div>
    <div v-else-if="type==='talk'">
      <el-card> 此功能暂未开放！ </el-card>
      </div>
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
</style>
<script>
import Games from "./Games.vue";
import Upload from "./Upload.vue";
export default {
  name: "Content",
  props: {
    type: String
  },
  components: {
    Games,
    Upload
  },
  data() {
    return {
      notices: [],
      webData: { visitorNumber: 1, gameNumber: 2 }
    };
  },
  methods: {
    hello(hs) {
      let h = hs || new Date().getHours();
      if (h > 4 && h < 11) {
        return "早上好";
      } else if (h >= 11 && h <= 13) {
        return "中午好";
      } else if (h > 13 && h <= 17) {
        return "下午好";
      } else if (h > 17 && h <= 23) {
        return "晚上好";
      } else {
        return "凌晨好";
      }
    }
  },
  created() {
    axios
      .get(HOST + "/getNotices")
      .then(res => {
        this.notices = res.data;
        console.log(this.notices);
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
