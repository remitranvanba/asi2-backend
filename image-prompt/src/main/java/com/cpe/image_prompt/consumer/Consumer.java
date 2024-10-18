package com.cpe.image_prompt.consumer;

import com.shared.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {
    /**
     * Message Listener of ActiveMQ queue
     * @param transaction
     */
    @JmsListener(destination = "${activemq.destination}", containerFactory = "jmsFactory")
    public void processToDo(Transaction transaction) {
        log.info("Consumer> {}", transaction.getText());
    }
}
