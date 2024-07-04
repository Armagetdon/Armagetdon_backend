package com.armagetdon.server.controller;


import com.armagetdon.server.apiPayload.ApiResponse;
import com.armagetdon.server.converter.PostConverter;
import com.armagetdon.server.domain.Post;
import com.armagetdon.server.dto.PostRequestDTO;
import com.armagetdon.server.dto.PostResponseDTO;
import com.armagetdon.server.service.PostCommandService;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostCommandService postCommandService;

    // 게시글 조회 API
    @PostMapping("/")
    public ApiResponse<PostResponseDTO.createResultDTO> create(@RequestBody @Valid PostRequestDTO.createPostDTO request){
        Post post = postCommandService.createPost(request);
        return ApiResponse.onSuccess(PostConverter.toCreatePostDTO(post));
    }
}
