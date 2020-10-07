package com.hanlin.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @describe:
 * @author:hl.yuan
 * @date:2020-10-06
 */
@Component
public class SpringConsumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) throws JMSException {
        // 加载spring配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-ActiveMQ.xml");

        SpringConsumer springConsumer = applicationContext.getBean(SpringConsumer.class);
//        while (true) {
//            TextMessage message = (TextMessage) springConsumer.jmsTemplate.receive();
//            if (message!=null ) {
//                String text = message.getText();
//                System.out.println(text);
//            } else {
//                break;
//            }
//        }
        while (true) {
            String text = (String) springConsumer.jmsTemplate.receiveAndConvert();
            if (text!=null) {
                System.out.println(text);
            } else {
                System.out.println("循环结束");
                break;
            }
        }

        System.out.println("结束消息完毕");

    }
}
