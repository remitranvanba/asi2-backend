package com.generationcard.generationcard.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TextData {
    private String text;

    public TextData(String text) {
        this.text = text;
    }

}
