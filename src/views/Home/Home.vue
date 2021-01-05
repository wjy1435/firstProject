<template>

    <div>
        <home-banner :banners="banners"></home-banner>
            <video-list :videoList="videoList"></video-list>
        <common-footer></common-footer>
    </div>

</template>


<script>

    import HomeBanner from './Component/BannerList';
    import VideoList from './Component/VideoList';
    import CommonFooter from '../../components/CommonFooter';
    import * as api from '../../api/api.js'
    // import {getBannerApi,getVideoListApi} from "@/api/getData.js";
    export default {
        components: {
            HomeBanner,
            VideoList,
            CommonFooter
        },
        //声明数据源
        data() {
            return {
                banners: [],
                videoList: []
            }
        },
        methods: {
            async getBannerData() {
                //获取轮播图数据
                try {
                    const result = await api.getBannerApi(); //等待异步请求
                    console.log(result)
                    if (result.data.code == 0) {
                        this.banners = result.data.data
                    }
                } catch (error) {
                    console.log(error);
                }
            },
            //获取视频列表
            async getVList() {
                const result = await api.getVideoListApi();
                console.log(result)
                if (result.data.code == 0) {
                    this.videoList = result.data.data
                }
            },


        },
        //页面渲染完成 后
        mounted() {
            this.getBannerData();
            this.getVList();
        }

    }
</script>

<style lang="scss" scoped>

</style>
