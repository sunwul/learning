<template>
  <div class="sigin">
    <el-card class="box-card-sigin" style="width:400px;opacity:0.8;background-color: #040505;border: 1px;" shadow="hover">
      <p>
        <h2>用户注册</h2>
      </p>
      <p class="sig_input">
        <span>
          <img src="../../assets/image/icon/user.png" alt="用户" />
        </span>
        <input class="userName" type="text" placeholder="请输入用户名" autofocus="ture" v-model="user.username" />
      </p>
      <p class="sig_input">
        <span>
          <img src="../../assets/image/icon/unlock_pwd.png" alt="密码" />
        </span>
        <input class="userPwd" type="password" placeholder="请输入密码" v-model="user.userpwd" />
      </p>
      <p class="sig_input">
        <span>
          <img src="../../assets/image/icon/lock_pwd.png" alt="确认密码" />
        </span>
        <input class="rePwd" type="password" placeholder="再次确认密码" v-model="user.userrepwd" />
      </p>
      <p class="sig_input">
        <span>
          <img src="../../assets/image/icon/email.png" alt="email" />
        </span>
        <input class="userEmail" type="text" placeholder="请输入邮箱" v-model="user.useremail" />
      </p>
      <button class="sub_btn" @click="sigin">注&nbsp;&nbsp;&nbsp;册</button>
      <p class="sig_a">
        <router-link to="/login" class="mz">已有账号?赶紧登录吧.</router-link>
      </p>
    </el-card>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        user: {
          username: '',
          userpwd: '',
          userrepwd: '',
          useremail: ''
        },
        responseResult: [],
      };
    },
    methods: {
      sigin() {
        if (this.user.userpwd !== this.user.userrepwd) {
          alert("两次密码输入不一致!");
        } else {
          this.$axios.post(
            /*向后端请求的接口地址为使用zuul后的地址*/
            "/userinfo/sigin", {
              username: this.user.username,
              userpwd: this.user.userpwd,
              useremail: this.user.useremail
            }).then(SiginResponse => {
            /**接收全部返回值 */
            // this.responseResult=JSON.stringify(SiginReponse.data);
            // alert(this.responseResult);
            if (SiginResponse.data.code === 200) {
              this.$router.replace({
                path: "/navbar"
              });
            } else {
              /**状态码不为200,弹出后台校验信息*/
              this.$message({
                type: 'error',
                duration: 1300,
                message: SiginResponse.data.message,
              });
              // alert(SiginReponse.data.message);
            }
          })
        }
      },
    },

  };
</script>
