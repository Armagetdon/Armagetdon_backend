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
        Long postId;
        Long memberId;
        String postImageUrl;
        LocalDateTime createdAt;
        String title;
        String thumbnail_url;
        String youtube_url;
    }

    // 게시물 상세조회 API
    @Getter
    @Builder
    public static class detailResultDTO {
        Long postId;
        String youtubeTitle;
        String memberNickname;
        boolean isRecommend;
        int recommendCount;
        String img_url;
        String youtube_url;
        boolean isMine;
    }

    // 게시물 전체조회 API
    @Getter
    @Builder
    public static class listResultDTO {
        Long postId;
        String youtubeTitle;
        String memberNickname;
        String level;
        boolean isRecommend;
        int recommendCount;
        String thumbnailUrl;
    }

    // 인기 게시물 조회 API
    @Getter
    @Builder
    public static class popularResultDTO {
        Long postId;
        String thumbnailUrl;
    }
}
