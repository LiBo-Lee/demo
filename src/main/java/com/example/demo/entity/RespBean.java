package com.example.demo.entity;

/**
 * @Description:返回信息的实体类
 * @Author：LB
 * @Date：2023年2月15日
 */
public class RespBean {
    private boolean result;
    private Integer status;
    private String msg;
    private Object data;

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public RespBean() {
    }

    public RespBean(boolean result, Integer status, String msg, Object data) {
        this.result = result;
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static RespBean build(){
        return new RespBean();
    }

    public static RespBean ok(String msg){
        return new RespBean(true,200, msg, null);
    }

    public static RespBean ok(String msg, Object data){
        return new RespBean(true, 200, msg, data);
    }

    public static RespBean error(String msg){
        return new RespBean(false, 500, msg, null);
    }

    public static RespBean error(String msg, Object data){
        return new RespBean(false,500, msg, data);
    }
}
