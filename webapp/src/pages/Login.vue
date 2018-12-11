<template>
<div style="width:400px;margin-left:auto;margin-right:auto;magrin-top:200px;">
    <el-card shadow="hover">
       <el-alert title="系统登陆" type="success" :closable="false" center></el-alert>
        <el-form ref="form" :model="formData" label-width="80px" :rules="rules">
          <el-form-item></el-form-item>
          <el-form-item label="用户名" prop="username">
          <el-input type="input" placeholder="请输入用户名" v-model="formData.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
          <el-input type="password" placeholder="请输入密码" v-model="formData.password"></el-input>
          </el-form-item>
          <el-form-item>
          <el-button type="primary" @click="login()">登陆</el-button>
          <el-button @click="reset()">重置</el-button>
          </el-form-item>
        </el-form>
    </el-card>
</div>
</template>

<script>

export default {
  name: 'Login',
  data () {
    return {
      formData: { username: '', password: '' },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'change' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    login () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.login(this.formData.username, this.formData.password)
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    reset () {
      this.$refs['form'].resetFields()
    }
  }
}
</script>
