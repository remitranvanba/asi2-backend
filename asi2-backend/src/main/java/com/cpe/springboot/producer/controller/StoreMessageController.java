package com.cpe.springboot.producer.controller;

import com.cpe.springboot.producer.StoreMessageProducer;

import com.shared.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publish")
public class StoreMessageController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    StoreMessageProducer storeMessageProducer;

    @Value("${activemq.destination}")
    private String destination;

    /**
     * API for publish message for ActiveMQ queue
     * @param transaction
     * @return String
     */
    @PostMapping("/text")
    public String publishTextMessage(@RequestBody Transaction transaction){

        storeMessageProducer.sendTo("text", transaction);
        return "Success";
    }

    @PostMapping("/image")
    public String publishImageMessage(@RequestBody Transaction transaction){

        storeMessageProducer.sendTo("image", transaction);
        return "Success";
    }

}
