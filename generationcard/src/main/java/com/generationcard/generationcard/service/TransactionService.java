package com.generationcard.generationcard.service;

import com.generationcard.generationcard.Repository.TransactionEntityRepository;
import com.generationcard.generationcard.model.TransactionEntity;
import com.shared.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionEntityRepository transactionEntityRepository;

    public TransactionService(TransactionEntityRepository transactionEntityRepository) {
        this.transactionEntityRepository = transactionEntityRepository;
    }

    public TransactionEntity createTransactionEntity() {
        TransactionEntity transaction = new TransactionEntity();
        return this.transactionEntityRepository.save(transaction);
    }
    public TransactionEntity updateTransaction(Transaction transaction) {
        Optional<TransactionEntity> transactionInDb = transactionEntityRepository.findById(transaction.getId());
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
            return transactionEntityRepository.save(transactionInDb.get());
        }  else {
            return null;
        }
    }
}
