package com.armagetdon.server.converter;


import com.armagetdon.server.apiPayload.code.status.ErrorStatus;
import com.armagetdon.server.apiPayload.exception.handler.MemberHandler;
import com.armagetdon.server.apiPayload.exception.handler.RecommendHandler;
import com.armagetdon.server.domain.Member;
import com.armagetdon.server.domain.Post;
import com.armagetdon.server.domain.Recommend;
import com.armagetdon.server.dto.RecommendRequestDTO;
import com.armagetdon.server.dto.RecommendResponseDTO;
import com.armagetdon.server.repository.MemberRepository;
import com.armagetdon.server.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class RecommendConverter {

    private static PostRepository postRepository;
    private static MemberRepository memberRepository;

    public static RecommendResponseDTO.recommendJoinDTO toRecommendResultDTO(Recommend recommend) {
        return RecommendResponseDTO.recommendJoinDTO.builder()
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Recommend toRecommend(RecommendRequestDTO.recommendDTO request) {
        Optional<Post> optionalPost = postRepository.findById(request.getPost_id());
        Post post = optionalPost.orElseThrow(()-> new RecommendHandler(ErrorStatus.POST_NOT_FOUND));
        Optional<Member> optionalMember = memberRepository.findById(request.getMember_id());
        Member member = optionalMember.orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return Recommend.builder()
                .post(post)
                .member(member)
                .build();
    }
}
