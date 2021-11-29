package com.zwh.jcclwspring.enums;

/**
 * @author by zwh
 * @description:
 * @date Created in 2021/8/26 10:54
 */
public enum LuckyEnum {

    LARGE_MONEY(  1, "这是一个大红包"),
    MIDDLE_MONEY( 1,"这个红包很一般"),
    LITTLE_MONEY( 1,"这是一个小红包");

    private int code;
    private String msg;

    LuckyEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
