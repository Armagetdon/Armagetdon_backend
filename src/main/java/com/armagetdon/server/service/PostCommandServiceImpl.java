package com.armagetdon.server.service;


import com.armagetdon.server.apiPayload.code.status.ErrorStatus;
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
        Optional<PostImage> OptionalImage = postImageRepository.findById(request.getPost_image_id());
        Optional<Member> OptionalMember = memberRepository.findById(request.getMember_id());
        YoutubeDetail youtubeDetail = youtubeService.getYoutubeDetails(request.getYoutube_url());

        Post newPost = Post.builder()
                .post_image_id(OptionalImage.get())
                .youtube_url(request.getYoutube_url())
                .thumbnail_url(youtubeDetail.getThumbnailUrl())
                .member(OptionalMember.get())
                .title(youtubeDetail.getTitle())
                .build();
        return postRepository.save(newPost);
    }

    @Override
    @Transactional
    public void deletePost(Long post_id) {
        Post deletedPost = postRepository.findById(post_id).orElseThrow(() -> new PostHandler(ErrorStatus.POST_NOT_FOUND));
        postRepository.delete(deletedPost);
    }

}