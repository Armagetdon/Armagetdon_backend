package com.armagetdon.server.dto;

import lombok.Getter;

public class ComplainRequestDTO {

    // 게시글 신고 API
    @Getter
    public static class complainPostDTO{
        Long post_id;
    }
}
