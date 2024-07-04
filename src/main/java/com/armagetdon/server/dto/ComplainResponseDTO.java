package com.armagetdon.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ComplainResponseDTO {

    // 게시글 신고 API
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class complainResultDTO{
        Long member_id;
        LocalDateTime createdAt;
    }

}
