package com.example.util;


// 接口返回 工具类
public class JsonData {

    /**
     * 状态码   0 成功  -1 失败
     */
    private int code;
    /**
     * 业务数据
     */
    private Object data; //  业务数据
    /**
     * 信息
     */
    private String msg; //信息

    public JsonData() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JsonData(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
    public JsonData(int code, Object data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public JsonData(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    //成功

    /**
     *
     * @return
     */
    public static JsonData buildSuccess(){
        return  new JsonData(0,null,null);
    }
    public static JsonData buildSuccess(Object data){
        return  new JsonData(0,data,null);
    }

    /**
     * 失败   固定状态码
     * @param msg
     * @return
     */
    public static JsonData buildError(String msg){
        return  new JsonData(-1,msg);
    }

    /**
     * 失败自定义状态码
     * @param msg
     * @param code
     * @return
     */
    public static JsonData buildError(int code,String msg){
        return  new JsonData(code,null,msg);
    }

}
