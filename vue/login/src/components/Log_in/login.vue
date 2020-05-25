<template>
  <div class="login">
    <el-card class="box-card-login" style="width:400px;opacity:0.8;background-color: #040505;border: 1px;" shadow="hover">
      <div>
        <p>
          <h2>用户登录</h2>
        </p>
        <p class="log_input">
          <span>
            <img src="../../assets/image/icon/user.png" alt="用户" />
          </span>
          <input class="userName" type="text" placeholder="请输入用户名" v-model="user.username" autofocus="ture" ref="username" />
        </p>
        <p class="log_input">
          <span>
            <img src="../../assets/image/icon/unlock_pwd.png" alt="密码" />
          </span>
          <label>
            <input class="userPwd" type="password" placeholder="请输入密码" v-model="user.userpwd" />
          </label>
        </p>
        <button class="sub_btn" @click="login">登&nbsp;&nbsp;&nbsp;录</button>
        <p class="log_a">
          <router-link to="/sigin" class="mz">没有账号?点此注册.</router-link>
          <div @click="openMask">弹窗公共组件测试</div>
          <dialog-bar v-model="sendVal" type="cancel" title="弹窗标题" content="弹窗内容长度测试弹窗内容长度测试弹窗内容长度测试弹窗内容长度测试弹窗内容长度测试弹窗内容长度测试"
            @cancel="clickCancel()" @danger="clickDanger()" @confirm="clickConfirm()"></dialog-bar>
        </p>
      </div>
    </el-card>
  </div>
</template>

<script>
  /*引入弹窗公共组件*/
  import dialogBar from '../../utils/dialog.vue'
  export default {
    data() {
      return {
        user: {
          username: "",
          userpwd: "",
        },
        /**定义空数组接收返回数据 */
        responseResult: [],
        /*弹窗绑定*/
        sendVal: false,
      };
    },
    methods: {
      /**登录 */
      login() {
        /**与后台约定post提交方式 */
        this.$axios
          .post("/userinfo/login", { /**向后端请求的接口地址为使用zuul后的地址 */
            /**向后台传递数据 */
            username: this.user.username,
            userpwd: this.user.userpwd
          })
          .then(LoginResponse => { /**处理返回对象 */
            /**接收全部返回值 */
            // this.responseResult = JSON.stringify(LoginResponse.data);
            // alert(this.responseResult);
            /**状态码为200时登录成功 */
            if (LoginResponse.data.code === 200) {
              this.$router.replace({
                /**路由跳转地址*/
                path: "/navbar"
              });
            } else {
              // this.isLoading = false;
              /**状态码不为200,弹出后台校验信息*/
              this.$message({
                type: 'error',
                duration: 1300,
                message: LoginResponse.data.message
              });
              // this.$message.error(LoginResponse.data.message);
              // alert(LoginResponse.data.message);
            }
          })
          .catch(failResponse => {}); /**异常处理 */
        /*login()  end*/
      },
      /*弹窗方法*/
      openMask(index) {
        this.sendVal = true;
      },

    },
    components: {
      /*计算属性*/
      'dialog-bar': dialogBar,
    },
  };
</script>

<style>
  @import url("../../assets/css/logsin.css");
</style>
