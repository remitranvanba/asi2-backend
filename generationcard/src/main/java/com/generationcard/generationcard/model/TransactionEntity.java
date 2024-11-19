package com.generationcard.generationcard.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(length = Integer.MAX_VALUE)
    private String img;
    private String title;
    private String description;
    private Integer userId;

}
