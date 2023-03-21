<template>
  <div class="div2">
    <div class="title">始发地：</div>
    <el-select v-model="value5" placeholder="请选择始发地城市" @click="shiw()" style="width:160px">
      <el-option v-for="item in options" :multiple-limit="1" :key="item.cityid" :label="item.cityname"
        :value="item.cityid">
      </el-option>
    </el-select>
    <div class="title">目的地:</div>
    <el-select v-model="value11" :multiple-limit="1" collapse-tags style="margin-left: 0px;width:160px"
      placeholder="请选择目的城市">
      <el-option v-for="item in options" :key="item.cityid" :label="item.cityname" :value="item.cityid">
      </el-option>
    </el-select>
    <el-button type="primary" icon="el-icon-search" class="left" @click="show2()">搜索</el-button>
    <el-dialog title="航班信息" :visible.sync="dialogTableVisible">
  <el-table :data="gridData2">
    <el-table-column property="flightnum" label="航班号" ></el-table-column>
    <el-table-column property="flydate" label="出发日期"></el-table-column>
    <el-table-column property="starttime" label="起飞时间"></el-table-column>
    <el-table-column property="flytime" label="飞行时间"></el-table-column>
    <el-table-column property="startcity.cityname" label="始发地"></el-table-column>
    <el-table-column property="endcity.cityname" label="目的地"></el-table-column>
    <el-table-column property="seatnum" label="座位数"></el-table-column>
  </el-table>
</el-dialog>
  </div>
</template>
<script>
 import axios from 'axios'
// import { Select } from "../../api/api"
export default {
  data() {
    return {
      options: [],
      value5: '',
      value11: '',
      selectcity: { StartCity: '', EndCity: "" },
      gridData2: [],
      dialogTableVisible: false,
    }
  },
  methods: {
    show2() {
      if (this.value11 == this.value5) {
        this.open4("出发城市和目的城市相同")
        return
      }

      this.selectcity.StartCity = this.value5
      this.selectcity.EndCity = this.value11
      axios.get("http://localhost:8080/api/search",{
            params:{
                "startCity":this.value5,
                "endCity":this.value11
            }
        }).then(
            res => {
          if (res.data.code == 404) {
            this.open3()
          }
          else {
            console.log(res.data.object)
            this.gridData2=res.data.object
            this.dialogTableVisible=true
          }
        }
        )
      // Select(this.selectcity).then(
      //   res => {
      //     if (res.data.code == 404) {
      //       this.open3()
      //     }
      //     else {
      //       this.gridData2=res.data.data
      //       this.dialogTableVisible=true
      //     }
      //   }
      // )
    },
    shlow(){
         axios.get("http://localhost:8080/city/info").then(
            res=>{
                console.log(res.data.object)
                this.options=res.data.object
                // this.dialogTableVisible=true
            }
         )
      },
    open4(tal) {
      this.$message.error(tal);
    },
    open3() {
      this.$message({
        message: '没有航班信息',
        type: 'warning'
      });
    },
  },
  mounted(){
    this.shlow()
  }
}
</script>
<style  scoped>
.div2 {
  display: flex;
}

.title {
  height: 40px;
  font-weight: 700;
  width: 70px;
  text-align: center;
  line-height: 40px;
}

.left {
  margin-left: 20px;
}
</style>