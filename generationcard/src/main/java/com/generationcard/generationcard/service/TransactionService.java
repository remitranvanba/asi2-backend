package com.generationcard.generationcard.service;

import com.generationcard.generationcard.Repository.TransactionEntityRepository;
import com.generationcard.generationcard.model.TransactionEntity;
import com.shared.Transaction;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);
    private final TransactionEntityRepository transactionEntityRepository;

    public TransactionService(TransactionEntityRepository transactionEntityRepository) {
        this.transactionEntityRepository = transactionEntityRepository;
    }

    public TransactionEntity createTransactionEntity() {
        TransactionEntity transaction = new TransactionEntity();
        return this.transactionEntityRepository.save(transaction);
    }
    public TransactionEntity updateTransaction(Transaction transaction) {
        Optional<TransactionEntity> transactionInDb = transactionEntityRepository.getById(transaction.getId());
        if (transactionInDb.isPresent()) {
            if (transaction.getImg() != null && !transaction.getImg().isEmpty()) {
                transactionInDb.get().setImg(transaction.getImg());
            }
            if (transaction.getText() != null && !transaction.getText().isEmpty()) {
                transactionInDb.get().setTitle(transaction.getText());
            }
            if (transaction.getDescription() != null && !transaction.getDescription().isEmpty()) {
                transactionInDb.get().setDescription(transaction.getDescription());
            }
            return transactionEntityRepository.saveAndFlush(transactionInDb.get());
        }  else {
            return null;
        }
    }

    @Transactional
    public TransactionEntity updateTitle(Transaction transaction) {
        Optional<TransactionEntity> transactionInDb = transactionEntityRepository.getById(transaction.getId());
        if (transactionInDb.isPresent()) {
            transactionInDb.get().setTitle(transaction.getText());
            return transactionEntityRepository.saveAndFlush(transactionInDb.get());
        } else {
            return null;
        }
    }
    @Transactional
    public TransactionEntity updateDescription(Transaction transaction) {
        Optional<TransactionEntity> transactionInDb = transactionEntityRepository.getById(transaction.getId());
        if (transactionInDb.isPresent()) {
            transactionInDb.get().setDescription(transaction.getDescription());
            return transactionEntityRepository.saveAndFlush(transactionInDb.get());
        } else {
            return null;
        }
    }
    @Transactional
    public TransactionEntity updateImage(Transaction transaction) {
        Optional<TransactionEntity> transactionInDb = transactionEntityRepository.getById(transaction.getId());
        if (transactionInDb.isPresent()) {
            transactionInDb.get().setImg(transaction.getImg());
            return transactionEntityRepository.save(transactionInDb.get());
        } else {
            return null;
        }
    }

    public void sendToMonolith(TransactionEntity transaction) {
        // send to monolith
        String uriResponse = "http://localhost:8083/create-card";
        RestTemplate restCardGeneration = new RestTemplate();
        try {
            Transaction resultCardGeneration = restCardGeneration.postForObject(uriResponse, transaction, Transaction.class);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }
}
