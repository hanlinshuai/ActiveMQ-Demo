package com.hanlin.persistence;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @describe:生产者Topic的持久化操作
 * @author:hl.yuan
 * @date:2020-10-05
 */
public class JmsProducePersistenceTopic {

    //  linux 上部署的activemq 的 IP 地址 + activemq 的端口号
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";

    // 目的地的名称
    public static final String QUEUE_NAME = "jdbc03";


    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 创建连接
        Connection connection = connectionFactory.createConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic queue = session.createTopic(QUEUE_NAME);

        MessageProducer producer = session.createProducer(queue);
        // 非持久化，如果不设置，就默认是持久化
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        // 设置持久化topic之后再，启动连接
        connection.start();

        for (int i = 1; i <= 9; i++) {
            // 创建消息
            TextMessage textMessage = session.createTextMessage("持久化消息：" + i);
            // 通过messageProducer发送给mq
            producer.send(textMessage);
        }

        producer.close();
        session.close();
        connection.close();

        System.out.println("生产者产生了消息！");
    }
}
