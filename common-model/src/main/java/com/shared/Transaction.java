package com.shared;

import java.io.Serializable;

public class Transaction implements Serializable {

    private static final long serialVersionUID = 1069270118228032176L;

    private int id;
    private String text;
    private int description;

    public Transaction() {};

    public Transaction(int id, String text, int description) {
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

    public int getDescription() {
        return this.description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }
    public void setDescription(int description) {
        this.description = description;
    }

}