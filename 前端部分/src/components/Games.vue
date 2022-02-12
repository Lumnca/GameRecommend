<template>
  <div class="box">
    <div v-if="!dialogVisible">
      <el-card class="box-card">
        <el-row :gutter="20">
          <el-col :span="2">
            <b>游戏检索</b>
          </el-col>
          <el-col :span="2">
            升降序
            <el-switch
              v-model="sortV"
              active-color="#13ce66"
              inactive-color="#13ce66"
              :change="dSort()"
            ></el-switch>
          </el-col>
          <el-col :span="4">
            <el-select v-model="value" placeholder="排序方式" :change="sort()">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-col>
          <el-col :span="4">
            <el-select v-model="value2" placeholder="设备类型" :change="devSelect()">
              <el-option
                v-for="item2 in options2"
                :key="item2.value"
                :label="item2.label"
                :value="item2.value"
              ></el-option>
            </el-select>
          </el-col>
          <el-col :span="4">
            <el-select v-model="value3" placeholder="游戏分类">
              <el-option
                v-for="item in options3"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-col>
          <el-col :span="8">
            <el-input placeholder="请输入内容" v-model="input2">
              <div slot="append">.com</div>
            </el-input>
          </el-col>
        </el-row>

        <hr />
        <div class="gs">
          <el-row>
            <el-col :span="6" v-for="(o) in games" :key="o">
              <el-card class="game-item" @click="itemClick(o)">
                <div>
                  <img :src="o.imgUrl" />
                </div>
                <div style="padding:12px;">
                  <div>
                    <strong>{{o.name}}{{devType[o.devType]}}</strong>
                  </div>
                  <div style="margin-top:4px;margin-bottom:4px;" class="glabel">
                    <span v-for="ic in JSON.parse(o.label)" :key="ic">
                      <el-tag size="small">{{ic}}</el-tag>
                    </span>
                  </div>
                  <el-row :gutter="20">
                    <el-col
                      :span="8"
                      style="color:#330033"
                    >大小: {{o.size>1000? Math.floor(o.size/1000) + 'G':o.size + 'M'}}</el-col>
                    <el-col :span="4"></el-col>
                    <el-col :span="12" style="color:#909399;">
                      <div>
                        {{dateTranslate(o.date)}}
                        <el-divider direction="vertical"></el-divider>
                        <i class="el-icon-star-off"></i>
                        ({{o.good}})
                      </div>
                    </el-col>
                  </el-row>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </div>
        <hr />
        <div style="width:100%">
          <el-row :gutter="20">
            <el-col :span="8">
              <div class="grid-content bg-purple"></div>
            </el-col>
            <el-col :span="8">
              <div class="grid-content bg-purple">
                <div style="margin:auto;">
                  <el-pagination
                    backgroun
                    @current-change="handleCurrentChange"
                    layout="prev, pager, next"
                    :page-size="8"
                    :total="origin.length"
                  ></el-pagination>
                </div>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="grid-content bg-purple"></div>
            </el-col>
          </el-row>
        </div>
      </el-card>
    </div>
    <div v-else>
      <el-card>
        <el-breadcrumb separator="/">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>
            <a @click="dialogVisible = false">返回游戏</a>
          </el-breadcrumb-item>
          <el-breadcrumb-item>{{game.name}}</el-breadcrumb-item>
        </el-breadcrumb>
      </el-card>
      <br />
      <div>
        <el-row :gutter="20">
          <el-col :span="16">
            <el-card>
              <div>
                <h2>
                  {{game.name}}
                  <span v-for="el in JSON.parse(game.label)" :key="el">
                    <el-tag>{{el}}</el-tag>&nbsp;
                  </span>
                </h2>
                <span>
                  上传者: Lumnca
                  <el-divider direction="vertical"></el-divider>
                  {{dateTranslate(game.date)}}
                  <el-divider direction="vertical"></el-divider>
                  <el-button type="text" @click="goodAdd()">
                    <i class="el-icon-star-off"></i>
                  </el-button>
                  ({{game.good}})
                </span>
              </div>
              <el-divider></el-divider>
              <img :src="game.imgUrl" width="480" height="320" />
              <el-tabs v-model="activeName">
                <el-tab-pane label="游戏介绍" name="first">
                  <div v-html="game.info"></div>
                </el-tab-pane>
                <el-tab-pane label="游戏截图" name="second">
                  <el-carousel height="640px">
                    <el-carousel-item v-for="item in JSON.parse(game.imgs)" :key="item">
                      <div style="padding:8px;">
                        <div class="demo-image__preview">
                          <el-image
                            style="width: 1080px; height: 640px"
                            :src="item"
                          ></el-image>
                        </div>
                      </div>
                    </el-carousel-item>
                  </el-carousel>
                </el-tab-pane>
              </el-tabs>
            </el-card>
          </el-col>
          <el-col :span="8">
            <el-card class="gameinfo">
              <div>
                <strong>游戏名称:</strong>
                {{game.name}}
              </div>
              <div>
                <strong>支持设备:</strong>
                {{devType[game.devType]}}
              </div>
              <div>
                <strong>文件大小:</strong>
                {{game.size}} M
              </div>
              <div>
                <strong>上传日期:</strong>
                {{dateTranslate(game.date)}}
              </div>
              <div>
                <strong>游戏链接:</strong>
                {{game.href==null? '无':game.href}}
              </div>
              <div>
                <strong>游戏标签:</strong>
                <span v-for="item in JSON.parse(game.label)" :key="item">
                  <el-tag style="margin:4px;" effect="dark">{{ item }}</el-tag>
                </span>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
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
.box {
  height: 800px;
}
.gameinfo div {
  padding: 4px;
  margin: 4px;
}
</style>
<script>
export default {
  name: "Games",
  props: {
    type: String
  },
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
      curPageIndex : 1
    };
  },
  created() {
    axios
      .get(HOST + "/getGames")
      .then(res => {
        this.origin = [...res.data];
      })
      .catch(() => {});
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
        this.changePageData();
        return;
      }
      this.games = [];
      this.origin.forEach(e => {
        if (e.devType === this.value2 || e.devType === 3) {
          this.games.push(e);
        }
      });
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
      this.game.good += 1;
      if (
        window.localStorage.getItem("#" + this.game.id) == new Date().getDate()
      ) {
        this.$message("今日该游戏已经点赞了！");
      } else {
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
    changePageData(){
        this.games = this.origin.slice((this.curPageIndex - 1) * 8, (this.curPageIndex) * 8 );
    }
  }
};
</script>
