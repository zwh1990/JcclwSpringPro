package com.zwh.jcclwspring.utils;

import com.zwh.jcclwspring.domain.Result;

/**
 * @author by zwh
 * @description:返回数据工具类
 * @date Created in 2021/8/25 16:42
 */
public class ResultUtil {

    public static Result success(Object object){
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success(){
        return success(null);
    }

    public static Result fail(int code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
