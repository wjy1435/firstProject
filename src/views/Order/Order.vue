<template>

    <div class="main">
        <div class="list" v-if="orders.length >0">
            <div class="box" v-for="(item,indexs) in orders" :key="indexs">
                <router-link :to="{path:'CourseDetail',query:{video_id:item.video_id}}">
                    <div class="smallbox">
                        <div class="imgdiv">
                            <img :src="item.videoImg" alt="小滴课堂 课程图片">
                        </div>
                        <div class="textdiv">
                            <p class="title">{{item.video_title}}</p>
                            <p class="price">{{item.total_fee/100}}</p>
                        </div>
                    </div>
                </router-link>
            </div>
        </div>
        <div class="no_order" v-else>
            <p>暂没购买课程</p>
        </div>
<!--   底部导航     -->
        <common-footer></common-footer>
    </div>

</template>

<script>
    import CommonFooter from '../../components/CommonFooter';
    import {getOrderListApi} from "../../api/api";

    export default {
        name: "Order",
        components: {
            CommonFooter
        },
        data() {
            return {
                orders: {}
            }
        },
        methods: {
            async getOrdreList() {
                try {
                    const result = await getOrderListApi(this.$store.state.token)
                    if (result.data.code == 0) {
                        this.orders = result.data.data || []
                    }
                } catch (error) {
                    console.log(error)
                }
            }
        },
        mounted() {
            this.getOrdreList();
        }
    }
</script>

<style lang="scss" scoped>
    .list {
        padding: 0 20px;
    }

    // 视频个体
       .box {
           padding: 20px 0;
           background-color: #fff;
           border-bottom: 1px solid #ddd;
    // 标题
       .title {
           font-size: 14px;
           margin-bottom: 15px;
       }
    // 订单详情
       .smallbox {
       //flex左右排列，两端对齐
       display: flex;
           justify-content: space-between;
    .imgdiv {
        width: 90px;
        height: 60px;
        flex-shrink: 0;
    img {
        width: 100%;
        height: 100%;
        border-radius: 10px;
    }
    }
    .textdiv p {
        margin-top: 10px;
        padding-left: 10px;
    }
    }
    }

    .no_order {
        margin-top: 50px;
        text-align: center;
    }
</style>
