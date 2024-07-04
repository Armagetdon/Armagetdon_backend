package com.armagetdon.server.converter;

import com.armagetdon.server.apiPayload.code.status.ErrorStatus;
import com.armagetdon.server.apiPayload.exception.handler.PostHandler;
import com.armagetdon.server.domain.Complain;
import com.armagetdon.server.domain.Post;
import com.armagetdon.server.dto.ComplainRequestDTO;
import com.armagetdon.server.dto.ComplainResponseDTO;
import com.armagetdon.server.repository.PostRepository;

import java.util.Optional;

public class ComplainConverter {

    private static PostRepository postRepository;
     // 게시글 신고 API
    public static ComplainResponseDTO.complainResultDTO toComplainResultDTO(Complain complain){
        return ComplainResponseDTO.complainResultDTO.builder()
                .member_id(complain.getComplain_id())
                .createdAt(complain.getCreatedAt())
                .build();
    }

    public static Complain toComplain(ComplainRequestDTO.complainPostDTO request){
        Optional<Post> optionalPost = postRepository.findById(request.getPost_id());
        Post c_post = optionalPost.orElseThrow(() -> new PostHandler(ErrorStatus.POST_NOT_FOUND));
        return Complain.builder()
                .post(c_post)
                .build();

    }
}
