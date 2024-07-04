package com.armagetdon.server.dto;

import com.armagetdon.server.domain.Member;
import com.armagetdon.server.domain.enums.Level;
import lombok.*;
import java.util.List;
import java.util.stream.IntStream;

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
    @AllArgsConstructor
    @Getter
    public static class MemberRankDto{
        private int rank;
        private String nickname;
        private int altitude;
        private String level;

        public static MemberRankDto from(int rank, Member member){
            String level = Level.getLevelByAltitude(member.getAltitude()).getName();

            return MemberRankDto.builder()
                    .rank(rank)
                    .nickname(member.getNickname())
                    .altitude(member.getAltitude())
                    .level(level)
                    .build();
        }
    }


    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class RankDto{
        private List<MemberRankDto> memberRankList;
        private MemberRankDto memberRank;

        public static RankDto from(List<Member> memberRankList, Member member, int rank){
            List<MemberRankDto> memberRankDtoList = IntStream.range(0, memberRankList.size())
                    .mapToObj(i -> MemberRankDto.from(i + 1, memberRankList.get(i)))
                    .toList();

            MemberRankDto memberRankDto = MemberRankDto.from(rank, member);

            return RankDto.builder()
                    .memberRankList(memberRankDtoList)
                    .memberRank(memberRankDto)
                    .build();
        }
    }
}
