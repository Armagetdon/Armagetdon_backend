package com.armagetdon.server.service;

import com.armagetdon.server.domain.Post;
import com.armagetdon.server.dto.PostRequestDTO;


public interface PostCommandService {

    Post createPost(Long memberId, PostRequestDTO.createPostDTO request);

    public void deletePost(Long id);

}
