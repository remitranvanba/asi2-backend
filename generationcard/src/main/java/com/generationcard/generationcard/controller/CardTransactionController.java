package com.generationcard.generationcard.controller;

import com.generationcard.generationcard.model.Transaction;
import com.generationcard.generationcard.producer.TransactionMessageProducer;
import com.generationcard.generationcard.service.CardTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cardgeneration")

public class CardTransactionController {

    @Autowired
    private CardTransactionService cardTransactionService;

    @Autowired
    private TransactionMessageProducer transactionMessageProducer;


    @PostMapping("/receive-text")
    public ResponseEntity<String> receiveTextData(@RequestBody String textData) {
        cardTransactionService.saveTextData(textData.Text());
        return ResponseEntity.ok("Text data received successfully");
    }

    @PostMapping("/receive-image")
    public ResponseEntity<String> receiveImageData(@RequestBody String imageData) {
        cardTransactionService.saveImageData(imageData.getImageUrl());
        return ResponseEntity.ok("Image data received successfully");
    }

    @PostMapping("/generate-card")
    public ResponseEntity<String> generateCard(@RequestBody Transaction transaction ) {
        transactionMessageProducer.sendMessage(transaction);
        cardTransactionService.initializeTransaction(transaction);
        return ResponseEntity.ok("Transaction sent to consumers");    }
}
