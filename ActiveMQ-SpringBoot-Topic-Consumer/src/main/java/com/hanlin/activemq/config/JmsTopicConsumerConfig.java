package com.hanlin.activemq.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 * @describe:
 * @author:hl.yuan
 * @date:2020-10-06
 */
@Configuration
public class JmsTopicConsumerConfig {
    @Value("myTopic")
    private String topicName;

    @Bean
    public Topic topic(){
        return  new ActiveMQTopic(topicName);
    }


    @JmsListener(destination ="${myTopic}" )
    public void receive (TextMessage textMessage) throws JMSException {
        System.out.println("topic消费者："+textMessage.getText());
    }

}
