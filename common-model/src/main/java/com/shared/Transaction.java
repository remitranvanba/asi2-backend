package com.shared;

import java.io.Serializable;
import java.util.UUID;

public class Transaction implements Serializable {

    private static final long serialVersionUID = 1069270118228032176L;

    // transfer field
    private long id;
    private String prompt;
    // property field
    private String text;
    private String description;
    private String img;



    public Transaction(long id, String text, String description, String img) {
        this.id = id;

        this.text = text;
        this.description = description;
        this.img = img;
    }

    public Transaction() {};

    public Transaction(long id, String text, String description) {
        this.id = id;
        this.text = text;
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public long getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public String getDescription() {
        return this.description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}