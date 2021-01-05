<template>

    <div>
<!--        顶部-->
        <detail-header :videoInfo="videoInfo"></detail-header>
<!--        视频介绍-->
        <detail-course :videoInfo="videoInfo"></detail-course>
<!--        视频tab简介-->
        <detail-tab :videoInfo="videoInfo" :chapterList="chapterList"></detail-tab>
<!--        底部  立刻购买-->
<!--        <router></router>-->
        <footer>
            <router-link :to="{path:'/pay',query:{video_id:this.$route.query.video_id}}" class="user_buy">
                <button>立刻购买</button>
            </router-link>
        </footer>
    </div>

</template>

<script>

    import DetailHeader from './Commponent/Header'
    import DetailCourse from './Commponent/Course'
    import DetailTab from './Commponent/Tab'

    import * as api from '../../api/api'

    export default {
        name: "CourseDetail",
        components:{
            DetailHeader,
            DetailCourse,
            DetailTab
        },
        data(){
            return{
                videoInfo:{},
                chapterList:[]
            }
        },
        methods:{
          async  getDetail(vid){
              try {
                 const result = await api.findDetailByIdApi(vid)
                  if(result.data.code == 0){
                        this.videoInfo = result.data.data;
                        this.chapterList = result.data.data.chapter_list
                      console.log(this.videoInfo)
                  }
              } catch (error) {
                  console.log(error)
              }
            }
        },
        mounted() {
            console.log(this.$route.query.video_id);
            this.getDetail(this.$route.query.video_id);
        }
    }
</script>

<style lang="scss" scoped>
    //包裹层
    .c_wrapper {
        padding: 0 14px;
    }
    //视频信息包裹层
    .course {
        margin:14px 0;
        display:flex;//设置flex，左右布局
    }
    //视频左边图片层
    .l_img {
        height:88px;
        margin-right:14px;
        & img {
            height:100%;
            border-radius:15px;
        }
    }
    // 视频右边文字包裹层
    .r_txt {
        padding:6px 0;
        font-size:12px;
        flex:1;//设置1可自动伸缩占用剩余空间
    }
    //每行文字层（综合评分、价格）
    .txt {
        // 设置flex让文字两端对齐
        display:flex;
        justify-content:space-between;
        line-height:16px;
        & p {
            text-align:center;
            width:40%;
            color:#3bb149;
        }
        & i {
            color:#666;
            font-weight:bolder;
            width:60%;
            & span {
                color:#2b333b;
                font-size:12px;
            }
        }
    }
    //底部
    footer {
        // fixed固定在底部
        position: fixed;
        bottom: 0;
        width: 100%;
        padding: 8px 0;
        background-color: #fff;
        z-index: 999;
        box-shadow: 0 -2px 4px 0 rgba(0, 0, 0, 0.05);
    }
    //设置购买按钮样式
    button {
        display: block;
        color: #fff;
        margin: 0 auto;
        background-color: #d93f30;
        height: 34px;
        line-height: 34px;
        border-radius: 17px;
        width: 80%;
        border: none;
        font-size: 15px;
        text-align: center;
    }
</style>
