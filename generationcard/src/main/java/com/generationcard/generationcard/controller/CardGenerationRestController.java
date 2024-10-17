package com.generationcard.generationcard.controller;

import com.generationcard.generationcard.service.CardGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CardGenerationRestController {
    @Autowired
    private final CardGenerationService cardGenerationService;

    public CardGenerationRestController(CardGenerationService cardGenerationService) {
        this.cardGenerationService = cardGenerationService;
    }

}
