<template>
  <div class="dialog" v-show="showMask">
    <div class="dialog-container">
      <!--标题-->
      <div class="dialog-title">{{title}}</div>
      <!--内容-->
      <div class="content" v-html="content"></div>
      <!--按钮组
      定义type的值,与v-if条件逐一比对
      -->
      <div class="btns">
        <!--确认按钮-->
        <div v-if="type != 'confirm'" class="confirm-btn" @click="confirmBtn">
          {{confirmText}}
        </div>
        <!--取消按钮-->
        <div v-if="type == 'cancel'" class="default-btn" @click="closeBtn">
          {{cancelText}}
        </div>
        <!--危险操作按钮-->
        <div v-if="type == 'danger'" class="danger-btn" @click="dangerBtn">
          {{dangerText}}
        </div>
      </div>
      <!--关闭事件-->
      <div class="close-btn" @click="closeMask"><img src="../assets/button/delete_30px.png"/></div>
    </div>
  </div>
</template>
<script>
  export default {
    props: {
      value: {},
      // 类型包括 defalut 默认， danger 危险， confirm 确认，
      type: {
        type: String,
        // default: ''
      },
      content: {
        type: String,
        default: ''
      },
      title: {
        type: String,
        default: ''
      },
      cancelText: {
        type: String,
        default: '取消'
      },
      dangerText: {
        type: String,
        default: '删除'
      },
      confirmText: {
        type: String,
        default: '确认'
      },
    },
    data() {
      return {
        showMask: false,
      }
    },
    methods: {
      //关闭事件
      closeMask() {
        this.showMask = false;
      },
      //关闭
      closeBtn() {
        this.$emit('cancel');
        this.closeMask();
      },
      //危险
      dangerBtn() {
        this.$emit('danger');
        this.closeMask();
      },
      //确认
      confirmBtn() {
        this.$emit('confirm');
        this.closeMask();
      }
    },
    mounted() {
      this.showMask = this.value;
    },
    watch: {
      value(newVal, oldVal) {
        this.showMask = newVal;
      },
      showMask(val) {
        this.$emit('input', val);
      }
    },
  }
</script>
<style>
  /*引入指定css*/
  @import url("../assets/css/dialog.css");
</style>
