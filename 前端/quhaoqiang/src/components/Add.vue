<template>
  <div class="add">
    <div class="divt">
      <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign" class="from">
        <el-form-item label="航班号">
          <el-input v-model="formLabelAlign.flightnum"></el-input>
        </el-form-item>
        <el-form-item label="飞行日期">
          <el-input v-model="formLabelAlign.flydate" type="date"></el-input>
        </el-form-item>
        <el-form-item label="起飞时间">
          <el-input v-model="formLabelAlign.starttime"></el-input>
        </el-form-item>
        <el-form-item label="飞行时间">
          <el-input v-model="formLabelAlign.flytime"></el-input>
        </el-form-item>
        <div class="select">
          <el-form-item label="始发地">
            <el-select v-model="formLabelAlign.value" placeholder="请选择始发地">
              <el-option v-for="item in options" :key="item.cityid" :label="item.cityname" :value="item.cityid">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="目的地" class="objective">
            <el-select v-model="formLabelAlign.value1" placeholder="请选择目的地">
              <el-option v-for="item2 in options" :key="item2.cityid" :label="item2.cityname" :value="item2.cityid">
              </el-option>
            </el-select>
          </el-form-item>
        </div>
        <el-form-item label="座位数">
          <el-input v-model="formLabelAlign.seatnum"></el-input>
        </el-form-item>
        <el-form-item class="magbtn">
          <input type="button" value="提交" class="btn1" @click="sumbit2" />
          <input type="reset" value="重置" class="btn2" />
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
import axios from 'axios';
// import {Addflight} from '../requst/api'
export default {
  data() {
    return {
      labelPosition: 'left',
      formLabelAlign: {
        flightnum: '',
        flydate: '',
        starttime: '',
        flytime: '',
        seatnum: '',
        value: '',
        value1: '',
        type: ''
      },
      options: []
    };
  },
  methods: {
    sumbit2() {
      if (this.formLabelAlign.flightnum == "") {
        this.open4("航班号不能为空")
        return;
      }
      if (this.formLabelAlign.flydate == "") {
        this.open4("飞行日期不能为空")
        return;
      }
      if (this.formLabelAlign.starttime == "") {
        this.open4("起飞时间不能为空")
        return;
      }
      if (this.formLabelAlign.flytime == "") {
        this.open4("飞行时间不能为空")
        return;
      }
      if (this.formLabelAlign.value == "") {
        this.open4("始发地不能为空")
        return;
      }
      if (this.formLabelAlign.value1 == "") {
        this.open4("目的地不能为空")
        return;
      }
      if (this.formLabelAlign.seatnum == "") {
        this.open4("座位数不能为空")
        return;
      }
      if (this.formLabelAlign.value == this.formLabelAlign.value1) {
        this.open4("出发地和目的地相同");
        return
      }
      axios.post("http://localhost:8080/api/insert", {
        "flightnum": this.formLabelAlign.flightnum,
        "flydate": this.formLabelAlign.flydate,
        "starttime": this.formLabelAlign.starttime,
        "flytime": this.formLabelAlign.flytime,
        "startcity": {
          "cityid": this.formLabelAlign.value
        },
        "endcity": {
          "cityid": this.formLabelAlign.value1
        },
        "seatnum": this.formLabelAlign.seatnum

      }).then(res => {
        console.log("==========================")
        console.log(res.data.clode)
        console.log(res.data.clode)
          if(res.data.clode==200){
            this.open2()
            location.reload()
          }
        
      })
        .catch(Error => {
          console.log(Error)
        })
    },
    open4(alletr) {
      this.$message.error(alletr);
    },
    open2() {
      this.$message({
        message: '插入成功',
        type: 'success'
      });
    },
    shlow2() {
      axios.get("http://localhost:8080/city/info").then(
        res => {
          console.log(res.data)
          this.options = res.data.object
        }
      )
    }
  },
  mounted() {
    this.shlow2()
  }
}
</script>
<style scoped>
.add {
  width: 100%;
  display: flex;
  justify-content: center;
}

.divt {
  width: 100%;
}

.select {
  display: flex;
}

.objective {
  margin-left: 100px;
}

.btn1 {
  background-color: #FF9933;
  color: #fff;
  width: 100px;
  height: 40px;
  border-radius: 3px;
  border: solid 1px #FF9933;
}

.btn2 {
  background-color: #CC6633;
  color: #fff;
  width: 100px;
  height: 40px;
  border-radius: 3px;
  border: solid 1px #CC6633;
  margin-left: 50px;
}

.magbtn {
  display: flex;
 margin-left: 300px;
}

.msg {
  margin-left: 100px;
}

.from {
  margin-left: 50px;
}
</style>
