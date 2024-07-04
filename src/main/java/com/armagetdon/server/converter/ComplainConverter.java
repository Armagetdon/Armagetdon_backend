package com.armagetdon.server.converter;

import com.armagetdon.server.apiPayload.code.status.ErrorStatus;
import com.armagetdon.server.apiPayload.exception.handler.MemberHandler;
import com.armagetdon.server.apiPayload.exception.handler.PostHandler;
import com.armagetdon.server.apiPayload.exception.handler.RecommendHandler;
import com.armagetdon.server.domain.Complain;
import com.armagetdon.server.domain.Member;
import com.armagetdon.server.domain.Post;
import com.armagetdon.server.dto.ComplainRequestDTO;
import com.armagetdon.server.dto.ComplainResponseDTO;
import com.armagetdon.server.dto.PostRequestDTO;
import com.armagetdon.server.repository.MemberRepository;
import com.armagetdon.server.repository.PostRepository;

import java.util.Optional;

public class ComplainConverter {

    private static PostRepository postRepository;
    private static MemberRepository memberRepository;

     // 게시글 신고 API
    public static ComplainResponseDTO.complainResultDTO toComplainResultDTO(Complain complain){
        return ComplainResponseDTO.complainResultDTO.builder()
                .member_id(complain.getComplain_id())
                .createdAt(complain.getCreatedAt())
                .build();
    }

    public static Complain toComplain(ComplainRequestDTO.complainPostDTO request){
        Optional<Post> optionalPost = postRepository.findById(request.getPost_id());
        Post post = optionalPost.orElseThrow(()-> new RecommendHandler(ErrorStatus.POST_NOT_FOUND));
        Optional<Member> optionalMember = memberRepository.findById(request.getMember_id());
        Member member = optionalMember.orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return Complain.builder()
                .post(post)
                .member(member)
                .build();

    }
}
