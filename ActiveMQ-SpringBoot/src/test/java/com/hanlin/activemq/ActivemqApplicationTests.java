package com.hanlin.activemq;

import com.hanlin.activemq.produce.SpringBootProduce;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import javax.jms.Queue;
import java.io.IOException;

@SpringBootTest
class ActivemqApplicationTests {

    @Autowired
    private Queue queue;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private SpringBootProduce springBootProduce;

    @Test
    void contextLoads() {
        // 生产者生产消息
        jmsMessagingTemplate.convertAndSend(queue,"我是测试消息");
    }


    @Test
    void test() throws IOException {
        springBootProduce.massageProduce();
        System.in.read();
    }
}
