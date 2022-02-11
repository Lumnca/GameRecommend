<template>
  <div>
    <el-card class="box-card">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="游戏名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="支持类型">
          <el-select v-model="form.region" placeholder="选择支持设备类型">
            <el-option label="安卓" :value="0"></el-option>
            <el-option label="PC" :value="1"></el-option>
            <el-option label="IOS" :value="2"></el-option>
            <el-option label="PC,安卓" :value="3"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="游戏链接">
          <el-switch v-model="form.delivery"></el-switch>
        </el-form-item>
        <el-form-item v-if="form.delivery" label="下载地址">
          <el-input style="width:400px;" v-model="form.href"></el-input>
        </el-form-item>
        <el-form-item label="游戏大小">
          <el-input-number v-model="form.size" controls-position="right" :min="1" :max="99999"></el-input-number>&nbsp;MB
        </el-form-item>
        <el-form-item label="游戏分类">
          <el-checkbox-group v-model="form.type">
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
          <FileUpload ref="f1" :type="1" :limit="1" :size="3"></FileUpload>
        </el-form-item>
        <el-form-item label="游戏截图">
          <FileUpload ref="f2" :type="2" :limit="6" :size="3"></FileUpload>
        </el-form-item>
        <el-form-item label="游戏介绍">
          <div id="div1"></div>-
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">立即创建</el-button>
          <el-button>取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import E from "wangeditor";
import FileUpload from "./tools/FileUpload.vue";
export default {
  name: "Upload",
  components: {
    FileUpload
  },
  data() {
    return {
      form: {
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
    onSubmit() {
      axios
        .post(HOST + "/addGame", {
          name: this.form.name,
          devType: this.form.region,
          id: 9999,
          state: 0,
          imgUrl: this.$refs.f1.filename,
          size: this.form.size,
          info: this.editor.txt.html(),
          date: new Date(),
          good: 0,
          href: this.form.href,
          label: JSON.stringify(this.form.type),
          imgs: JSON.stringify(this.$refs.f2.imgs)
        })
        .then(res => {
          this.$message(res.data.msg);
          setTimeout(() => {
            window.location.reload();
          }, 2000);
        })
        .catch(() => {});
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleSuccess(res, file) {
      this.fileList.push(file);
      this.filename = res.msg;
    },
    handleRemove2(file, fileList) {
      console.log(file, fileList);
    },
    handleSuccess2(res, file) {
      file.name = res.msg;
      this.gFs.push(file);
      this.imgs.push(res.msg);
      console.log(this.gFs);
    }
  },
  mounted() {
    const editor = new E("#div1");
    editor.create();
    this.editor = editor;
  }
};
</script>
