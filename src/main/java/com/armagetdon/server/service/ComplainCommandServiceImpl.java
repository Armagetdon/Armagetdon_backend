package com.armagetdon.server.service;

import com.armagetdon.server.apiPayload.code.status.ErrorStatus;
import com.armagetdon.server.apiPayload.exception.handler.MemberHandler;
import com.armagetdon.server.apiPayload.exception.handler.PostHandler;
import com.armagetdon.server.converter.ComplainConverter;
import com.armagetdon.server.domain.Complain;
import com.armagetdon.server.domain.Member;
import com.armagetdon.server.domain.Post;
import com.armagetdon.server.domain.Recommend;
import com.armagetdon.server.dto.ComplainRequestDTO;
import com.armagetdon.server.repository.ComplainRepository;
import com.armagetdon.server.repository.MemberRepository;
import com.armagetdon.server.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;



@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ComplainCommandServiceImpl implements ComplainCommandService{

    private final ComplainRepository complainRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Complain askComplain(ComplainRequestDTO.complainPostDTO request){
        Optional<Post> newPost = postRepository.findById(request.getPost_id());
        Post post = newPost.orElseThrow(() -> new PostHandler(ErrorStatus.POST_NOT_FOUND));
        Optional<Member> newMember = memberRepository.findById(request.getMember_id());
        Member member = newMember.orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Complain newComplain = Complain.builder()
                .post(post)
                .member(member)
                .build();

        return complainRepository.save(newComplain);
    }
}
