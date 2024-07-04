package com.armagetdon.server.service;

import com.armagetdon.server.domain.Post;
import com.armagetdon.server.domain.PostImage;
import com.armagetdon.server.dto.PostRequestDTO;

import java.io.IOException;


public interface PostCommandService {

    public Post createPost(PostRequestDTO.createPostDTO request);


    public void deletePost(Long id);

}
