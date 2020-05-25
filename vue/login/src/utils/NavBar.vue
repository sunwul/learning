<template>
  <div id="NavBar" style="height: 100%;">
    <el-container style="height: 100%;">
      <!--侧边栏-->
      <el-aside id="navbar" :width="isCollapse? '65px':'200px'">
        <!-- <div class="user_icon"> -->
        <span style="text-align: center;">
          <el-avatar class="user" shape="circle" :size="64"><strong>SUN</strong></el-avatar>
          <div>Hello World</div>
        </span>
        <!-- </div> -->
        <el-menu style="width: 100%;" text-color="#FFFFFF" :unique-opened="false" router="true" :collapse=isCollapse>
          <template index="/home" class="zhuye"><i class="el-icon-s-home"></i>主页</template>
          <el-submenu index="1">
            <template slot="title" class="title_yj"><i class="el-icon-user-solid"></i>用户组</template>
            <el-menu-item index="/alluserinfo"><i class="el-icon-user"></i>用户信息</el-menu-item>
            <el-menu-item index="/login">登录页面</el-menu-item>
            <el-menu-item index="/sigin">注册页面</el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title" class="title_yj"><i class="el-icon-s-tools"></i>工具页</template>
            <el-menu-item index="/undifind">undifind</el-menu-item>
            <el-menu-item index="/404">404</el-menu-item>
            <el-menu-item index="/yq">疫情(iframe套壳)</el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title" class="title_yj"><i class="el-icon-s-tools"></i>导航三</template>
            <el-menu-item index="/404">选项1</el-menu-item>
            <el-menu-item index="/404">选项2</el-menu-item>
            <el-menu-item index="/404">选项3</el-menu-item>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title" class="title_yj"><i class="el-icon-s-tools"></i>导航四</template>
            <el-menu-item index="/404">选项1</el-menu-item>
            <el-menu-item index="/404">选项2</el-menu-item>
            <el-menu-item index="/404">选项3</el-menu-item>
          </el-submenu>
          <el-submenu index="5">
            <template slot="title" class="title_yj"><i class="el-icon-s-tools"></i>导航五</template>
            <el-menu-item index="/404">选项1</el-menu-item>
            <el-menu-item index="/404">选项2</el-menu-item>
            <el-menu-item index="/404">选项3</el-menu-item>
          </el-submenu>
          <el-submenu index="6">
            <template slot="title" class="title_yj"><i class="el-icon-s-tools"></i>导航六</template>
            <el-menu-item index="/404">选项1</el-menu-item>
            <el-menu-item index="/404">选项2</el-menu-item>
            <el-menu-item index="/404">选项3</el-menu-item>
          </el-submenu>
          <div class="about">
            <span class="about_title">关于系统</span>
            <div></div>
            <span class="about_details">
              author&nbsp;:&nbsp;sunwul<br/>
              email&nbsp;:&nbsp;1404512053@qq.com
            </span>
          </div>
        </el-menu>
      </el-aside>

      <!--主体-->
      <el-container class="main" width="100%">
        <!--顶部导航栏 固定-->
        <el-header style="text-align: right;font-size: 26px">
          <span @click="toggleCollapse">切换{{isCollapse}}</span>
          <span style="font-size: 18px;text-align: right;">后台ID生成有时间戳的参与,根据后台查询规则,将时间近的ID显示在前面</span>
          <span>顶部标题栏</span>
        </el-header>

        <!--此处插入正文信息-->
        <el-main style="padding: 6px 12px 6px 12px;">
          <!-- <el-scrollbar> -->
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
          <!-- </el-scrollbar> -->
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        isCollapse: false,
      };
    },
    methods: {
      toggleCollapse() {
        this.isCollapse = !this.isCollapse
      },
    },
  }

</script>

<style scoped="scoped">
  /*scoped作用：控制style内css的作用域就是当前这个组件*/
  /*引入私有css*/
  /*@import url("../assets/css/NavBar.css");*/
  /*菜单图标与文本的间距*/
  .el-icon-location {
    margin-right: 10px;
  }
</style>
<style>
  /*一级菜单 悬停  覆盖el-submenu__title原有样式*/
  .el-submenu__title:hover {
    /*悬停背景颜色*/
    background-color: #33B35A !important;
    /*其它颜色*/
    color: #FFFFFF !important;
    outline: 0 !important;
  }

</style>
