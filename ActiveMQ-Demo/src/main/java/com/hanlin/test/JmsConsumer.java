package com.hanlin.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @describe:消费者
 * @author:hl.yuan
 * @date:2020-10-05
 */
public class JmsConsumer {

    //  linux 上部署的activemq 的 IP 地址 + activemq 的端口号
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";

    // 目的地的名称
    public static final String QUEUE_NAME = "jdbc01";

    public static void main(String[] args) throws JMSException {
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
        Queue queue = session.createQueue(QUEUE_NAME);
        // 创建消费者
        MessageConsumer consumer = session.createConsumer(queue);

        while (true) {
            // 接受消息，receive无参方法：如果没有消息，就一直等待；
            // receive有参方法里面传入的是time 当超过设定的时间后，就不在等待了，所以receive就位null，null后，直接break，跳出循环。
            TextMessage receive = (TextMessage) consumer.receive();
            if (receive!=null) {
                System.out.println("****消费者的消息："+receive.getText());
            } else {
                break;
            }
        }
        // 关闭资源
        consumer.close();
        session.close();
        connection.close();
    }
}
