package com.downing.common;

/**
 * @author downing
 * @descript 授权认证异常
 */
public class AuthorizedException extends RuntimeException {

    private Integer code = -401;

    public AuthorizedException(String message) {
        this(message, -401);
    }

    public AuthorizedException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
