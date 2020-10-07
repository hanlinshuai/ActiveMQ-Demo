package com.hanlin.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

/**
 * @describe:spring整合ActiveMQ
 * @author:hl.yuan
 * @date:2020-10-06
 */
@Component
public class SpringProduce {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        // 加载spring配置文件
        ApplicationContext applicationContext = new  ClassPathXmlApplicationContext("Spring-ActiveMQ.xml");

        SpringProduce springProduce = applicationContext.getBean(SpringProduce.class);
        for (int i=0;i<3;i++){
            String a = "测试消息"+i;
            springProduce.jmsTemplate.send((session -> {
                TextMessage message = session.createTextMessage(a);
                return message;
            }));
        }
        System.out.println("消息发送成功。。。。");

    }
}
