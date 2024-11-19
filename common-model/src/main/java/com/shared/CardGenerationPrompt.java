package com.shared;



public class CardGenerationPrompt {
    private Integer userId;
    private String prompt;

    public Integer getUserId() {
        return userId;
    }
    public String getPrompt() {
        return prompt;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public CardGenerationPrompt() {
    }

    public CardGenerationPrompt(Integer userId, String prompt) {
        this.userId = userId;
        this.prompt = prompt;
    }
}
