package com.hanlin.activemq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.Topic;
import java.util.UUID;

@SpringBootTest
class ActivemqApplicationTests {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Topic topic;

    @Test
    void contextLoads() {
        jmsMessagingTemplate.convertAndSend(topic, UUID.randomUUID().toString());
    }

}
