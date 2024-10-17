package com.generationcard.generationcard.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class CardGenerated {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private String imgUrl;

    public CardGenerated() {
    }
    public CardGenerated(int id, String name, String description, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
    }
}
