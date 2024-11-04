package com.generationcard.generationcard.Repository;


import com.generationcard.generationcard.model.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionEntityRepository extends JpaRepository<TransactionEntity, Integer> {
    public Optional<TransactionEntity> findById(long id);
}
