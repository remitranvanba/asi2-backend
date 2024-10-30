package com.generationcard.generationcard.model;

import java.io.Serializable;

public class Transaction implements Serializable {
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

    public int getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public String getDescription() {
        return this.description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
