package com.armagetdon.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class PostResponseDTO {

    // 게시물 생성 API
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class createResultDTO{
        Long memberId;
        LocalDateTime createdAt;
    }

}
