package com.generationcard.generationcard.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@AllArgsConstructor
@Getter
@Setter
public class CardInfo {
    private TextData textData;
    private ImageData imageData;
    private String description;
}
