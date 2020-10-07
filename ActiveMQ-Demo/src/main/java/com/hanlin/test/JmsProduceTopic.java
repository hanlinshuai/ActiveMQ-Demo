package com.hanlin.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @describe:ActiveMQ生产者，topic模式
 * @author:hl.yuan
 * @date:2020-10-05
 */
public class JmsProduceTopic {

    //  linux 上部署的activemq 的 IP 地址 + activemq 的端口号
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";

    // 目的地的名称
    public static final String TOPIC_NAME = "topic01";


    public static void main(String[] args) throws JMSException {
        // 1 按照给定的url创建连接工厂，这个构造器采用默认的用户名密码。该类的其他构造方法可以指定用户名和密码。
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2 通过连接工厂，获得连接 connection 并启动访问。
        Connection connection = connectionFactory.createConnection();
        // 启动连接
        connection.start();
        // 创建会话 第一参数是是否开启事务， 第二参数是消息签收的方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建队列
        Topic queue = session.createTopic(TOPIC_NAME);
        // 5 创建消息的生产者
        MessageProducer messageProducer = session.createProducer(queue);
        // 添加三条数据
        for (int i=1;i<=9;i++) {
            // 7  创建消息
            TextMessage textMessage = session.createTextMessage("TOPIC消息--" + i);
            // 8  通过messageProducer发送给mq
            messageProducer.send(textMessage);
        }

        // 9 关闭资源
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("  **** 消息发送到MQ完成 ****");
    }
}
