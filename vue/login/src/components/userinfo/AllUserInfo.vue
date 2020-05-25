<template>
  <div>
    <el-table :data="tableData" style="width: 100%; height=100%;font-size: medium;">
      <el-table-column prop="userid" label="用户ID" width="250">
      </el-table-column>
      <el-table-column prop="username" label="用户昵称" width="200">
      </el-table-column>
      <el-table-column prop="userpwd" label="用户密码" width="200">
      </el-table-column>
      <el-table-column prop="useremail" label="用户邮箱" width="200">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="primary" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <!--删除气泡框-->
          <el-popconfirm confirmButtonText='确定' confirmButtonType="danger" cancelButtonText='取消' cancelButtonType="info"
            icon="el-icon-info" iconColor="red" title="确定删除这条用户信息吗？" @onConfirm="DelPopSubmit(scope.row)">
            <el-button slot="reference" type="danger">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!-- 编辑当前行信息 -->
    <el-dialog title="用户信息编辑(用户名必须改变)" :visible.sync="dialogFormVisible" append-to-body="true" width="35%" center>
      <el-form :model="formData" label-position="left" size="medium" label-width="100px">
        <el-form-item label="用户ID(只读)" :label-width="formLabelWidth">
          <!-- :disabled="true" 禁用  readonly="true" 只读-->
          <el-input id="userid" v-model="formData.form_userid" autocomplete="off" readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="用户昵称" :label-width="formLabelWidth">
          <el-input id="username" v-model="formData.form_username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户密码" :label-width="formLabelWidth" prop="userpass">
          <el-input id="userpwd" v-model="formData.form_userpwd" autocomplete="off" type="password"></el-input>
        </el-form-item>
        <!--:label-width="formLabelWidth"-->
        <el-form-item label="用户邮箱" :label-width="formLabelWidth">
          <el-input id="useremail" v-model="formData.form_useremail" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <span>点击确定后,更新成功时数据会实时刷新,更新异常将会在弹窗关闭后弹出</span>
        </el-form-item>
        <el-form-item>
          <el-button type="info" @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="warning" @click="dialogEdit">确 定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [],
        formData: {
          form_userid: '',
          form_username: '',
          form_userpwd: '',
          form_useremail: ''
        },
        // tableData: Array(60).fill(item),
        //弹窗嵌套控制
        dialogFormVisible: false
      }
    },
    methods: {
      /*修改信息_传值_触发弹窗*/
      handleEdit(index, row) {
        /*获取数据,插入弹窗form输入框*/
        this.formData.form_userid = row.userid;
        this.formData.form_username = row.username;
        this.formData.form_userpwd = row.userpwd;
        this.formData.form_useremail = row.useremail;
        /*打开弹窗*/
        this.dialogFormVisible = true;
      },
      /*气泡确认_传入行信息*/
      DelPopSubmit(row) {
        /*根据Id删除信息*/
        this.$axios.post("/userinfo/deleteUserById", {
          userid: row.userid
        }).then(DeleteUserById => {
          if (DeleteUserById.data.code === 200) {
            // alert(DeleteUserById.data.message);
            this.$message.success({duration: 1300,message: DeleteUserById.data.message});
          } else {
            // alert(DeleteUserById.data.message);
            this.$message.success({duration: 1300,message: DeleteUserById.data.message});
          }
        }).catch(failResponse => {})
      },
      /*弹窗确认_提交数据*/
      dialogEdit() {
        this.dialogFormVisible = false;
        /*调用修改的接口*/
        this.$axios.post("/userinfo/updateUserInfoById", {
          /*通过Dom获取特定的值--弹窗form*/
          userid: document.getElementById("userid").value,
          username: document.getElementById("username").value,
          userpwd: document.getElementById("userpwd").value,
          useremail: document.getElementById("useremail").value
        }).then(UpdateUserInfo => {
          if (UpdateUserInfo.data.code === 200) {
            /*成功*/
            // alert(UpdateUserInfo.data.message);
            this.$message({
              type: 'success',
              duration: 1300,
              message: UpdateUserInfo.data.message,
            });
          } else {
            /*失败*/
            // alert(UpdateUserInfo.data.message);
            this.$message({
              type: 'error',
              duration: 1300,
              message: UpdateUserInfo.data.message,
            });
          }
        }).catch(failResponse => {})
      }
    },
    beforeCreate: function() { //页面创建时
      this.$axios.post("/userinfo/findAll", {}).then(FindAllUserInfo => {
        if (FindAllUserInfo.data.code === 200) {
          //后台返回数据已经是JSON格式,可以直接绑定到el-table中,使用JSON.stringify会将JSON格式的值转换为String,el-table读不到数据
          this.tableData = FindAllUserInfo.data.data;
        }
      }).catch();
    },
    beforeUpdate: function() { //数据更新时
      this.$axios.post("/userinfo/findAll", {}).then(FindAllUserInfo => {
        if (FindAllUserInfo.data.code === 200) {
          //后台返回数据已经是JSON格式,可以直接绑定到el-table中,使用JSON.stringify会将JSON格式的值转换为String,el-table读不到数据
          this.tableData = FindAllUserInfo.data.data;
        }
      }).catch(failResponse => {});
    }
  }
</script>

<style>
</style>
