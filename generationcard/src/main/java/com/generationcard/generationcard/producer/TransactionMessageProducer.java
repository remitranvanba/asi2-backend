package com.generationcard.generationcard.producer;

import com.shared.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produce")
public class TransactionMessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate;


    public void sendMessage(Transaction transaction, String destination) {

        jmsTemplate.convertAndSend(destination, transaction);  // Queue for TextPrompt
        // jmsTemplate.convertAndSend("image.queue", transaction);  // Queue for ImagePrompt
    }
}
