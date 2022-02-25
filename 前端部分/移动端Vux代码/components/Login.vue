<template>
  <div class="login">
    <div class="logo">
      <img src="src/assets/lumnca.png" width="240" />
    </div>
    <group>
      <x-input
        title="账号"
        name="username"
        v-model="username"
        placeholder="请输入账号"
        is-type="china-name"
      ></x-input>
    </group>
    <group>
      <x-input title="密码" type="password" placeholder v-model="password" :min="4" :max="12"></x-input>
    </group>
    <group>
      <x-button type="primary" @click.native="submit()">登 录</x-button>
    </group>
    <div style="text-align:right;padding:4px 16px;">
      <a href="javascript:">注 册</a> &nbsp;&nbsp;&nbsp;
      <a href="#">忘记密码？</a>
    </div>
  </div>
</template>

<script>
import { Group, Cell, Tabbar, TabbarItem, XHeader, XInput, XButton } from "vux";
export default {
  components: {
    Group,
    Cell,
    Tabbar,
    TabbarItem,
    XHeader,
    XButton,
    XInput
  },
  data() {
    return {
      username: "",
      password: ""
    };
  },
  methods: {
    submit() {
      console.log(this.username + "/" + this.password);
      this.$router.push({
        path: "user/myself"
      });
    }
  }
};
</script>

<style>
.vux-demo {
  text-align: center;
}
.logo {
  padding: 16px 72px;
}
.login {
  margin-top: 36%;
}

</style>
