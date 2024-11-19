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
    private Integer userId;

    public Transaction(long id, String prompt, String text, String description, String img, Integer userId) {
        this.id = id;
        this.prompt = prompt;
        this.text = text;
        this.description = description;
        this.img = img;
        this.userId = userId;
    }

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

    public Transaction(long id, String title, String description, String img, Integer userId) {
        this.id = id;
        this.text = title;
        this.description = description;
        this.img = img;
        this.userId = userId;
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
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}