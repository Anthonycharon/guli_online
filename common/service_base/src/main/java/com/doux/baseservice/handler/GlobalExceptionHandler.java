package com.doux.baseservice.handler;


import com.doux.baseservice.exception.MyException;
import com.doux.commonutils.ExceptionUtil;
import com.doux.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;

/**
 * 统一异常处理类
 *
 * @author zt
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception exception) {
        exception.printStackTrace();
        log.error(ExceptionUtil.getMessage(exception));
        return R.error().message("执行了全局异常处理");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("出现ArithmeticException异常");
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public R error(MyException e) {
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
