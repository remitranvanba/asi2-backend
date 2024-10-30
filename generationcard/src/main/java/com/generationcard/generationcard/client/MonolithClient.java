package com.generationcard.generationcard.client;

import com.generationcard.generationcard.model.CardInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface MonolithClient {

    @PostMapping("/process-card")
    void sendCardInfoToMonolith(@RequestBody  CardInfo cardInfo);
}
