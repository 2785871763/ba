package com.zje.server.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author：dcy
 * @Description:
 * @Date: 2020/7/24 13:48
 */
@Slf4j
@Component
public class MsgProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${rocketmq.producer.topic}")
    private String topic;

    /**
     * 发送消息
     * 【单聊、群聊、Server消息】
     *
     * @param msg
     */
    public void sendMsg(String msg) {
        rocketMQTemplate.syncSend(topic, msg);
//        SendResult sendResult = rocketMQTemplate.syncSend(topic, msg);
//        log.info("sendP2PMsg -=- {}", sendResult.toString());
    }

}
