package com.hanlin.persistence;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @describe:消费者Topic的持久化
 * @author:hl.yuan
 * @date:2020-10-05
 */
public class JmsConsumerPersistenceTopic {

    //  linux 上部署的activemq 的 IP 地址 + activemq 的端口号
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";

    // 目的地的名称
    public static final String QUEUE_NAME = "jdbc03";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("Topic模式开始：持久化操作");

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 获取连接
        Connection connection = connectionFactory.createConnection();
        // 设置客户端ID。向MQ服务器注册自己的名称
        connection.setClientID("hlyuan");
        // 获取session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 获取队列
        Topic topic = session.createTopic(QUEUE_NAME);

        // 创建一个topic订阅者对象。一参是topic，二参是订阅者名称
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic,"remark...");

        // 启动连接
        connection.start();

        topicSubscriber.setMessageListener((message -> {
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
        topicSubscriber.close();
        session.close();
        connection.close();

    }

}
