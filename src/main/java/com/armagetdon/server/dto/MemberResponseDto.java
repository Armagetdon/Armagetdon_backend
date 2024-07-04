package com.armagetdon.server.dto;

import lombok.*;

@Data
public class MemberResponseDto {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class MyPageDto{
        private String nickname;
        private long reward;
        private String level;
        private int leftAltitude;

        public static MyPageDto from(String nickname, long reward, String level, int leftAltitude){
            return MyPageDto.builder()
                    .nickname(nickname)
                    .reward(reward)
                    .level(level)
                    .leftAltitude(leftAltitude)
                    .build();
        }
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class RewardDto{
        private long reward;

        public static RewardDto from(long reward){
            return RewardDto.builder()
                    .reward(reward)
                    .build();
        }
    }

    @Builder
    @NoArgsConstructor
//    @AllArgsConstructor
    @Getter
    public static class RankDto{
        public static RankDto from(){
            return RankDto.builder().build();
        }
    }
}
