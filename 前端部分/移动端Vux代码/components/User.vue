<template>
  <div>
      <router-view></router-view>
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
      username:"",
      password: ""
    };
  },
  methods: {
    submit(){
      console.log(this.username + "/"+ this.password)
      this.$router.push({
        path:'user/myself'
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
