package com.downing.boot.mina;

import com.downing.boot.mina.handler.ClientMessageHandler;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;

/**
 * @author downing
 * @desc  连接器
 * @date 2020/8/3 15:15
 */
public class MinaConnector {
    private static final Logger logger = LoggerFactory.getLogger(MinaConnector.class);

    private NioSocketConnector connector = null;
    private IoHandler handler = new ClientMessageHandler();
    private IoSession session;

    private SocketAddress socketAddress = new InetSocketAddress("127.0.0.1",9999);

    public void connector(){
        connector = new NioSocketConnector();
        DefaultIoFilterChainBuilder chain = connector.getFilterChain();
        chain.addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));//添加编码过滤器
        connector.setHandler(handler);
        ConnectFuture cf = connector.connect(socketAddress);
        cf.awaitUninterruptibly();
        session = cf.getSession();
    }

    /**
     * 发送消息
     * @param message
     */
    public void send(String message){
        session.write(message);
    }
}
