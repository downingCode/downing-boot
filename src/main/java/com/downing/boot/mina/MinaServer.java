package com.downing.boot.mina;

import com.downing.boot.mina.handler.ServerMessageHandler;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author downing
 * @desc
 * @date 2020/8/3 15:14
 */
public class MinaServer {

    public static void main(String[] args) throws IOException {
        //创建SocketAcceptor，监听连接进来的连接
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());//添加Log过滤器
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));//添加编码过滤器
        acceptor.setHandler(new ServerMessageHandler());//设置业务逻辑处理类
        acceptor.getSessionConfig().setReadBufferSize(2048);//设置读取数据的缓冲区
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        acceptor.bind(new InetSocketAddress(9999));//绑定监听的端口号
    }
}
