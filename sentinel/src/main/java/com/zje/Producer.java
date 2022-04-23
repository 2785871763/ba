package com.zje;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * @author: zje
 * @createDate 2022/4/9
 * @desc
 */
public class Producer {

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        // 创建producer
        DefaultMQProducer producer = new DefaultMQProducer("PG");
        producer.setNamesrvAddr("http://120.77.71.220:9876");
        producer.start();
        for (int i = 0; i < 10; i++) {
            byte[] body = ("hello world -"+i).getBytes(StandardCharsets.UTF_8);
            Message msg = new Message("msgTopic","msgTag",  body);
            SendResult res = producer.send(msg);
            System.out.println(res);
        }
        producer.shutdown();

    }


}
