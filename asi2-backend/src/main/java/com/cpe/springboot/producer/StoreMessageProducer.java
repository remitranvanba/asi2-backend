package com.cpe.springboot.producer;

import com.shared.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StoreMessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate;



    /**
     * Convert and publish the message to the queue
     *
     * @param destination
     * @param transaction
     */
    public void sendTo(String destination, Transaction transaction) {
        jmsTemplate.convertAndSend(destination, transaction);
        log.info("Producer> Message Sent");
    }


}
