package com.armagetdon.server.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;

public class RecommendRequestDTO {

    @Getter
    public static class recommendDTO{
        @Min(1)
        Long post_id;
        Long member_id;
    }
}
