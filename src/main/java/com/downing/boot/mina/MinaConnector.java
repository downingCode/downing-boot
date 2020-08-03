package com.downing.boot.mina;

import com.downing.boot.mina.handler.MessageSessionHandler;
import org.apache.mina.core.service.IoHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author downing
 * @desc  连接器
 * @date 2020/8/3 15:15
 */
public class MinaConnector {
    private static final Logger logger = LoggerFactory.getLogger(MinaConnector.class);

    private IoHandler handler = new MessageSessionHandler();
}
