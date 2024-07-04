package com.armagetdon.server.service;

import com.armagetdon.server.domain.Post;
import com.armagetdon.server.domain.PostImage;
import com.armagetdon.server.dto.PostRequestDTO;


public interface PostCommandService {

    public Post createPost(PostRequestDTO.createPostDTO request);
    public PostImage giveImage(Long post_image_id);
}
