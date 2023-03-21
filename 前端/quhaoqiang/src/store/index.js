import Vue from "vue"
import Vuex from 'vuex'
import axios from "axios"
// 注册插件
const Show=(store)=>{
    axios.get("http://localhost:8080/city/info").then(getInfoSucc)
    function getInfoSucc(res){
        store.state.list=res.data.object
    }
}
Vue.use(Vuex)
export default  new  Vuex.Store({
    plugins:[Show],
    state:{
        list:[]
    },
    mutations:{
    },
    getters:{
      
    }
})