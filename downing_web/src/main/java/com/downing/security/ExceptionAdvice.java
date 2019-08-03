package com.downing.security;

import com.downing.common.DowningResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author downing
 * @descript 统一异常处理，所有抛出的运行时异常都会被拦截到这里
 */
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * 逻辑业务异常
     */
    @ExceptionHandler(value = LogicException.class)
    public DowningResult handleException(LogicException exception) {
        //TODO 日志记录
        return new DowningResult(exception.getCode(), exception.getMessage());
    }

    /**
     * 逻辑安全异常
     */
    @ExceptionHandler(AuthorizedException.class)
    public ResponseEntity<DowningResult> handleException(AuthorizedException exception) {
        return ResponseEntity.ok(new DowningResult(exception.getCode(), exception.getMessage()));
    }

    /**
     * 所有业务异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DowningResult> handleException(Exception exception) {
        System.out.println("111111111111");
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
