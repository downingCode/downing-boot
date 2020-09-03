package com.downing.boot.mina.pojo;

/**
 * @author downing
 * @desc
 * @date 2020/8/3 17:43
 */
public class ResultData extends AbstractData{
    //是否成功处理
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ResultData(byte mainType, byte subType) {
        super(mainType, subType);
    }
}
