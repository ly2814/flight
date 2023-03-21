<template>
    <div class="divtitle">
        <div>
            <Select2Vue></Select2Vue>
        </div>
       <div class="show">
          <div @click="down()">
            <el-button  class="btn" type="primary">下载<i class="el-icon-download el-icon--right"></i></el-button>
          </div>
       </div>
    </div>
</template>
<script>
import axios from 'axios';
import Select2Vue from './Select2.vue';
export default {
    name: 'WangluyaoTitlie',
    components:{
        Select2Vue
    },
    data() {
      return {
        options:[],
        value5: "",
        value11: ""
      }
    },

    mounted() {
        this.shlow()
    },

    methods: {
      Select2(){ 
          this.$router.push({path:"/Sercher",query:{value:this.value5,value2:this.value11}})
      },
      shlow(){
         axios.get("http://localhost:8080/city/info").then(
            res=>{
                console.log(res.data.object)
                this.options=res.data.object
            }
         )
      },
      down(){
        location.href = 'http://localhost:8080/io/download?filename=航班统计表.xlsx'

    }
    }
};
</script>
<style  scoped>
.divtitle{
    display: flex;
}
    .show{
        display: flex;
        justify-content: center;
    }
    .btn{
        margin-left: 480px;
    }
</style>