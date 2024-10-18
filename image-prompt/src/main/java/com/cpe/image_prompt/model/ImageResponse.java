package com.cpe.image_prompt.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {

    @NonNull
    String url;
    @NonNull
    String base64;
}
