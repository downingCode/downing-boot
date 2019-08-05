package com.downing.common;

/**
 * @author downing
 * @descript 自定义异常
 */
public class LogicException extends RuntimeException {

    private Integer code = -1;

    public LogicException(String message) {
        this(message, -1);
    }

    public LogicException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
