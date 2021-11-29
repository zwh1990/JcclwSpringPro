package com.zwh.jcclwspring.domain;

/**
 * @author by zwh
 * @description:请求返回的格式
 * @date Created in 2021/8/25 16:30
 */
public class Result <T>{
    /**
     * 标识码 0 代表成功， 1代表失败
     */
    private Integer code;

    /**
     * 信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
