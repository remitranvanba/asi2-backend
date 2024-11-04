package com.shared;

public class CardGenerationResponse {
    public long transactionId;

    public CardGenerationResponse() {
    }

    public CardGenerationResponse(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }


}