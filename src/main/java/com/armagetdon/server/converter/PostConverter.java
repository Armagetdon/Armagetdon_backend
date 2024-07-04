package com.armagetdon.server.converter;

import com.armagetdon.server.domain.Post;
import com.armagetdon.server.domain.PostImage;
import com.armagetdon.server.dto.PostRequestDTO;
import com.armagetdon.server.dto.PostResponseDTO;
import com.armagetdon.server.service.PostCommandServiceImpl;


public class PostConverter {

    private static PostCommandServiceImpl postCommandService;
    // 게시글 생성 API
    public static Post toPost(PostRequestDTO.createPostDTO request){
        PostImage image = postCommandService.giveImage(request.getPost_image_id());
        return Post.builder()
                .post_image_id(image)
                .youtube_url(request.getYoutube_url())
                .build();
    }

    public static PostResponseDTO.createResultDTO toCreatePostDTO(Post post){
        return PostResponseDTO.createResultDTO.builder()
                .createdAt(post.getCreatedAt())
                .memberId(post.getPost_id())
                .build();
    }
}
