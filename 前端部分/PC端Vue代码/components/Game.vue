<template>
  <div class="box3">
    <el-card>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/games' }">返回</el-breadcrumb-item>
        <el-breadcrumb-item>{{game.name}}</el-breadcrumb-item>
      </el-breadcrumb>
    </el-card>
    <br />
    <div>
      <el-row :gutter="20">
        <el-col :span="24">
          <el-card>
            <div>
              <h2>{{game.name}}</h2>
              <span>
                上传者
                <i class="el-icon-user"></i> : Lumnca
                <el-divider direction="vertical"></el-divider>
                <i class="el-icon-date"></i>
                {{dateTranslate(game.date)}}
                <el-divider direction="vertical"></el-divider>
                <el-button type="text" @click="goodAdd()">
                  <i class="el-icon-star-off"></i>
                </el-button>
                ({{game.good}})
                <el-divider direction="vertical"></el-divider>
                <strong>支持设备:</strong>
                <el-tooltip
                  v-if="game.devType === 0 ||game.devType === 3"
                  class="item"
                  effect="dark"
                  content="支持安卓"
                  placement="top-start"
                >
                  <i class="el-icon-mobile-phone"></i>
                </el-tooltip>
                <el-tooltip
                  v-if="game.devType === 1 ||game.devType === 3"
                  class="item"
                  effect="dark"
                  content="支持PC电脑"
                  placement="top-start"
                >
                  <i class="el-icon-monitor"></i>
                </el-tooltip>

                <el-divider direction="vertical"></el-divider>
                <strong>文件大小:</strong>
                {{game.size}} M
                <el-divider direction="vertical"></el-divider>
                <span v-for="el in JSON.parse(game.label)" :key="el">
                  <el-tag>{{el}}</el-tag>&nbsp;
                </span>
              </span>
            </div>
            <el-divider></el-divider>

            <img :src="game.imgUrl" width="1080" height="640" />

            <el-tabs v-model="activeName">
              <el-tab-pane label="游戏介绍" name="first">
                <div v-html="game.info"></div>
              </el-tab-pane>
              <el-tab-pane label="游戏截图" name="second">
                <el-carousel height="640px">
                  <el-carousel-item v-for="item in JSON.parse(game.imgs)" :key="item">
                    <div style="padding:8px;">
                      <div class="demo-image__preview">
                        <el-image style="width: 1080px; height: 640px" :src="item"></el-image>
                      </div>
                    </div>
                  </el-carousel-item>
                </el-carousel>
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </el-col>
        <el-col :span="0"></el-col>
      </el-row>
    </div>
  </div>
</template>
<style scoped>
.gs .el-card {
  --el-card-padding: 0px !important;
}
h2 {
  color: #78b0dd;
}
.item {
  padding: 16px 0px;
}
.game-item {
  margin: 12px;
  font-size: 14px;
}
.glabel :not(:first-child) {
  margin-left: 4px;
}
.game-item img {
  width: 100%;
  height: 240px;
}
.game-item:hover {
  cursor: pointer;
  box-shadow: 2px 2px;
}
.box3 {
  max-width: 1200px;
}
.gameinfo div {
  padding: 4px;
  margin: 4px;
}
</style>
<script>
import { HOST } from "../config.js";
export default {
  name: "Game",
  data() {
    return {
      devType: ["[安卓]", "[PC]", "[IOS]", "[安卓][PC]"],
      activeName: "first",
      dialogVisible: false,
      sortV: false,
      options: [
        {
          value: 1,
          label: "上传时间"
        },
        {
          value: 2,
          label: "文件大小"
        },
        {
          value: 3,
          label: "点赞最多"
        }
      ],
      options2: [
        {
          value: 0,
          label: "全部"
        },
        {
          value: 2,
          label: "安卓"
        },
        {
          value: 1,
          label: "PC"
        }
      ],
      options3: [
        {
          value: 0,
          label: "<100m"
        },
        {
          value: 1,
          label: "100m~1000m"
        },
        {
          value: 2,
          label: ">1000m"
        }
      ],
      value: "",
      value2: 0,
      value3: "",
      input2: "",
      games: [],
      game: {},
      origin: [],
      curPageIndex: 1
    };
  },
  created() {
    axios
      .get(HOST + "/getGames")
      .then(res => {
        this.origin = [...res.data];
      })
      .catch(() => {});

    this.game = JSON.parse(this.$route.params.game);
  },
  methods: {
    dSort() {
      if (this.sortV) {
        console.log(this.origin.reverse());
      }
    },
    sort() {
      if (this.value === 1) {
        this.origin.sort((a, b) => {
          return (
            (new Date(a.date).getTime() - new Date(b.date).getTime()) *
            (this.sortV ? 1 : -1)
          );
        });
      } else if (this.value === 2) {
        this.origin.sort((a, b) => {
          return (a.size - b.size) * (this.sortV ? 1 : -1);
        });
      } else if (this.value === 3) {
        this.origin.sort((a, b) => {
          return (a.good - b.good) * (this.sortV ? 1 : -1);
        });
      }
      this.changePageData();
    },
    devSelect() {
      if (this.value2 === 0) {
        return;
      }
      this.games = [];
      this.origin.forEach(e => {
        if (e.devType === this.value2 || e.devType === 3) {
          this.games.push(e);
        }
      });
      this.changePageData();
    },
    itemClick(item) {
      this.dialogVisible = true;
      this.game = item;
      console.log(item);
    },
    dateTranslate(date) {
      let d = new Date(date);
      return d.getFullYear() + "/" + (d.getMonth() + 1) + "/" + d.getDate();
    },
    goodAdd() {
     
      if (
        window.localStorage.getItem("#" + this.game.id) == new Date().getDate()
      ) {
        this.$message("今日该游戏已经点赞了！");
      } else {
        this.game.good += 1;
        axios
          .post(HOST + "/addGood/" + this.game.id)
          .then(res => {
            console.log(res.data);
            this.$message({
              message: "点赞成功！",
              type: "success"
            });
          })
          .catch(() => {});

        window.localStorage.setItem("#" + this.game.id, new Date().getDate());
      }
    },
    handleCurrentChange(val) {
      this.changePageData();
      this.curPageIndex = val;
    },
    changePageData() {
      this.games = this.origin.slice(
        (this.curPageIndex - 1) * 8,
        this.curPageIndex * 8
      );
    }
  }
};
</script>
