<template>
  <div class="gs">
    <divider>游戏列表</divider>
    <flexbox orient="vertical">
      <flexbox-item class="game-item" v-for="game in games" :key="game.id">
        <div class="flex-demo" @click="gameItemClick(game)">
          <flexbox>
            <flexbox-item :span="4">
              <div class="flex-demo">
                <img :src="game.imgUrl" width="120" height="80" />
              </div>
            </flexbox-item>
            <flexbox-item>
              <div class="flex-demo">
                <h4>{{game.name}}</h4>
              <div>
                类型: <span class="lab" v-for="lab in JSON.parse(game.label) " :key="lab">{{lab}}</span>
                </div> 
                <div >平台:{{devs[game.devType]}}
                  <span style="font-size:14px;" @click="goodAdd()">
                     &nbsp;<svg t="1644746223581" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2084" width="14" height="14"><path d="M857.28 344.992h-264.832c12.576-44.256 18.944-83.584 18.944-118.208 0-78.56-71.808-153.792-140.544-143.808-60.608 8.8-89.536 59.904-89.536 125.536v59.296c0 76.064-58.208 140.928-132.224 148.064l-117.728-0.192A67.36 67.36 0 0 0 64 483.04V872c0 37.216 30.144 67.36 67.36 67.36h652.192a102.72 102.72 0 0 0 100.928-83.584l73.728-388.96a102.72 102.72 0 0 0-100.928-121.824zM128 872V483.04c0-1.856 1.504-3.36 3.36-3.36H208v395.68H131.36A3.36 3.36 0 0 1 128 872z m767.328-417.088l-73.728 388.96a38.72 38.72 0 0 1-38.048 31.488H272V476.864a213.312 213.312 0 0 0 173.312-209.088V208.512c0-37.568 12.064-58.912 34.72-62.176 27.04-3.936 67.36 38.336 67.36 80.48 0 37.312-9.504 84-28.864 139.712a32 32 0 0 0 30.24 42.496h308.512a38.72 38.72 0 0 1 38.048 45.888z" p-id="2085"></path></svg>
                     ({{game.good}})
                  </span>
                </div>
              </div>
            </flexbox-item>
          </flexbox>
        </div>
        <divider>-</divider>
      </flexbox-item>
    </flexbox>
  </div>
</template>

<script>
import { dateFormat } from 'vux'
import {
  Grid,
  GridItem,
  GroupTitle,
  Group,
  Cell,
  Tabbar,
  TabbarItem,
  XHeader,
  Radio,
  Panel,
  Flexbox,
  FlexboxItem,
  Divider
} from "vux";
export default {
  name: "games",
  components: {
    Grid,
    Group,
    Cell,
    Tabbar,
    TabbarItem,
    XHeader,
    GridItem,
    GroupTitle,
    Radio,
    Panel,
    Flexbox,
    FlexboxItem,
    Divider
  },
  data() {
    return {
      // note: changing this line won't causes changes
      // with hot-reload because the reloaded component
      // preserves its current state and we are modifying
      // its initial state.
      games: [],
      list: [],
      devs: ["[安卓]", "[PC]", "[IOS]", "[安卓][PC]"]
    };
  },
  methods: {
    dateFormat(date,str){
      return dateFormat(date,str);
    },
    onItemClick() {
      console.log("on item click");
    },
    gameItemClick(game) {
      this.$router.push({
        path: "/game", //跳转的路径
        query: {
          //路由传参时push和query搭配使用 ，作用时传递参数
          game: game
        }
      });
    },
    goodAdd(){
      console.log('ok');
    }
  },
  created() {
    this.$http.get("http://117.50.175.19:8868/getGames").then(({ data }) => {
      this.games = data;
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
.gs {
  overflow-y: scroll;
  overflow-x: hidden;
}
.flex-demo {
  height: 84px;
}
.game-item {
  padding: 0px;
}
.lab {
  text-align: center;
  color: #fff;
  background-color: #b2c0bf;
  border-radius: 4px;
  background-clip: padding-box;
  padding: 2px 6px;
  margin-left: 6px;
  font-size: 12px;
}
.icon{
}
</style>