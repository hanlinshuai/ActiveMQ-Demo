package com.hanlin.test;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @describe:消费者第二写法
 * @author:hl.yuan
 * @date:2020-10-05
 */
public class JmsConsumer2 {

    //  linux 上部署的activemq 的 IP 地址 + activemq 的端口号
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";

    // 目的地的名称
    public static final String QUEUE_NAME = "jdbc01";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("我是二号消费者");
        // 创建connection连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 获取连接
        Connection connection = activeMQConnectionFactory.createConnection();
        // 启动
        connection.start();
        // 获取session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 获取队列
        Queue queue = session.createQueue(QUEUE_NAME);
        // 获得消费者
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (message != null && message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("****消费者的消息：" + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // 让主线程不要结束。因为一旦主线程结束了，其他的线程（如此处的监听消息的线程）也都会被迫结束。
        // 实际开发中，我们的程序会一直运行，这句代码都会省略。
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
}
