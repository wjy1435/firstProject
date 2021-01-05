import  axios from 'axios'

const  service = axios.create({
    baseURL:"http://106.13.65.83:8081",
    timeout:5000    //配置超时时间
})

export default service
