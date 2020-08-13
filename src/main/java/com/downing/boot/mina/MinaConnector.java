package com.downing.boot.mina;

import com.downing.boot.mina.handler.ClientMessageHandler;
import com.downing.boot.mina.pojo.AbstractData;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;

/**
 * @author downing
 * @desc  连接器
 * @date 2020/8/3 15:15
 */
public class MinaConnector {
    private static final Logger logger = LoggerFactory.getLogger(MinaConnector.class);
    //连接器
    private NioSocketConnector connector = null;
    //收到服务端消息处理器
    private IoHandler handler = new ClientMessageHandler();
    //会话
    private IoSession session;
    //套接字地址
    private SocketAddress socketAddress;
    protected static int receiveBufferSize = 1024000;
    protected static int sendBufferSize    = 1024000;
    private boolean valid = false;
    public boolean isValid() {
        return this.valid;
    }

    public MinaConnector(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }

    public void connect(){
        connector = new NioSocketConnector();
        ExecutorService filterExecutor = new OrderedThreadPoolExecutor();
        DefaultIoFilterChainBuilder chain = connector.getFilterChain();
        //添加过滤器
        chain.addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));//添加编码过滤器
        chain.addLast("threadPool", new ExecutorFilter(filterExecutor));// 线程
        connector.getSessionConfig().setReceiveBufferSize(receiveBufferSize);
        connector.getSessionConfig().setSendBufferSize(sendBufferSize);

        connector.setHandler(handler);
        ConnectFuture cf = connector.connect(socketAddress);
        cf.awaitUninterruptibly();
        session = cf.getSession();
        valid = true;
    }

    /**
     * 发送消息
     * @param data
     */
    public void send(AbstractData data){
        session.write(data);
    }

    public void setTimeOut(long seconds){
        connector.setConnectTimeoutCheckInterval(seconds);
    }
}
