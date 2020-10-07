package com.hanlin.activemq.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @describe:
 * @author:hl.yuan
 * @date:2020-10-06
 */
@Configuration
public class MyJmsConsumerConfig {

    @JmsListener(destination = "${myQueue}")
    public  void receive(TextMessage textMessage) throws JMSException {
        System.out.println("监听器接受到的消息："+textMessage.getText());
    }



}
