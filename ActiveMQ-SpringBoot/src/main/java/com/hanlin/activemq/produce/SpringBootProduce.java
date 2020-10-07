package com.hanlin.activemq.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.Date;
import java.util.UUID;

/**
 * @describe:消息生产者
 * @author:hl.yuan
 * @date:2020-10-06
 */
@Component
public class SpringBootProduce {

    @Autowired
    private Queue queue;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Scheduled(fixedDelay = 3000)
    public void massageProduce (){
        jmsMessagingTemplate.convertAndSend(queue, UUID.randomUUID().toString());
        System.out.println(new Date()+"~~~~~消息定时发送成功~~~~~~");
    }

}
