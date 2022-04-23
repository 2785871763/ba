package com.zje.server.netty.handler;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcy.common.constant.CommonConstant;
import com.dcy.common.dto.msg.BaseMsg;
import com.dcy.common.dto.msg.MsgEvent;
import com.dcy.common.utils.SessionSocketHolder;
import com.zje.server.netty.util.MsgHandlerUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/7/15 8:49
 */
@Slf4j
public class MsgServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static AttributeKey<String> USER_ID = AttributeKey.valueOf("userId");
    public static AttributeKey<List<String>> GROUP_IDS = AttributeKey.valueOf("groupIds");

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE) {
//            String userId = ctx.channel().attr(USER_ID).get();
            ctx.pipeline().remove(AuthHandler.class);
        } else if (evt instanceof IdleStateEvent) {
            // 转换类型
            IdleStateEvent event = (IdleStateEvent) evt;
            switch (event.state()) {
                case READER_IDLE:
                    //读空闲
                    BaseMsg pongMsg = new BaseMsg();
                    pongMsg.setEvent(MsgEvent.PONG);
                    ctx.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(pongMsg)))
                            .addListeners(future -> {
                                if (!future.isSuccess()) {
                                    log.error("IO error,close Channel");
                                    ctx.close();
                                }
                            });
                    break;
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    /**
     * 建立连接
     * 每当从服务端收到新的客户端连接时，客户端的 Channel 存入ChannelGroup列表中，并通知列表中的其他客户端 Channel
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        channelGroup.add(ctx.channel());
        // 添加到全局组
        SessionSocketHolder.put(CommonConstant.ALL_CHANNEL_GROUP_NAME, channelGroup);
        log.info("[服务端] - " + incoming.remoteAddress() + "  加入");
        log.info("[服务端] handlerAdded {} channelGroup 长度", channelGroup.size());
    }

    /**
     * 断开连接
     * 每当从服务端收到客户端断开时，客户端的 Channel 移除 ChannelGroup 列表中，并通知列表中的其他客户端 Channel
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        log.info("[服务端] - " + incoming.remoteAddress() + " 离开");
        unBindUserAndChannel(ctx);
        log.info("[服务端] handlerRemoved {} channelGroup 长度", channelGroup.size());
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //文本消息
        String text = msg.text();
        JSONObject jsonObject = JSON.parseObject(text);
        String event = (String) jsonObject.get("event");
        switch (MsgEvent.valueOf(event)) {
            case P2P:
            case GROUP:
                MsgHandlerUtil.handlerSendMsg(text);
                break;
            case PING:
                MsgHandlerUtil.handlerSendPongMsg(ctx);
                break;
        }
    }


    /**
     * 解绑 用户和 channel的关系
     *
     * @param ctx
     */
    private void unBindUserAndChannel(ChannelHandlerContext ctx) {
        // 清除离线用户
        SessionSocketHolder.remove(ctx.channel());
//        String userId = ctx.channel().attr(USER_ID).get();
        // 处理组中的人员
        List<String> groupIds = ctx.channel().attr(GROUP_IDS).get();
        if (CollUtil.isNotEmpty(groupIds)) {
            for (String groupId : groupIds) {
                // 得到组中有多少人
                ChannelGroup curChannelGroup = SessionSocketHolder.getGroup(groupId);
                if (curChannelGroup.size() == 0) {
                    SessionSocketHolder.remove(groupId);
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel incoming = ctx.channel();
        log.info("[客户端] {} 异常", incoming.remoteAddress());
        cause.printStackTrace();
        ctx.close();
    }


}
