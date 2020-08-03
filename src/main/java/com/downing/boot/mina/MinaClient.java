package com.downing.boot.mina;

import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * @author downing
 * @desc
 * @date 2020/8/3 15:14
 */
public class MinaClient {

    public static void main(String[] args) {
        MinaConnector connector = new MinaConnector();
        connector.connector();
        connector.send("ping");
    }
}
