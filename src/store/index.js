import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        token: localStorage.getItem("token") || ''
    },
    //同步修改值
    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token
        }
    },
    //异步调用 mutations 的方法
    //利用上下文触发
    //vue 中  this.$store.dispatch 触发 action 里面的定义的方法
    actions: {
        setToken(context,token){
            context.commit('SET_TOKEN',token)
        },
        clearToken(context){
            context.commit('SET_TOKEN','')
        }
    },
    modules: {}
})
