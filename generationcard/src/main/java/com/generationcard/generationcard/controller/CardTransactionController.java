package com.generationcard.generationcard.controller;

import com.generationcard.generationcard.service.CardTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cardmicroservice")

public class CardTransactionController {

    @Autowired
    private CardTransactionService cardTransactionService;

    @PostMapping("/generate-card")
    public ResponseEntity<String> generateCard(@RequestBody Transaction  transaction ) {
        // Process the transaction to generate the card
        boolean success = cardTransactionService.processTransaction(transaction);

        if (success) {
            return ResponseEntity.ok("Card generation successful");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Card generation failed");
        }
    }
}
