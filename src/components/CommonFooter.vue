<template>

    <div class="tab">
        <cube-tab-bar v-model="selectedLabelSlots" show-slider inline @click="changHandler">
            <cube-tab v-for="item in tabs" :icon="item.icon" :label="item.label" :key="item.path" :value="item.path">

            </cube-tab>
        </cube-tab-bar>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                selectedLabelSlots: '/',
                tabs: [
                    {
                        label: '首页',
                        icon: 'cubeic-home',
                        path: '/'
                    },
                    {
                        label: '我的订单',
                        icon: 'cubeic-like',
                        path: '/order'
                    },
                    {
                        label: '个人中心',
                        icon: 'cubeic-person',
                        path: '/personal'
                        // path:'register'
                    }]
            }
        },
        methods:{
            changHandler(path){
                //this.$route.path  是当前路由
                if(path !== this.$route.path){
                    this.$router.push(path)
                }
            }
        },
        //函数钩子  created页面渲染成html前调用，初始化某些属性值  然后再渲染成视图
        //          mounted 在模板渲染成html后调用    初始化完成后 再对 html  的dom节点   进行额外的操作
        created() {
            //默认路由选择器    比如 刷新页面  需要重新  进到当前路由
            this.selectedLabelSlots = this.$route.path
        }
    }
</script>

<!--scss 是一种css  预处理语言  scoped 是指 这个 scss 样式  只作用于 当前组件-->
<style lang="scss" scoped>
    .tab {
        position: fixed;
        bottom: 0;
        z-index: 999;
        background-color:#fff;
        width: 100%;
        border-top: 1px solid rgba($color: #000000, $alpha: 0.1);
    }
    .cube-tab_active {
        color: #3bb149;
    }
</style>
