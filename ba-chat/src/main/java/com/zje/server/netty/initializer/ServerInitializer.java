package com.zje.server.netty.initializer;

import com.zje.server.netty.handler.AuthHandler;
import com.zje.server.netty.handler.MsgServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/7/15 8:56
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //因为是基于http协议，使用http的编码和解码器
        pipeline.addLast(new HttpServerCodec())
                //以块的方式来写的处理器
                .addLast(new ChunkedWriteHandler())
                //聚合器，使用websocket会用到
                .addLast(new HttpObjectAggregator(1024 * 64))
                // WebSocket数据压缩
                .addLast(new WebSocketServerCompressionHandler())
                // 验证token
                .addLast(new AuthHandler())
                // Netty支持websocket
                .addLast(new WebSocketServerProtocolHandler("/ws", null, true))
                //65 秒没有向客户端发送消息就发生心跳
                .addLast(new IdleStateHandler(65, 0, 0))
                // 消息处理器
                .addLast(new MsgServerHandler());
    }
}
