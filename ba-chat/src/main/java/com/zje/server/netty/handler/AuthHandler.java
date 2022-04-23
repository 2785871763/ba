package com.zje.server.netty.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.dcy.common.dto.msg.MsgEvent;
import com.dcy.common.dto.msg.MsgType;
import com.dcy.common.dto.msg.BaseMsg;
import com.dcy.common.utils.JwtUtil;
import com.dcy.common.utils.SessionSocketHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @Author：dcy
 * @Description: 验证websocket 是否合法
 * @Date: 2020/7/24 9:35
 */
@Slf4j
public class AuthHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    public static AttributeKey<String> USER_ID = AttributeKey.valueOf("userId");
    public static AttributeKey<List<String>> GROUP_IDS = AttributeKey.valueOf("groupIds");

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        String uri = request.uri();
        if (uri.contains("/ws")) {
            List<String> strs = StrUtil.split(uri, '?');
            if (strs.size() >= 2) {
                // 获取token 验证token
                String token = JwtUtil.validateToken(strs.get(1));
                if (StrUtil.isNotBlank(token)) {
                    Map map = JSON.parseObject(token, Map.class);
                    String userId = MapUtil.get(map, "id", String.class);
                    List<String> groupIds = MapUtil.get(map, "groupIds", List.class);
                    ctx.channel().attr(USER_ID).set(userId);
                    ctx.channel().attr(GROUP_IDS).set(groupIds);
                    if (SessionSocketHolder.get(userId) == null) {
                        // 绑定用户和 channel的关系
                        SessionSocketHolder.put(userId, ctx.channel());
                        // 这个人有多少个组
                        if (CollUtil.isNotEmpty(groupIds)) {
                            for (String groupId : groupIds) {
                                if (SessionSocketHolder.getGroup(groupId) == null) {
                                    // 创建不同的channelGroup
                                    ChannelGroup channels = new DefaultChannelGroup(ctx.executor());
                                    // 把自己添加进去
                                    channels.add(ctx.channel());
                                    SessionSocketHolder.put(groupId, channels);
                                } else {
                                    // 以及创建过组了，直接添加到组即可
                                    SessionSocketHolder.putChannel(groupId, ctx.channel());
                                }
                            }
                        }
                    }
                    // 传递到下一个handler：升级握手
                    ctx.fireChannelRead(request.setUri("/ws").retain());
                } else {
                    // 验证失败
                    ctx.close();
                }
            }
        } else {
            // 路径不对 关闭
            ctx.close();
        }
    }


}
