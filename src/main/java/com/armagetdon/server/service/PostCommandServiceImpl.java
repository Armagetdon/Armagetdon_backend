package com.armagetdon.server.service;


import com.armagetdon.server.apiPayload.code.status.ErrorStatus;
import com.armagetdon.server.apiPayload.exception.handler.PostHandler;
import com.armagetdon.server.converter.PostConverter;
import com.armagetdon.server.domain.Post;
import com.armagetdon.server.dto.PostRequestDTO;
import com.armagetdon.server.repository.PostImageRepository;
import com.armagetdon.server.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostCommandServiceImpl implements PostCommandService {

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;

    @Override
    @Transactional
    public Post createPost(Long memberId, PostRequestDTO.createPostDTO request) {
        Post newPost = PostConverter.toPost(request);
        return postRepository.save(newPost);
    }

    @Override
    @Transactional
    public void deletePost(Long post_id) {
        Post deletedPost = postRepository.findById(post_id).orElseThrow(() -> new PostHandler(ErrorStatus.POST_NOT_FOUND));
        postRepository.delete(deletedPost);
    }

}