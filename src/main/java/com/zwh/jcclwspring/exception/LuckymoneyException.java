package com.zwh.jcclwspring.exception;

import com.zwh.jcclwspring.enums.LuckyEnum;

/**
 * @author by zwh
 * @description:
 * @date Created in 2021/8/26 10:46
 */
public class LuckymoneyException extends RuntimeException{

    private int code;

    public LuckymoneyException(LuckyEnum luckyEnum) {
        super(luckyEnum.getMsg());
        this.code = luckyEnum.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
