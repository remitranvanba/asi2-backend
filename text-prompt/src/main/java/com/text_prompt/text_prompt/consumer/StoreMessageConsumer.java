package com.text_prompt.text_prompt.consumer;

import com.shared.Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StoreMessageConsumer {
    /**
     * Message Listener of ActiveMQ queue
     * @param store
     */
    @JmsListener(destination = "${activemq.destination}", containerFactory = "jmsFactory")
    public void processToDo(Store store) {
        log.info("Consumer> {}", store);
    }
}
