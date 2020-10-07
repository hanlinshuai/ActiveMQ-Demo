package com.hanlin.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @describe:消费者
 * @author:hl.yuan
 * @date:2020-10-05
 */
public class JmsConsumerTopic {

    //  linux 上部署的activemq 的 IP 地址 + activemq 的端口号
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";

    // 目的地的名称
    public static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("我是一号消费者");
        // 获取连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 获取连接
        Connection connection = connectionFactory.createConnection();
        // 启动
        connection.start();
        // 获取session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 获取队列
        Topic queue = session.createTopic(TOPIC_NAME);
        // 创建消费者
        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener((message) ->{
            if (message != null && message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("****消费者的消息：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        // 关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}
