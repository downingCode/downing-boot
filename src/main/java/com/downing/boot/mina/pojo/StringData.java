package com.downing.boot.mina.pojo;

import com.downing.boot.mina.constant.ProtocolConstant;
import lombok.Data;

/**
 * @author downing
 * @desc
 * @date 2020/8/3 16:52
 */
@Data
public class StringData extends ResultData{

    private String message;

    public StringData() {
        super(ProtocolConstant.MAIN, ProtocolConstant.SUB);
        this.message = message;
    }

    public StringData(byte mainType, byte subType, String message) {
        super(ProtocolConstant.MAIN, ProtocolConstant.SUB);
        this.message = message;
    }
}
