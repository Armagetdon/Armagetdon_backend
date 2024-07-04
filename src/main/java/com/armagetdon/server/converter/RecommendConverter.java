package com.armagetdon.server.converter;


import com.armagetdon.server.apiPayload.code.status.ErrorStatus;
import com.armagetdon.server.apiPayload.exception.handler.RecommendHandler;
import com.armagetdon.server.domain.Post;
import com.armagetdon.server.domain.Recommend;
import com.armagetdon.server.dto.RecommendRequestDTO;
import com.armagetdon.server.dto.RecommendResponseDTO;
import com.armagetdon.server.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class RecommendConverter {

    private static PostRepository postRepository;

    public static RecommendResponseDTO.recommendJoinDTO toRecommendResultDTO(Recommend recommend) {
        return RecommendResponseDTO.recommendJoinDTO.builder()
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Recommend toRecommend(RecommendRequestDTO.recommendDTO request) {
        Optional<Post> optionalPost = postRepository.findById(request.getPost_id());
        Post post = optionalPost.orElseThrow(()-> new RecommendHandler(ErrorStatus.POST_NOT_FOUND));
        return Recommend.builder()
                .post(post)
                .build();

    }
}
