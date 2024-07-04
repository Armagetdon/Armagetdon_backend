package com.armagetdon.server.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class PostRequestDTO {

    // 게시물 생성 API
    @Getter
    public static class createPostDTO{

        @NotBlank
        private String youtube_url;

        @Min(1)
        private Long post_image_id;
    }
}
