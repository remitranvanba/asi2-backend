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
        log.info("Consumer> {}", transaction.getText());
        String uri = "http://localhost:8080/prompt/req";
        RestTemplate restTemplate = new RestTemplate();
        ImageRequest imageRequest = new ImageRequest(transaction.getText(), "");
        ImageResponse result = restTemplate.postForObject(uri, imageRequest, ImageResponse.class);
        log.info("ImageResponse: {}", result.getUrl());
    }
}
