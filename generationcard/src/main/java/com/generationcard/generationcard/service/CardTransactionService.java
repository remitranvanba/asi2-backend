package com.generationcard.generationcard.service;

import com.generationcard.generationcard.producer.TransactionMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class CardTransactionService {

    @Autowired
    private TransactionMessageProducer transactionMessageProducer;

    public boolean processTransaction(Transaction  transaction ) {
        // Simulate card generation logic
        boolean isCardGenerated = generateCard(transaction );

        // Notify Monolith via ActiveMQ
        transactionMessageProducer.sendMessage("card.queue", new CardTransactionResponse(isCardGenerated));

        return isCardGenerated;
    }

    private boolean generateCard(Transaction  transaction ) {
        // Simulate card generation process
        return true; // Assume card generation is successful
    }
}
