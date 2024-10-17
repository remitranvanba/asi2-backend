package com.generationcard.generationcard.service;

import com.generationcard.generationcard.entity.CardGenerated;
import com.generationcard.generationcard.repository.CardGeneratedRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardGenerationService {
    @Autowired
    private final CardGeneratedRepo cardGeneratedRepo;

    public CardGenerationService(CardGeneratedRepo cardGeneratedRepo) {
        this.cardGeneratedRepo = cardGeneratedRepo;
    }

    public CardGenerated createCard(CardGenerated card){
        return new CardGenerated(1, "oum", "test", "url");
    }
}
