package com.armagetdon.server.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;

public class ComplainRequestDTO {

    // 게시글 신고 API
    @Getter
    public static class complainPostDTO{
        @Min(1)
        private Long post_id;
    }
}
