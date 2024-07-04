package com.armagetdon.server.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class RecommendRequestDTO {

    @Getter
    public static class recommendDTO{
        @Min(1)
        @NotNull
        Long post_id;

        @NotNull
        @Min(1)
        Long member_id;
    }
}
