package com.hanlin.activemq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * @describe:ActiveMQ配置类
 * @author:hl.yuan
 * @date:2020-10-06
 */
@Configuration
public class MyActiveMqConfig {

    @Value("${myQueue}")
    private String myQueue;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(myQueue);
    }

}
