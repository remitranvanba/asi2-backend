package com.cpe.image_prompt.consumer;

import com.cpe.image_prompt.model.ImageRequest;
import com.cpe.image_prompt.model.ImageResponse;
import com.shared.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class Consumer {
    /**
     * Message Listener of ActiveMQ queue
     * @param transaction
     */
    @JmsListener(destination = "${activemq.destination}", containerFactory = "jmsFactory")
    public void processToDo(Transaction transaction) {
        log.info("Consumer> {}", transaction.getPrompt());
        String uri = "http://localhost:8080/prompt/req";
        RestTemplate restTemplate = new RestTemplate();
        ImageRequest imageRequest = new ImageRequest(transaction.getPrompt(), "");
        ImageResponse result = null;
        try {
             result = restTemplate.postForObject(uri, imageRequest, ImageResponse.class);
        }
        catch (Exception e) {
            log.error("Error when calling AI API, might be due to token");
        }

        if (result == null) {
            // no more free ai :C
            result = new ImageResponse();
            result.setUrl("https://neural.love/cdn/ai-photostock/1ef8c9d3-c038-6048-beb4-bb732dd4d9fd/0.webp?Expires=2051222399&Signature=ZLISP66E8-1hJ9CDrg6cTPHiI4p2zSGbnKxhE3DfKCKemDzsTiLhDgZL1XmCD9OVfVv3Px6wecHuW67GMsxnlRzZ21mqFblIz2LA17fT3Kp0FNPhhHT7pgEKMVwgICR~rjWJyZKz6IQo7JbTRbJ1XzyBxpA1WHEafj6n7Y3fPZ-gJD8Y4jJsWSTam8vOdDG0DdkM9lyCbGYU30wPVXDtSOzGJRFyYK23ax0Sx~63iRMzftLir6Gq28cvDtOciVa1trew4Z7UzSx4AlPFka1WY2u9AAXmbm3E1fC-2hMrdWMEs2jkmnGEwost~lhbjWWnwz4mm43SnEn-X4qzmfbMSQ__&Key-Pair-Id=K2RFTOXRBNSROX");
        }
        log.info("ImageResponse: {}", result.getUrl());

        // post api call to indicate we finished the IMG on ms card-generation
        transaction.setImg(result.getUrl());

        String uriResponse = "http://localhost:8087/cardgeneration/receive-image";
        RestTemplate restCardGeneration = new RestTemplate();

        Transaction resultCardGeneration = restCardGeneration.postForObject(uriResponse, transaction, Transaction.class);
        log.info(resultCardGeneration.getImg());
    }
}
