package com.shared;



public class CardGenerationPrompt {
    private String userId;
    private String prompt;

    public String getUserId() {
        return userId;
    }
    public String getPrompt() {
        return prompt;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public CardGenerationPrompt() {
    }

    public CardGenerationPrompt(String userId, String prompt) {
        this.userId = userId;
        this.prompt = prompt;
    }
}
