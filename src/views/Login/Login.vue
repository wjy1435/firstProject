<template>
    <div class="main">
        <cube-form :model="model" @submit="submitHandler">
            <cube-form-group>
                <!--            手机号-->
                <cube-form-item :field="fields[0]"></cube-form-item>
                <!--            密码-->
                <cube-form-item :field="fields[1]"></cube-form-item>
            </cube-form-group>

            <cube-form-group>
                <cube-button type="submit">登录</cube-button>
            </cube-form-group>

        </cube-form>
        <router-link to="/Register" class="reg">
            注册
        </router-link>
    </div>

</template>

<script>
    import * as api from '../../api/api'

    export default {
        name: "Register",
        data() {
            return {
                model: {
                    phoneValue: "",
                    pwdValue: "",
                },
                fields: [
                    {
                        type: 'input',
                        modelKey: 'phoneValue',
                        label: '手机号',
                        props: {
                            placeholder: '请输入手机'
                        },
                        rules: {
                            required: true
                        },
                        message: {
                            required: '手机号不能为空'
                        }
                    },
                    {
                        type: 'input',
                        modelKey: 'pwdValue',
                        label: '密码',
                        props: {
                            placeholder: '请输入密码',
                            type: 'password',
                            eye: {
                                open: false
                            }
                        },
                        rules: {
                            required: true
                        },
                        message: {
                            required: '密码不能为空'
                        }
                    }
                ]
            }
        },
        methods:{
            submitHandler(e,model){
                e.preventDefault();
                api.LoginrApi(model.phoneValue,model.pwdValue).then(
                    res=>{
                        if (res.data.code === 0) {
                            //登录成功
                            console.log( '00000')
                            localStorage.setItem('token',res.data.data)
                            //
                            this.$store.dispatch('setToken',res.data.data);
                            this.$router.push({path:'/'})
                        }else{
                            const toast = this.$createToast({
                                txt: "登录失败",
                                type: "error",
                                time: 1500
                            });
                            toast.show();
                        }
                    }
                )
            }
        }
    }
</script>

<style lang="scss" scoped>
    .main {
        padding: 50px 5% 0;
        text-align: center;
    }
    // 登录
    .cube-btn {
        margin-top: 20px;
    }
    //注册
    .reg {
        display: inline-block;
        margin-top: 30px;
        font-size: 18px;
    }
</style>
