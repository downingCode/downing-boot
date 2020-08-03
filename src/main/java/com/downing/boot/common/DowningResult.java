package com.downing.boot.common;

/**
 * @author downing
 * @desc
 * @date 2020/7/21 18:39
 */
public class DowningResult {

    private Integer code = 200;
    private String message;
    private Object data;

    public DowningResult() {
    }

    public DowningResult(String message) {
        this.message = message;
    }

    public DowningResult(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public DowningResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public DowningResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
