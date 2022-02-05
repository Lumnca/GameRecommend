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
        <el-form-item label="游戏标签">
          <el-checkbox-group v-model="form.type">
            <el-checkbox label="策略" name="type"></el-checkbox>
            <el-checkbox label="SLG" name="type"></el-checkbox>
            <el-checkbox label="热血" name="type"></el-checkbox>
            <el-checkbox label="冒险" name="type"></el-checkbox>
            <el-checkbox label="R18" name="type"></el-checkbox>
            <el-checkbox label="3D" name="type"></el-checkbox>
            <el-checkbox label="回合" name="type"></el-checkbox>
            <el-checkbox label="塔防" name="type"></el-checkbox>
            <el-checkbox label="角色扮演" name="type"></el-checkbox>
            <el-checkbox label="FPS" name="type"></el-checkbox>
            <el-checkbox label="休闲" name="type"></el-checkbox>
            <el-checkbox label="益智" name="type"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="标题图片">
          <el-upload
            class="upload-demo"
            action="http://127.0.0.1:8868/uploadimg"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :on-success="handleSuccess"
            :file-list="fileList"
            :limit="1"
            name="upload"
            list-type="picture"
          >
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="游戏截图">
          <el-upload
            class="upload-demo"
            action="http://127.0.0.1:8868/uploadimg"
            :on-preview="handlePreview"
            :on-remove="handleRemove2"
            :on-success="handleSuccess2"
            :file-list="gFs"
            name="upload"
            list-type="picture"
          >
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件</div>
          </el-upload>
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

export default {
  name: "Upload",
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
      gFs:[],
      imgs:[]
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
          imgUrl: this.filename,
          size: this.form.size,
          info: this.editor.txt.html(),
          date: new Date(),
          good: 0,
          href: this.form.href,
          label: JSON.stringify(this.form.type),
          imgs : JSON.stringify(this.imgs)
        })
        .then(res => {
          console.log(res.data);
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
