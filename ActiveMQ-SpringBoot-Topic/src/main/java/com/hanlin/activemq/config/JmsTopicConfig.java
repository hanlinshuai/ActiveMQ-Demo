package com.hanlin.activemq.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Topic;

/**
 * @describe:topic配置类
 * @author:hl.yuan
 * @date:2020-10-06
 */
@Configuration
public class JmsTopicConfig {

    @Value("${myTopic}")
    private String topicName;

    @Bean
    public Topic topic(){
        return  new ActiveMQTopic(topicName);
    }



}
