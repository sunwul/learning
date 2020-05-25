<template>
  <el-container class="home_container">
    <!--侧边栏-->
    <el-aside :width="isCollapse? '64px':'200px'">
      <el-avatar :size="50">SUN</el-avatar>
      <!--侧边栏控制-->
      <div class="toggle_button" @click="toggleCollapse">
        |||
      </div>
      <!--侧边栏菜单区域-->
      <el-menu class="aside_container"
               background-color="#292929"
               text-color="#fff"
               active-text-color="#409eff" :unique-opened="true" router="true" :collapse=isCollapse
               :collapse-transition="false">
        <el-submenu index="1">
          <template slot="title" class="title_yj"><i id="aside_icon_i" class="el-icon-user-solid"></i>用户组</template>
          <el-menu-item index="/alluserinfo"><i class="el-icon-user"></i>用户信息</el-menu-item>
          <el-menu-item index="/login">登录页面</el-menu-item>
          <el-menu-item index="/sigin">注册页面</el-menu-item>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title" class="title_yj"><i id="aside_icon_i" class="el-icon-s-tools"></i>工具页</template>
          <el-menu-item index="/undifind">undifind</el-menu-item>
          <el-menu-item index="/404">404</el-menu-item>
          <el-menu-item index="/yq">疫情(iframe套壳)</el-menu-item>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title" class="title_yj"><i id="aside_icon_i" class="el-icon-s-tools"></i>导航三</template>
          <el-menu-item index="/404">选项1</el-menu-item>
          <el-menu-item index="/404">选项2</el-menu-item>
          <el-menu-item index="/404">选项3</el-menu-item>
        </el-submenu>
        <el-submenu index="4">
          <template slot="title" class="title_yj"><i id="aside_icon_i" class="el-icon-s-tools"></i>导航四</template>
          <el-menu-item index="/404">选项1</el-menu-item>
          <el-menu-item index="/404">选项2</el-menu-item>
          <el-menu-item index="/404">选项3</el-menu-item>
        </el-submenu>
        <el-submenu index="5">
          <template slot="title" class="title_yj"><i id="aside_icon_i" class="el-icon-s-tools"></i>导航五</template>
          <el-menu-item index="/404">选项1</el-menu-item>
          <el-menu-item index="/404">选项2</el-menu-item>
          <el-menu-item index="/404">选项3</el-menu-item>
        </el-submenu>
        <el-submenu index="6">
          <template slot="title" class="title_yj"><i id="aside_icon_i" class="el-icon-s-tools"></i>导航六</template>
          <el-menu-item index="/404">选项1</el-menu-item>
          <el-menu-item index="/404">选项2</el-menu-item>
          <el-menu-item index="/404">选项3</el-menu-item>
          <el-menu-item index="/404">选项1</el-menu-item>
          <el-menu-item index="/404">选项2</el-menu-item>
          <el-menu-item index="/404">选项3</el-menu-item>
          <el-menu-item index="/404">选项1</el-menu-item>
          <el-menu-item index="/404">选项2</el-menu-item>
          <el-menu-item index="/404">选项3</el-menu-item>
          <el-menu-item index="/404">选项1</el-menu-item>
          <el-menu-item index="/404">选项2</el-menu-item>
          <el-menu-item index="/404">选项3</el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>

    <!--页面主体区域-->
    <el-container>
      <!--头部区域-->
      <el-header>
        <div class="header_title">
          <span>后台管理系统</span>
        </div>
        <el-button type="info" @click="logout">退出</el-button>
      </el-header>
      <!--右侧内容主体-->
      <el-main>
        <router-view class="con_main">
          <el-backtop target=".page-component__scroll .el-scrollbar__wrap" :bottom="100">
            <div style="{
                    height: 100%;
                    width: 100%;
                    background-color: #f2f5f6;
                    box-shadow: 0 0 6px rgba(0,0,0, .12);
                    text-align: center;
                    line-height: 40px;
                    color: #1989fa;
                  }">
              UP
            </div>
          </el-backtop>
        </router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  export default {
    name: 'home',
    data() {
      return {
        //左侧菜单数据
        menulist: [],
        //返回数据的id --预留
        objectIcon: [],
        //是否折叠侧边栏
        isCollapse: false
      }
    },
    methods: {
      logout() {//退出
        window.sessionStorage.clear()//清空token
        this.$router.push('/login')//从定向到login
      },
      async getMenuList() {//获取所有菜单
        const {data: res} = await this.$http.get('menus')//从服务端请求菜单数据
        if (res.meta.status !== 200) return this.$message.error(res.meta.msg)
        this.menulist = res.data//将数据装入menulist中
        console.log(res)
      },
      toggleCollapse() {//切换侧边栏的折叠与展开
        this.isCollapse = !this.isCollapse
      }
    },
    created() {//生命周期钩子--创建时
      this.getMenuList()
    }
  }
</script>

<style scoped>
  .home_container {
    position: fixed; /*生成绝对定位的元素，相对于浏览器窗口进行定位*/
    margin-left: -8px;
    margin-top: -8px;
    width: 100%;
    height: 100%;
  }

  .el-header {
    background-color: #303133;
    display: flex;
    justify-content: space-between;
    padding-left: 0;
    align-items: center;
    color: #eeeeee;
    font-size: 20px;
  }

  .header_title {
    display: flex;
    align-items: center;
  }

  .el-avatar {
    margin-right: 15px;
  }

  .el-aside {
    background-color: #292929;
    /*x轴滚动条始终隐藏,y轴始终显示*/
    overflow-x: hidden;
    overflow-y: scroll;
  }

  .aside_container {
    border-right: none; /*去除侧边栏边框*/
  }

  /*侧边导航内部导航菜单样式*/
  .el-submenu {
    /*背景颜色*/
    background: #292929;
  }

  .toggle_button {
    background-color: #292929;
    font-size: 10px;
    line-height: 24px;
    color: #eeeeee;
    text-align: center;
    letter-spacing: 0.4em; /*间距*/
    cursor: pointer; /*鼠标停靠变成*/
  }

  /*.el-main {*/
  /*  background-color: #eaedf1;*/
  /*}*/

  /*菜单图标与文本的间距*/
  .el-icon-location {
    margin-right: 10px;
  }

  /*控制正文内容显示部分*/
  .el-main .con_main {
    /*溢出元素直接舍弃隐藏*/
    overflow: hidden;
  }

</style>

<style>
  * {
    margin: 0;
    padding: 0;
    border: #880000 solid 0px;
  }

  #aside_icon_i{
    margin-right: 20px;
  }

  /*一级菜单 悬停  覆盖el-submenu__title原有样式*/
  .el-submenu__title:hover {
    /*悬停背景颜色*/
    background-color: #33B35A !important;
    /*其它颜色*/
    color: #FFFFFF !important;
    outline: 0 !important;
  }

  /*侧边导航栏下拉二级菜单内容 覆盖el-menu-item原有样式*/
  .el-menu-item {
    /*背景*/
    background-color: #111111;
    /*左边框*/
    border-left: #33b35a solid 3px;
  }

  /*侧边栏下拉二级菜单悬停*/
  .el-menu-item:hover {
    outline: 0 !important;
    /*悬停背景颜色*/
    background-color: #33b35a !important;
    /*其它颜色*/
    color: #FFFFFF !important;
  }

  /*侧边栏下拉二级菜单选中*/
  .el-menu-item.is-active {
    outline: 0 !important;
    background-color: #33b35a !important;
    color: #FFFFFF !important;
  }
</style>
