package com.hanlin.affairs;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @describe:生产者的事务
 * @author:hl.yuan
 * @date:2020-10-05
 */
public class JmsProduceAffairs {

    //  linux 上部署的activemq 的 IP 地址 + activemq 的端口号
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";

    // 目的地的名称
    public static final String QUEUE_NAME = "jdbc02";


    public static void main(String[] args) throws JMSException {
        // 创建连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 获取连接
        Connection connection = connectionFactory.createConnection();
        // 启动连接
        connection.start();
        // 事务：开启；签收策略：自动签收。
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        // 创建队列
        Queue queue = session.createQueue(QUEUE_NAME);
        // 创建消息生产者
        MessageProducer producer = session.createProducer(queue);

        for (int i = 1; i <= 9; i++) {
            // 创建消息
            TextMessage textMessage = session.createTextMessage("持久化消息：" + i);
            // 通过messageProducer发送给mq
            producer.send(textMessage);
        }
        // 提交事务
        session.commit();
        producer.close();
        session.close();
        connection.close();

        System.out.println("生产者产生了消息！");
    }


}
