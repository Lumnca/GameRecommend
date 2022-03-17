<template>
  <div class="login-card">
    <el-card class="box-card">
      <el-form
        :model="ruleForm"
        status-icon
        ref="ruleForm"
        label-width="60px"
        class="demo-ruleForm"
      >
        <el-form-item label="账号" prop="age">
          <el-input v-model.number="ruleForm.age"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pass">
          <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <div class="ps-text">
            <el-button type="text">注册</el-button> 
              <el-divider direction="vertical"></el-divider>
            <el-button type="text">忘记密码</el-button>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>
<style scoped>
.login-card {
  width: 400px;
  margin-top: 5%;
  padding: 4px;
}
.ps-text {
  width: 100%;
  text-align: right;
}
</style>
<script>
export default {
  data() {
    return {
      ruleForm: {
        pass: "",
        checkPass: "",
        age: ""
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          alert("submit!");
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>
