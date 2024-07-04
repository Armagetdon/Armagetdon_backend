package com.armagetdon.server.dto;

import com.armagetdon.server.domain.Member;
import com.armagetdon.server.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class RecommendResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class recommendJoinDTO{

        LocalDateTime createdAt;


    }
}
