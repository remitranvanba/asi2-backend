package com.generationcard.generationcard.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class Transaction implements Serializable {
    @Serial
    private static final long serialVersionUID = 1069270118228032176L;

    private int id;
    private String text;
    private String description;

    public Transaction() {};

    public Transaction(int id, String text, String description) {
        this.id = id;
        this.text = text;
        this.description = description;
    }

}
