package com.hanlin.persistence;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @describe:消费者的持久化
 * @author:hl.yuan
 * @date:2020-10-05
 */
public class JmsConsumerPersistence {

    //  linux 上部署的activemq 的 IP 地址 + activemq 的端口号
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";

    // 目的地的名称
    public static final String QUEUE_NAME = "jdbc03";

    public static void main(String[] args) throws JMSException, IOException {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 获取连接
        Connection connection = connectionFactory.createConnection();
        // 启动连接
        connection.start();
        // 获取session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 获取队列
        Queue queue = session.createQueue(QUEUE_NAME);
        // 获取消费者
        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener((message -> {
            if (message!=null && message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }));
        System.in.read();
        consumer.close();
        session.close();
        connection.close();

    }

}
