package com.armagetdon.server.dto.response;

import com.armagetdon.server.domain.Complain;
import com.armagetdon.server.domain.Member;
import com.armagetdon.server.domain.Post;
import com.armagetdon.server.domain.Recommend;
import lombok.Builder;

import java.util.List;

@Builder
public record MemberRes (
        Long member_id,
        String nickname,
        int altitude,
        Long reward,

        List<Post> post,
        List<Recommend> recommend,
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
