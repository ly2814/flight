<template>
    <div>
    <el-tabs :tab-position="tabPosition" style="height: 900px;" >
    <el-tab-pane>  <span slot="label"><i class="el-icon-date"></i> 航班信息</span><Hanglist3></Hanglist3></el-tab-pane>
    <el-tab-pane ><span slot="label"><i class="el-icon-circle-plus-outline"></i> 航班信息</span><HelloWorld></HelloWorld></el-tab-pane>
  </el-tabs>
</div>
</template>
<script>
    import HelloWorld from "./Add.vue"
    import Hanglist3 from "./hengban.vue"
export default {
    components :{
        HelloWorld,
        Hanglist3,
    },
    data() {
        return {
            tabPosition: 'left'
        }
    },
    methods: {
    }
}
</script>
<style>
   .div1{
    text-align: center;
    background-color: honeydew;
   }

</style>