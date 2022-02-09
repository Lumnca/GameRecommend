<template>
  <div>
    <el-upload
      class="upload-demo"
      :action="fileUploadAddr"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :on-success="handleSuccess"
      :before-upload="beforeUpload"
      :file-list="fileList"
      :on-exceed="onExceed"
      :limit="limit"
      name="upload"
      list-type="picture"
    >
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件</div>
    </el-upload>
  </div>
</template>

<style>
</style>

<script>
export default {
  name: "FileUpload",
  props: {
    msg: String,
    limit: Number,
    type: Number,
    size: Number
  },
  data() {
    return {
      fileUploadAddr: "http://127.0.0.1:8868/uploadimg",
      fileList: [],
      imgs: [],
      filename: ""
    };
  },
  methods: {
    handlePreview(file) {
      console.log(file);
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handleSuccess(res, file) {
      file.name = res.msg;
      this.fileList.push(file);

      if (this.type === 2) {
        this.imgs.push(file.name);
      } else {
        this.filename = file.name;
      }
    },
    beforeUpload(file) {
      const isImg =
        file.type === "image/jpeg" ||
        file.type === "image/png" ||
        file.type === "image/jpg";
      const isLimit = file.size / 1024 / 1024 < (this.size);

      if (!isImg) {
        this.$message.error("上传图片格式不正确！");
      }
      if (!isLimit) {
        this.$message.error("上传图片大小超出限制！");
      }
      return isImg && isLimit;
    },
    onExceed() {
      this.$message.warning("已经超出文件上传限制！");
    }
  }
};
</script>
