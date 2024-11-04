package com.generationcard.generationcard.controller;

import com.generationcard.generationcard.model.TransactionEntity;
import com.generationcard.generationcard.service.TransactionService;
import com.shared.CardGenerationPrompt;
import com.shared.CardGenerationResponse;
import com.shared.Transaction;
import com.generationcard.generationcard.model.ImageData;
import com.generationcard.generationcard.model.TextData;
import com.generationcard.generationcard.producer.TransactionMessageProducer;
import com.generationcard.generationcard.service.CardTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/cardgeneration")

public class CardTransactionController {

    private static final Logger log = LoggerFactory.getLogger(CardTransactionController.class);
    @Autowired
    private CardTransactionService cardTransactionService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    private TransactionMessageProducer transactionMessageProducer;

    // Call de retour des ms
    @PostMapping("/receive-title")
    public ResponseEntity<TransactionEntity> receiveTitle(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.updateTitle(transaction));
    }
    @PostMapping("/receive-description")
    public ResponseEntity<TransactionEntity> receiveDescription(@RequestBody Transaction transaction) {
        TransactionEntity transactionEntity = transactionService.updateDescription(transaction);
        if (!transactionEntity.getTitle().isEmpty() &&
                !transactionEntity.getDescription().isEmpty() &&
                !transactionEntity.getImg().isEmpty()) {
            transactionService.sendToMonolith(transactionEntity);
        }
        return ResponseEntity.ok(transactionEntity);
    }

    // Call de retour des ms
    @PostMapping("/receive-image")
    public ResponseEntity<TransactionEntity> receiveImage(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.updateImage(transaction));
    }
    // call d'entrée depuis le monolith
    @PostMapping("/generate-card")
    public ResponseEntity<CardGenerationResponse> generateCard(@RequestBody CardGenerationPrompt prompt ) {
        // Create transaction with prompt information
        TransactionEntity transaction = transactionService.createTransactionEntity();
        Transaction transactionShared = new Transaction(
                transaction.getId(),
                transaction.getTitle(),
                transaction.getDescription(),
                transaction.getImg());
        // titre
        log.info(Long.toString(transaction.getId())) ;
        transactionMessageProducer.sendMessage(transactionShared, "title");
        // description
        transactionMessageProducer.sendMessage(transactionShared, "description");
        // image
        transactionMessageProducer.sendMessage(transactionShared, "image");
        // cardTransactionService.initializeTransaction(transaction);
        return ResponseEntity.ok(new CardGenerationResponse(transaction.getId()));
    }
}
