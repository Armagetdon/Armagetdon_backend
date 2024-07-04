package com.armagetdon.server.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
public class MemberRequestDto {

    @Getter
    public static class PatchRewardDto{
        @NotBlank(message = "memberId를 입력해주세요")
        private Long memberId;
        @NotBlank(message = "reward를 입력해주세요")
        private Long reward;
    }
}
