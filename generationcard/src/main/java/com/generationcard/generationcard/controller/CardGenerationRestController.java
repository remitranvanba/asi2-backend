package com.generationcard.generationcard.controller;

import com.generationcard.generationcard.entity.CardGenerated;
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

    @RequestMapping(method=RequestMethod.POST,value="/card")
    public CardGenerated generateCard(@RequestBody CardGenerated card){
        return cardGenerationService.createCard(card);
    }
}
