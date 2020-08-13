package com.downing.boot.exception;

import com.downing.boot.common.DowningResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author downing
 * @desc 异常拦截处理类
 * @date 2020/7/22 16:10
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public DowningResult exception(Exception e) {
        System.out.printf(getPrintStackTrace(e));
        return new DowningResult(-500, e.getMessage(), null);
    }

    @ExceptionHandler(LogicException.class)
    @ResponseBody
    public DowningResult logicException(LogicException e) {
        System.out.printf(getPrintStackTrace(e));
        return new DowningResult(e.getErrorCode(), e.getMessage(), null);
    }

    public String getPrintStackTrace(Throwable aThrowable){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        aThrowable.printStackTrace(pw);
        return sw.toString();
    }

}
