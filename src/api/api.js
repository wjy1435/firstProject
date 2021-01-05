import axios from '../request'



//注册接口
export const registerApi = (name, pwd, phone) => axios.post('/api/v1/pri/user/register', {
    "name": name,
    "pwd": pwd,
    "phone": phone
})

// 登录接口
export const LoginrApi = (phone,pwd) => axios.post('/api/v1/pri/user/login', {
    "phone": phone,
    "pwd": pwd,
})
//轮播图接口
export const getBannerApi = () => axios.get('/api/v1/pub/video/listBanner')

//视频列表接口
export const getVideoListApi = () => axios.get('/api/v1/pub/video/list')

//视频详情接口
export const findDetailByIdApi = (vid) => axios.get('/api/v1/pub/video/findDetailById', {
    params: {
        video_id: vid
    }
})

//下单接口
export const setOrderApi = (token, vid) => axios.post('api/v1/pri/order/save', {
    "video_id": vid
}, {
    headers: {
        "token": token
    }
})

// 订单列表接口
export const getOrderListApi = (token) => axios.get('/api/v1/pri/order/list', {
    params: {
        "token": token
    }
})
// 个人信息接口
export const getUserInfoApi = (token) => axios.get('/api/v1/pri/user/findUserById', {
    params: {
        "token": token
    }
})
