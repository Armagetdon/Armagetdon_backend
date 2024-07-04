package com.armagetdon.server.service;

import com.armagetdon.server.apiPayload.code.status.ErrorStatus;
import com.armagetdon.server.apiPayload.exception.handler.PostImageHandler;
import com.armagetdon.server.converter.PostConverter;
import com.armagetdon.server.domain.Post;
import com.armagetdon.server.domain.PostImage;
import com.armagetdon.server.dto.PostRequestDTO;
import com.armagetdon.server.repository.PostImageRepository;
import com.armagetdon.server.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostCommandServiceImpl implements PostCommandService {

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;

    @Override
    @Transactional
    public Post createPost(PostRequestDTO.createPostDTO request){
        Post newPost = PostConverter.toPost(request);
        return postRepository.save(newPost);
    }

    public PostImage giveImage(Long post_image_id){
        PostImage image = postImageRepository.findById(post_image_id).orElseThrow(() -> new PostImageHandler(ErrorStatus.IMAGE_NOT_FOUND));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+image);
        return image;
    }
}
