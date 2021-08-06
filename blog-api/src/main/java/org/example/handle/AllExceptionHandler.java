package org.example.handle;

import org.example.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/***
 * @Description: "统一异常处理"
 * @Author: ZBZ
 * @Date: 2021/8/6
 */

@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result doExceptionHandler(Exception e) {
        e.printStackTrace();
        return Result.fail(-999, "系统异常");
    }
}
