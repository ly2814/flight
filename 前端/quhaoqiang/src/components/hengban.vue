<template>
    <div>
        <TitleVue></TitleVue>
        <el-table :data="tableData" style="width: 100%">
            <el-table-column prop="flightnum" label="航班号" width="150">
            </el-table-column>
            <el-table-column prop="flydate" label="飞行日期" width="150">
            </el-table-column>
            <el-table-column prop="starttime" label="发出时间" width="150">
            </el-table-column>
            <el-table-column prop="flytime" label="飞行时间" width="150">
            </el-table-column>
            <el-table-column prop="startcity.cityname" label="出发城市" width="150">
            </el-table-column>
            <el-table-column prop="endcity.cityname" label="目的城市" width="150">
            </el-table-column>
            <el-table-column prop="seatnum" label="座位数目" width="150">
            </el-table-column>
            <el-table-column label="操作" width="140">
                <template slot-scope="scope">
                    <el-button size="mini"  @click="handleEdit(scope.$index, scope.row), dialogFormVisibl" icon="el-icon-edit" type="primary"></el-button>
                    <el-dialog title="修改信息" :visible.sync="dialogFormVisible">
                        <el-form :model="form">
                            <el-form-item label="航班号" :label-width="formLabelWidth">
                                <el-input v-model="form.flightnum"></el-input>
                            </el-form-item>
                            <el-form-item label="飞行日期" :label-width="formLabelWidth">
                                <el-input v-model="form.flydate" autocomplete="off" type="date"></el-input>
                            </el-form-item>
                            <el-form-item label="发出时间" :label-width="formLabelWidth">
                                <el-input v-model="form.starttime" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="飞行时间" :label-width="formLabelWidth">
                                <el-input v-model="form.flytime" autocomplete="off"></el-input>
                            </el-form-item>
                            <el-form-item label="始发地" :label-width="formLabelWidth">
                                <el-select v-model="form.startcity" placeholder="请选择始发地">
                                    <div v-for="item in list">
                                        <el-option :value="item.cityname"></el-option>
                                    </div>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="目的地" :label-width="formLabelWidth">
                                <el-select v-model="form.endcity" placeholder="请选择目的地">
                                    <div v-for="item in list">
                                        <el-option :value="item.cityname"></el-option>
                                    </div>

                                </el-select>
                            </el-form-item>
                            <el-form-item label="座位数" :label-width="formLabelWidth">
                                <el-input v-model="form.seatnum" autocomplete="off"></el-input>
                            </el-form-item>
                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click.stop="dialogFormVisible = false">取 消</el-button>
                            <el-button type="primary" @click.stop="show()">确 定</el-button>
                        </div>
                    </el-dialog>
                    <el-button size="mini" type="danger" @click="open(scope.$index, scope.row)" icon="el-icon-delete-solid"></el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="block">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page.sync="currentPage1" :page-size="params.size" layout="total, prev, pager, next"
                :total="total">
            </el-pagination>
        </div>
    </div>
</template>
  
<script>
import TitleVue from "./Title.vue";
import axios from "axios";
import { mapState } from "vuex";
export default {
    components:{
        TitleVue
    },
    computed: {
        ...mapState(["list"]),
    },
    data() {
        return {
            id1: "",
            di2: "",
            tableData: [
            ],
            currentPage1: 1,
            total: 0,
            params: {
                page: 1,
                size: 10
            },
            flightid: "",
            dialogFormVisible: false,
            form: {
                flightid2: "",
                flightnum: '',
                flydate: "",
                starttime: "",
                flytime: "",
                startcity: '',
                endcity: '',
                seatnum: "",
                type: [],
            },
            formLabelWidth: '120px'
        }
    },
    mounted() {
        this.getDate()
    },
    methods: {
        getDate() {
            axios.get('http://localhost:8080/api/info?page=' + this.params.page + '&pageSize=' + this.params.size).then(res => {
                this.tableData = res.data.object.content
                console.log(res.data)
                this.total = res.data.object.totalElements

            })
        },
        getinfoSucc(res) {
            this.tableData = res.data.object.content
            this.total = res.data.sumcontent
            console.log(res)
        },
        handleSizeChange(val) {
            //获取页数
            console.log(`每页 ${val} 条`);
            this.params.size = val;
            //在进行一次访问请求
        },
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.params.page = val;
            this.getDate()
        },
        handleEdit(index, row) {
            console.log(row);
            this.dialogFormVisible = true;

            this.form.flightid2 = row.flightid;
            this.form.flightnum = row.flightnum;
            this.form.flydate = row.flydate
            this.form.starttime = row.starttime;
            this.form.flytime = row.flytime
            this.form.startcity = row.startcity.cityname;
            this.form.endcity = row.endcity.cityname;
            this.form.seatnum = row.seatnum
        },
        handleDelete2(flightid) {
            axios.delete("http://localhost:8080/api/deleteById?id=" + flightid).then(res => {
                if (res.data > 0) {
                    setTimeout(() => {
                        location.reload()
                    }, 5000)
                }
            })
        },
        open(index, row) {
            this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$message({
                    type: 'success',
                    message: '删除成功!'
                });
                this.handleDelete2(row.flightid)
                location.reload();
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        },
        show() {
            if (this.form.startcity == this.form.endcity) {
                this.$message('出发地和目的地相同更新失败');
                return
            }
            for (var a = 0; a < this.list.length; a++) {
                // console.log(this.list)
                if (this.list[a].cityname == this.form.startcity) {
                    //  alert(this.list[a].cityid)
                    this.id1 = this.list[a].cityid
                }
                if (this.list[a].cityname == this.form.endcity) {
                    this.id2 = this.list[a].cityid
                }
            }
            this.dialogFormVisible = false
            axios.put("http://localhost:8080/api/update", {
                "flightid": this.form.flightid2,
                "flightnum": this.form.flightnum,
                "flydate": this.form.flydate,
                "starttime": this.form.starttime,
                "flytime": this.form.flytime,
                "startcity": {
                    "cityid": this.id1
                },
                endcity: {
                    "cityid": this.id2
                },
                "seatnum": this.form.seatnum
            })
                .then(res => {
                    if (res.data.clode> 0) {
                        this.$message({
                            message: '更新成功',
                            type: 'success'
                        });
                        location.reload()
                    }
                    else {
                        this.$message.error('更新失败');
                    }
                })
                .catch(Error => {
                    console.log(Error)
                })
        }

    }
}
</script>
<style scoped>
.btn1 {
    font-size: 10px;
}
</style>