package com.armagetdon.server.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class PostRequestDTO {

    // 게시물 생성 API
    @Getter
    public static class createPostDTO{

        @NotBlank
        private String youtube_url;

        @NotNull
        private Long post_image_id;
    }
}
