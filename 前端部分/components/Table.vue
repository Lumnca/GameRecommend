<template>
  <div>
    <el-table :data="tableData" show-overflow-tooltip border style="width: 1600px;">
      <el-table-column fixed prop="date" label="日期" width="150"></el-table-column>
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column prop="size" label="大小/MB" width="120"></el-table-column>
      <el-table-column prop="devType" label="类型" width="60"></el-table-column>
      <el-table-column prop="state" label="状态" width="40"></el-table-column>
      <el-table-column prop="good" label="点赞" width="120"></el-table-column>
      <el-table-column prop="label" label="标签"></el-table-column>
      <el-table-column label="图片" width="90">
        <template #default="scope">
          <img
            :src="scope.row.imgUrl"
            width="72"
            height="40"
            :title="scope.row.imgUrl"
            alt="no img"
          />
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" @click="handleClick( scope.row)">Edit</el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.row)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog v-model="dialogVisible" :title="game.name" width="70%">
      <div>
        <el-form ref="game" :model="game" label-width="80px">
          <el-form-item label="游戏名称">
            <el-input v-model="game.name"></el-input>
          </el-form-item>
          <el-form-item label="支持类型">
            <el-select v-model="game.devType" placeholder="选择支持设备类型">
              <el-option label="安卓" :value="0"></el-option>
              <el-option label="PC" :value="1"></el-option>
              <el-option label="IOS" :value="2"></el-option>
              <el-option label="PC,安卓" :value="3"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="下载地址">
            <el-input style="width:400px;" v-model="game.href"></el-input>
          </el-form-item>

          <el-form-item label="游戏大小">
            <el-input-number v-model="game.size" controls-position="right" :min="1" :max="99999"></el-input-number>&nbsp;MB
          </el-form-item>

          <el-form-item label="游戏分类">
            <el-checkbox-group v-model="game.label">
              <el-checkbox label="RPG" name="type"></el-checkbox>
              <el-checkbox label="SLG" name="type"></el-checkbox>
              <el-checkbox label="ACT" name="type"></el-checkbox>
              <el-checkbox label="AVG" name="type"></el-checkbox>
              <el-checkbox label="AAG" name="type"></el-checkbox>
              <el-checkbox label="SRPG" name="type"></el-checkbox>
              <el-checkbox label="RTS" name="type"></el-checkbox>
              <el-checkbox label="FTG" name="type"></el-checkbox>
              <el-checkbox label="STG" name="type"></el-checkbox>
              <el-checkbox label="FPS" name="type"></el-checkbox>
              <el-checkbox label="TPS" name="type"></el-checkbox>
              <el-checkbox label="PZL" name="type"></el-checkbox>
              <el-checkbox label="CAG" name="type"></el-checkbox>
              <div>
                <el-checkbox label="R18" name="type"></el-checkbox>
                <el-checkbox label="3D" name="type"></el-checkbox>
                <el-checkbox label="GalGame" name="type"></el-checkbox>
              </div>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="标题图片">
            <img :src="game.imgUrl" width="500" />
          </el-form-item>
          <el-form-item label="游戏截图">暂不支持修改！</el-form-item>

          <el-form-item label="游戏状态">
            <div>
              <el-select v-model="game.state" placeholder="请选择">
                <el-option label="上架" :value="1"></el-option>
                <el-option label="审核" :value="0"></el-option>
              </el-select>
            </div>
          </el-form-item>
          <el-form-item label="游戏介绍">
            <div id="div1"></div>
          </el-form-item>
        </el-form>
      </div>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="update(game)">确认</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style>
</style>

<script>
import { HOST } from "../config.js";
import E from "wangeditor";
export default {
  name: "Table",
  data() {
    return {
      tableData: [],
      dialogVisible: false,
      game: {
        name: "",
        region: "",
        date1: "",
        date2: "",
        delivery: false,
        type: [],
        resource: "",
        desc: "",
        size: 0
      },
      editor: null,
      fileList: [],
      filename: "",
      gFs: [],
      imgs: []
    };
  },
  methods: {
    handleClick(i) {
      this.game = i;
      this.dialogVisible = true;
      this.game.label = JSON.parse(this.game.label);
      if (this.editor === null) {
        const editor = new E("#div1");
        editor.create();
        this.editor = editor;
        this.editor.txt.html(this.game.info);
      } else {
        this.editor.txt.html(this.game.info);
      }
    },
    handleDelete(g) {
      axios
        .post(HOST + "/delGame/" + g.id)
        .then(res => {
          this.$message(res.data.msg);
          setTimeout(() => {
            window.location.reload();
          }, 2000);
        })
        .catch(() => {});
    },
    update(game) {
      this.game.label = JSON.stringify(this.game.label);
      axios
        .post(HOST + "/updateGame", game)
        .then(res => {
          this.$message(res.data.msg);
          setTimeout(() => {
            window.location.reload();
          }, 2000);
        })
        .catch(() => {});
    }
  },
  created() {
    if (window["pass"]) {
      axios
        .get(HOST + "/getGames")
        .then(res => {
          this.tableData = res.data;
        })
        .catch(() => {});
    }
  },
  mounted() {}
};
</script>
