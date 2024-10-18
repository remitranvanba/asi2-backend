package com.cpe.image_prompt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ImageRequest {
    private String promptTxt;
    private String negativePromptTxt;
}
