package com.downing.boot.mina;

import com.downing.boot.mina.pojo.StringData;

import java.net.InetSocketAddress;

/**
 * @author downing
 * @desc
 * @date 2020/8/3 15:14
 */
public class MinaClient {

    public static void main(String[] args) {
        MinaConnector connector = new MinaConnector(new InetSocketAddress("127.0.0.1", 9999));
        connector.connect();
        if (connector.isValid()) {
            StringData stringData = new StringData();
            stringData.setMessage("ping");
            connector.send(stringData);
            //获取存储的返回消息
        } else {
            System.out.printf("连接失败");
        }
    }
}
