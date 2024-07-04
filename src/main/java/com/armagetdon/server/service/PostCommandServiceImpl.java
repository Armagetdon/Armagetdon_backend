package com.armagetdon.server.service;


import com.armagetdon.server.apiPayload.code.status.ErrorStatus;
import com.armagetdon.server.apiPayload.exception.GeneralException;
import com.armagetdon.server.apiPayload.exception.handler.PostHandler;
import com.armagetdon.server.converter.PostConverter;
import com.armagetdon.server.domain.Member;
import com.armagetdon.server.domain.Post;
import com.armagetdon.server.domain.PostImage;
import com.armagetdon.server.dto.PostRequestDTO;
import com.armagetdon.server.dto.response.YoutubeDetail;
import com.armagetdon.server.repository.MemberRepository;
import com.armagetdon.server.repository.PostImageRepository;
import com.armagetdon.server.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostCommandServiceImpl implements PostCommandService {

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;
    private final MemberRepository memberRepository;
    private final YoutubeService youtubeService;

    @Override
    @Transactional
    public Post createPost(PostRequestDTO.createPostDTO request) throws GeneralSecurityException, IOException {
        PostImage postImage = postImageRepository.findById(request.getPost_image_id())
                .orElseThrow(() -> new GeneralException(ErrorStatus._BAD_REQUEST));
        Member member = memberRepository.findById(request.getMember_id())
                .orElseThrow(() -> new GeneralException(ErrorStatus._BAD_REQUEST));
        YoutubeDetail youtubeDetail = youtubeService.getYoutubeDetails(request.getYoutube_url());

        Post newPost = Post.builder()
                .post_image_id(postImage)
                .youtube_url(request.getYoutube_url())
                .thumbnail_url(youtubeDetail.getThumbnailUrl())
                .member(member)
                .title(youtubeDetail.getTitle())
                .build();

        // reward 지급
        member.addReward(10);
        // 고도 상승
        member.addAltitude(1000);

        return postRepository.save(newPost);
    }

    @Override
    @Transactional
    public void deletePost(Long post_id) {
        Post deletedPost = postRepository.findById(post_id).orElseThrow(() -> new PostHandler(ErrorStatus.POST_NOT_FOUND));
        postRepository.delete(deletedPost);
    }

}