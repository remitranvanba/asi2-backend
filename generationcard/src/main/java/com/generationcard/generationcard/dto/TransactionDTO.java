package com.generationcard.generationcard.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TransactionDTO implements Serializable {
    private int id;

    public TransactionDTO() {
    }

    public TransactionDTO(int id) {
        this.id = id;
    }
}
