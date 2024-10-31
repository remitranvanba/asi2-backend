package com.generationcard.generationcard.client;

import com.generationcard.generationcard.model.CardInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "card-service", url = "http://localhost:8080/card")
public interface MonolithClient {

    @PostMapping("/process-card")
    void sendCardInfoToMonolith(@RequestBody  CardInfo cardInfo);
}
