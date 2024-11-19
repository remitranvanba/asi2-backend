package com.text_prompt.text_prompt.consumer;

import com.shared.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Timer;

@Component
@Slf4j
public class Consumer {
    /**
     * Message Listener of ActiveMQ queue
     * @param transaction
     */
    @JmsListener(destination = "${activemq.destinationTitle}", containerFactory = "jmsFactory")
    public void processToDo(Transaction transaction) {
        // generate card information: Description

        // RestTemplate restTitle = new RestTemplate();
        // String result = restTitle.getForObject("https://random-word-api.herokuapp.com/word", String.class);

        transaction.setText("Title n°" + transaction.getId());
        // send call to cardGeneration to notify that everything is OK
        // transaction.setText(result.substring(2, result.length()-2));
        String uriResponse = "http://localhost:8087/cardgeneration/receive-title";
        RestTemplate restCardGeneration = new RestTemplate();
        Transaction resultCardGeneration = restCardGeneration.postForObject(uriResponse, transaction, Transaction.class);
        log.info("Title: " + resultCardGeneration.getText());
    }

    @JmsListener(destination = "${activemq.destinationDescription}", containerFactory = "jmsFactory")
    public void processToDoDescription(Transaction transaction) {
        // generate card information: Description
//      transaction.setDescription("Je suis une description aléatoire, mais pas vraiment");
        try {
            transaction.setDescription(String.join(" ",new RestTemplate().getForObject("https://random-word-api.herokuapp.com/word?number=10", List.class)));
        } catch (Exception e) {
            transaction.setDescription("Je suis une description aléatoire, mais pas vraiment");
        }
        // send call to cardGeneration to notify that everything is OK
        String uriResponse = "http://localhost:8087/cardgeneration/receive-description";
        RestTemplate restCardGeneration = new RestTemplate();
        Transaction resultCardGeneration = restCardGeneration.postForObject(uriResponse, transaction, Transaction.class);
        log.info("Description: " + resultCardGeneration.getDescription());




    }

}
