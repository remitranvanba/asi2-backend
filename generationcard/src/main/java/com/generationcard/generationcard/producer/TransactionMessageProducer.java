package com.generationcard.generationcard.producer;

public class TransactionMessageProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String destination, CardTransactionResponse response) {
        jmsTemplate.convertAndSend(destination, response);
    }
}
