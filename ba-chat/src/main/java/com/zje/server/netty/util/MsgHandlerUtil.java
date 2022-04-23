package com.zje.server.netty.util;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSON;
import com.dcy.common.dto.msg.BaseMsg;
import com.dcy.common.dto.msg.MsgEvent;
import com.zje.server.mq.MsgProducer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/7/28 8:58
 */
@Slf4j
public class MsgHandlerUtil {

    /**
     * 处理 私聊发送消息,处理发送群组消息
     *
     * @param text
     */
    public static void handlerSendMsg(String text) {
        // TODO 离线消息处理  发送消息体缓存本地 + 远程DB
        // 集群版
        MsgProducer producer = SpringUtil.getBean(MsgProducer.class);
        producer.sendMsg(text);
    }

    /**
     * 处理心跳事件
     *
     * @param ctx
     */
    public static void handlerSendPongMsg(ChannelHandlerContext ctx) {
        BaseMsg pongMsg = new BaseMsg();
        pongMsg.setEvent(MsgEvent.PONG);
        ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(pongMsg))).addListeners(future -> {
            if (!future.isSuccess()) {
                log.error("IO error,close Channel");
                ctx.close();
            }
        });;
    }
}
