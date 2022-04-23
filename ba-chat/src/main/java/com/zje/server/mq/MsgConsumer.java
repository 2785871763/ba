package com.zje.server.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcy.common.constant.CommonConstant;
import com.dcy.common.dto.msg.MsgEvent;
import com.dcy.common.utils.SessionSocketHolder;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

/**
 * @Author：dcy
 * @Description: 消费消息
 * @Date: 2020/7/24 13:47
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "${rocketmq.producer.topic}", consumerGroup = "${rocketmq.producer.group}", messageModel = MessageModel.BROADCASTING)
public class MsgConsumer implements RocketMQListener<String> {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void onMessage(String message) {
        JSONObject jsonObject = JSON.parseObject(message);
        String event = jsonObject.getString("event");
        switch (MsgEvent.valueOf(event)) {
            case P2P:
                // 接收人的
                Channel receiveChannel = SessionSocketHolder.get(jsonObject.getString("receiveUserId"));
                if (receiveChannel != null) {
                    receiveChannel.writeAndFlush(new TextWebSocketFrame(message));
                }
                // 发送人的
                Channel userChannel = SessionSocketHolder.get(jsonObject.getString("userId"));
                if (userChannel != null) {
                    userChannel.writeAndFlush(new TextWebSocketFrame(message));
                }
                sendP2pOffMsg(jsonObject);
                break;
            case GROUP:
                // 收到消息，获取群组的人，然后推送消息
                ChannelGroup channelGroup = SessionSocketHolder.getGroup(jsonObject.getString("groupId"));
                if (channelGroup != null) {
                    channelGroup.writeAndFlush(new TextWebSocketFrame(message));
                }
                sendP2pGroupOffMsg(jsonObject);
                break;
            case SERVER:
                // 获取全局的组信息
                ChannelGroup allChannelGroup = SessionSocketHolder.getGroup(CommonConstant.ALL_CHANNEL_GROUP_NAME);
                if (allChannelGroup != null) {
                    allChannelGroup.writeAndFlush(new TextWebSocketFrame(message));
                }
                break;
        }
    }

    /**
     * 离线消息处理 ，暂时http请求
     *
     * @param p2PMsg
     */
    private void sendP2pOffMsg(JSONObject p2PMsg) {
        // TODO 离线消息处理  发送消息体缓存本地 + 远程DB
        String url = "http://localhost:13286/off-p2p-mes/sendP2pOffMsg";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", p2PMsg.getString("userId"));
        jsonObject.put("receiveUserId", p2PMsg.getString("receiveUserId"));
        jsonObject.put("message", p2PMsg.getString("message"));
        jsonObject.put("sendTime", LocalDateTime.now());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> request = new HttpEntity<>(jsonObject, headers);
        restTemplate.postForEntity(url, request, String.class);
    }

    /**
     * 离线消息处理，暂时http请求
     *
     * @param groupMsg
     */
    private void sendP2pGroupOffMsg(JSONObject groupMsg) {
        // TODO 离线消息处理  发送消息体缓存本地 + 远程DB
        String url = "http://localhost:13286/off-group-mes/sendGroupOffMsg";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", groupMsg.getString("userId"));
        jsonObject.put("groupId", groupMsg.getString("groupId"));
        jsonObject.put("message", groupMsg.getString("message"));
        jsonObject.put("sendTime", LocalDateTime.now());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> request = new HttpEntity<>(jsonObject, headers);
        restTemplate.postForEntity(url, request, String.class);
    }
}
