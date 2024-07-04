package com.armagetdon.server.converter;

import com.armagetdon.server.apiPayload.code.status.ErrorStatus;
import com.armagetdon.server.apiPayload.exception.handler.PostHandler;
import com.armagetdon.server.domain.Post;
import com.armagetdon.server.domain.PostImage;
import com.armagetdon.server.dto.PagingResponse;
import com.armagetdon.server.dto.PostRequestDTO;
import com.armagetdon.server.dto.PostResponseDTO;
import com.armagetdon.server.repository.ImageRepository;
import com.armagetdon.server.service.PostCommandServiceImpl;
import org.springframework.data.domain.Page;

import java.util.List;
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
                .postImage(image)
                .youtubeUrl(request.getYoutube_url())
                .build();
    }

    public static PostResponseDTO.createResultDTO toCreatePostDTO(Post post){
        return PostResponseDTO.createResultDTO.builder()
                .createdAt(post.getCreatedAt())
                .memberId(post.getPost_id())
                .build();
    }

    public static PostResponseDTO.detailResultDTO toDetailPostDTO(Post post, boolean isRecommend, boolean isMine){
        return PostResponseDTO.detailResultDTO.builder()
                .postId(post.getPost_id())
                .youtubeTitle(post.getYoutubeUrl())
                .youtubeUrl(post.getYoutubeUrl())
                .isRecommend(isRecommend)
                .recommendCount(post.getPostRecommendCount())
                .img_url(post.getPostImage().getS3url())
                .youtubeUrl(post.getYoutubeUrl())
                .isMine(isMine)
                .build();
    }

    public static PostResponseDTO.listResultDTO toListPostDTO(Post post, boolean isRecommend, String level){
        return PostResponseDTO.listResultDTO.builder()
                .postId(post.getPost_id())
                .youtubeTitle(post.getTitle())
                .memberNickname(post.getMember().getNickname())
                .level(level)
                .isRecommend(isRecommend)
                .recommendCount(post.getPostRecommendCount())
                .thumbnailUrl(post.getThumbnailUrl())
                .build();
    }

    public static PagingResponse<PostResponseDTO.listResultDTO> toPagingResponse(
            Page<Post> pagePost, List<PostResponseDTO.listResultDTO> posts){
        return PagingResponse.<PostResponseDTO.listResultDTO>builder()
                .data(posts)
                .page(pagePost.getNumber())
                .totalPages(pagePost.getTotalPages())
                .totalElements((int) pagePost.getTotalElements())
                .isFirst(pagePost.isFirst())
                .isLast(pagePost.isLast())
                .build();
    }

    public static PostResponseDTO.popularResultDTO toPopularResultDTO(Post post) {
        return PostResponseDTO.popularResultDTO.builder()
                .postId(post.getPost_id())
                .thumbnailUrl(post.getThumbnailUrl())
                .build();
    }
}
