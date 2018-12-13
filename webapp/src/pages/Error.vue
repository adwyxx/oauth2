<template>
  <el-row>
    <el-col :span="6">
      <div class="grid-content"></div>
    </el-col>
    <el-col :span="12">
      <div>
        <el-row>
          <el-col :span="24">
            <router-link :to="{path: '/login'}">
              <img :src="imagePath">
            </router-link>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-collapse accordion>
              <el-collapse-item>
                <template slot="title">
                  <i class="header-icon el-icon-info"></i>
                  错误消息：{{message}}
                </template>
                <div>{{stack}}</div>
              </el-collapse-item>
            </el-collapse>
          </el-col>
        </el-row>
      </div>
    </el-col>
    <el-col :span="6">
      <div class="grid-content"></div>
    </el-col>
  </el-row>
</template>
<style>
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>

<script>
export default {
  name: 'Error',
  data() {
    return {
      status: 404,
      message: '哎呀，网页出错了',
      stack: '',
      imagePath: './static/images/404.gif'
    }
  },
  created() {
    if (this.$route.params) {
      this.message = this.$route.params.message
      this.stack = this.$route.params.stack
    }
  }
}
</script>
