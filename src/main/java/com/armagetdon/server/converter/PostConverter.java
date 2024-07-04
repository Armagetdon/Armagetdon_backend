package com.armagetdon.server.converter;

import com.armagetdon.server.apiPayload.code.status.ErrorStatus;
import com.armagetdon.server.apiPayload.exception.handler.PostHandler;
import com.armagetdon.server.domain.Post;
import com.armagetdon.server.domain.PostImage;
import com.armagetdon.server.dto.PostRequestDTO;
import com.armagetdon.server.dto.PostResponseDTO;
import com.armagetdon.server.repository.ImageRepository;
import com.armagetdon.server.service.PostCommandServiceImpl;

import java.util.Optional;


public class PostConverter {

    private static PostCommandServiceImpl postCommandService;
    private static ImageRepository ImageRepository;
    // 게시글 생성 API
    public static Post toPost(PostRequestDTO.createPostDTO request){
        //PostImage image = postCommandService.giveImage(request.getPost_image_id();

        Optional<PostImage> optionalImage = ImageRepository.findById(request.getPost_image_id());
        PostImage image = optionalImage.orElseThrow(()-> new PostHandler(ErrorStatus.IMAGE_NOT_FOUND));
        return Post.builder()
                .post_image_id(image)
                .youtube_url(request.getYoutube_url())
                .build();
    }

    public static PostResponseDTO.createResultDTO toCreatePostDTO(Post post){
        return PostResponseDTO.createResultDTO.builder()
                .createdAt(post.getCreatedAt())
                .thumbnail_url(post.getThumbnail_url())
                .postImageUrl(post.getPost_image_id().getS3url())
                .title(post.getTitle())
                .postId(post.getPost_id())
                .memberId(post.getPost_id())
                .youtube_url(post.getYoutube_url())
                .build();
    }
}
