package com.armagetdon.server.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberResponseDto {

    @Builder
    public static class MyPageDto{
        private String nickname;
        private long reward;
        private int altitude;

        public static MyPageDto from(String nickname, long reward, int altitude){
            return MyPageDto.builder()
                    .nickname(nickname)
                    .reward(reward)
                    .altitude(altitude)
                    .build();
        }
    }

    @Builder
    public static class RewardDto{
        private long reward;

        public static RewardDto from(long reward){
            return RewardDto.builder()
                    .reward(reward)
                    .build();
        }
    }

    @Builder
    public static class RankDto{
        public static RankDto from(){
            return RankDto.builder().build();
        }
    }
}
