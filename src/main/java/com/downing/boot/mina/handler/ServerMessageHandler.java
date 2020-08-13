package com.downing.boot.mina.handler;

import com.downing.boot.mina.pojo.AbstractData;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * @author downing
 * @desc
 * @date 2020/8/3 15:02
 */
public class ServerMessageHandler extends IoHandlerAdapter {

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        cause.printStackTrace();
        super.exceptionCaught(session, cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        AbstractData abstractData = (AbstractData) message;
        System.out.printf("主协议："+abstractData.getMainType());
        System.out.printf("子协议："+abstractData.getSubType());
        session.write("pong");
        super.messageReceived(session, message);
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
    }
}
