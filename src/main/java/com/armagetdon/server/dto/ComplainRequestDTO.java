package com.armagetdon.server.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
@Data
public class ComplainRequestDTO {

    // 게시글 신고 API
    @Getter
    public static class complainPostDTO{
        @Min(1)
        @NotNull
        private Long post_id;

        @Min(1)
        @NotNull
        private Long member_id;
    }
}
