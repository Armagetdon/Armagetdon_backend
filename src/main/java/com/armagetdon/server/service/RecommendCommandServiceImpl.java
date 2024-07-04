package com.armagetdon.server.service;

import com.armagetdon.server.apiPayload.code.status.ErrorStatus;
import com.armagetdon.server.apiPayload.exception.handler.MemberHandler;
import com.armagetdon.server.apiPayload.exception.handler.PostHandler;
import com.armagetdon.server.converter.RecommendConverter;
import com.armagetdon.server.domain.Member;
import com.armagetdon.server.domain.Post;
import com.armagetdon.server.domain.Recommend;
import com.armagetdon.server.dto.RecommendRequestDTO;
import com.armagetdon.server.repository.MemberRepository;
import com.armagetdon.server.repository.PostRepository;
import com.armagetdon.server.repository.RecommendRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecommendCommandServiceImpl implements RecommendCommandService {

    private final RecommendRepository recommendRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Recommend addRecommend(RecommendRequestDTO.recommendDTO request) {
        Optional<Post> newPost = postRepository.findById(request.getPost_id());
        Post post = newPost.orElseThrow(() -> new PostHandler(ErrorStatus.POST_NOT_FOUND));

        Optional<Member> newMember = memberRepository.findById(request.getMember_id());
        Member member = newMember.orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Optional<Recommend> recommend = recommendRepository.findByPostAndMember(post, member);
        if (recommend.isEmpty()) {
            Recommend newRecommend = Recommend.builder()
                    .post(post)
                    .member(member)
                    .isActive(true)
                    .build();
            return recommendRepository.save(newRecommend);
        }
        recommend.get().changeState();

        return recommend.get();

    }
}

