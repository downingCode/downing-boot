package com.downing.boot.exception;

import com.downing.boot.emun.ExceptionEnum;
import com.downing.boot.entity.DowningResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author downing
 * @desc
 * @date 2020/7/22 15:58
 */
public class LogicException extends RuntimeException{

    private Integer errorCode = -500;

    private String errorMsg;

    public LogicException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public LogicException(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public LogicException(Throwable cause, Integer errorCode, String errorMsg) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public LogicException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getResultMsg());
        this.errorCode = exceptionEnum.getResultCode();
        this.errorMsg = exceptionEnum.getResultMsg();
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
