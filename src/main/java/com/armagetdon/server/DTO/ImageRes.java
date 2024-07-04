package com.armagetdon.server.DTO;

import com.armagetdon.server.domain.PostImage;
import lombok.Builder;

@Builder
public record ImageRes(
        String s3_url
) {
    public static ImageRes of(PostImage image) {
        return ImageRes.builder()
                .s3_url(image.getS3url())
                .build();
    }
}