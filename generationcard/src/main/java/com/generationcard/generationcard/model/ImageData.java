package com.generationcard.generationcard.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImageData {
    private String imageUrl;

    public ImageData(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
