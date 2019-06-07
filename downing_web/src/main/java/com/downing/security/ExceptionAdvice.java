package com.downing.security;

import com.downing.common.DowningResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author downing
 * @descript 统一异常处理，所有抛出的运行时异常都会被拦截到这里
 */
@ControllerAdvice
public class ExceptionAdvice {

    /**
     * 逻辑业务异常
     */
    @ExceptionHandler(LogicException.class)
    public ResponseEntity<DowningResult> handleException(LogicException exception) {
        //TODO 日志记录
        return ResponseEntity.ok(new DowningResult(exception.getCode(), exception.getMessage()));
    }

    /**
     * 所有业务异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DowningResult> handleException(Exception exception) {
        return ResponseEntity.ok(new DowningResult(-500, exception.getMessage()));
    }


    /**
     * 获取堆栈信息
     *
     * @param aThrowable
     * @return
     */
    public String getPrintStackTrace(Throwable aThrowable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        aThrowable.printStackTrace(pw);
        return sw.toString();
    }
}
