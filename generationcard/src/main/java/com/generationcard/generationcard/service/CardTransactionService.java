package com.generationcard.generationcard.service;

import com.generationcard.generationcard.client.MonolithClient;
import com.generationcard.generationcard.model.CardInfo;
import com.generationcard.generationcard.model.ImageData;
import com.generationcard.generationcard.model.TextData;
import com.generationcard.generationcard.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CardTransactionService {

    private TextData textData;
    private ImageData imageData;

    @Autowired
    private MonolithClient monolithClient;

    public void saveTextData(TextData text) {
        this.textData = text;
        checkAndSendToMonolith();
    }
    public void saveImageData(ImageData image) {
        this.imageData = image;
        checkAndSendToMonolith();
    }

    private void checkAndSendToMonolith() {
        if (textData != null && imageData != null) {
            // All data received, send final result to Monolith
            CardInfo cardInfo = new CardInfo(textData, imageData, "Card Description");
            monolithClient.sendCardInfoToMonolith(cardInfo);
        }
    }

    public void initializeTransaction(Transaction transaction) {
        this.textData = null;
        this.imageData = null;
    }
}
