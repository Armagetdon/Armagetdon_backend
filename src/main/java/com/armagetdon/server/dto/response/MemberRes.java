package com.armagetdon.server.dto.response;

import com.armagetdon.server.domain.Complain;
import com.armagetdon.server.domain.Member;
import com.armagetdon.server.domain.Post;
import com.armagetdon.server.domain.Recommend;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
public record MemberRes (
        @Schema(
                description = "member가 생성된 순서 관리하는 PK"
        )
        Long member_id,

        @Schema(
                description = "랜덤 생성된 닉네임 관리"
        )
        String nickname,

        @Schema(
                description = "고도 관리"
        )
        int altitude,
        @Schema(
                description = "포인트(리워드) 관리 "
        )
        Long reward,
        @Schema(
                description = "내가 올린 게시글 관리"
        )

        List<Post> post,
        @Schema(
                description = "추천 기능 관리를 위한 테이블"
        )
        List<Recommend> recommend,
        @Schema(
                description = "삭제 기능 관리를 위한 테이블"
        )
        List<Complain> complain
){
    public static MemberRes of(Member member){
        return MemberRes.builder()
                .member_id(member.getMember_id())
                .nickname(member.getNickname())
                .altitude(member.getAltitude())
                .reward(member.getReward())
                .post(member.getPost())
                .recommend(member.getRecommend())
                .complain(member.getComplain())
                .build();
    }
}
