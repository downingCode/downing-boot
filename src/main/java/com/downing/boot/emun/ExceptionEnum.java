package com.downing.boot.emun;

/**
 * @author downing
 * @desc
 * @date 2020/7/22 16:39
 */
public enum ExceptionEnum {

    SUCCESS(200, "操作成功"),
    SIGNATURE_NOT_MATCH(400, "请求的数字签名不匹配!"),
    AUTHORIZATION_FAIL(401, "认证失败"),
    AUTHORIZED_FAIL(403, "权限不足!"),
    NOT_FOUND(404, "未找到该资源!"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    SERVER_BUSY(503, "服务器正忙，请稍后再试!");

    /**
     * 错误码
     */
    private Integer resultCode;

    /**
     * 错误描述
     */
    private String resultMsg;

    ExceptionEnum(Integer resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
