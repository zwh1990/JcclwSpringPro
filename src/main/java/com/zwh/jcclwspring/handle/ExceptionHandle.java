package com.zwh.jcclwspring.handle;

import com.zwh.jcclwspring.domain.Result;
import com.zwh.jcclwspring.exception.LuckymoneyException;
import com.zwh.jcclwspring.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author by zwh
 * @description:异常捕获
 * @date Created in 2021/8/26 10:04
 */
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception exception) {
        if (exception instanceof LuckymoneyException) {
            LuckymoneyException luckymoneyException = (LuckymoneyException) exception;
            return ResultUtil.fail(luckymoneyException.getCode(), luckymoneyException.getMessage());
        } else {
            return ResultUtil.fail(1, exception.getMessage());
        }
    }

}
