package com.zje;

import io.netty.util.concurrent.DefaultThreadFactory;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

/**
 * @author: zje
 * @createDate 2022/4/10
 * @desc
 */
public class Transaction {

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {

        TransactionMQProducer producer = new TransactionMQProducer("TPG");
        producer.setNamesrvAddr("http://120.77.71.220:9876");
        ExecutorService executorService= new ThreadPoolExecutor(2, 5, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2000), r -> {
                    Thread t = new Thread(r);
                    t.setName("t-name");
                    return t;
                });
        producer.setExecutorService(executorService);
        producer.setTransactionListener(new ICBC());
        producer.start();
        String[] str = {"A","B","C"};
        for (int i = 0; i < 3; i++) {
            byte[] b = ("hi-" + i).getBytes(StandardCharsets.UTF_8);
            Message msg = new Message("tTopic",str[i],b);
            SendResult sendResult = producer.sendMessageInTransaction(msg,null);
            System.out.println(sendResult.getSendStatus());
        }
    }

}


class ICBC implements TransactionListener{

    // 回调操作
    // 提交成功调用
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        return null;
    }

    // 回调操作
    // 消息回查
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        return null;
    }
}