package com.downing.boot.mina.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author downing
 * @desc  传输数据
 * @date 2020/8/3 16:34
 */
@Data
public abstract class AbstractData implements Serializable {

    /**
     * 主协议号
     */
    private byte mainType;

    /**
     * 子协议号
     */
    private byte subType;

    public AbstractData(byte mainType, byte subType) {
        this.mainType = mainType;
        this.subType = subType;
    }
}
