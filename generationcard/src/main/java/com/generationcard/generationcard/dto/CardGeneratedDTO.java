package com.generationcard.generationcard.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CardGeneratedDTO {
    private int id;
    private String name;
    private String description;
    private String imgUrl;

    public CardGeneratedDTO() {
    }
    public CardGeneratedDTO(int id, String name, String description, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
    }
}
