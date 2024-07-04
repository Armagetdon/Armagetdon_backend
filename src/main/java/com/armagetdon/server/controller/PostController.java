package com.armagetdon.server.controller;


import com.armagetdon.server.apiPayload.ApiResponse;
import com.armagetdon.server.converter.PostConverter;
import com.armagetdon.server.domain.Post;
import com.armagetdon.server.dto.PostRequestDTO;
import com.armagetdon.server.dto.PostResponseDTO;
import com.armagetdon.server.service.PostCommandService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Tag(name="Post", description = "게시물 관련 기능 API")
public class PostController {

    private final PostCommandService postCommandService;

    @Operation(summary = "게시글 생성 API", description = "게시글을 생성하는 API")
    @PostMapping("/")
    public ApiResponse<PostResponseDTO.createResultDTO> create(@RequestBody @Valid PostRequestDTO.createPostDTO request){
        Post post = null;
        try {
            post = postCommandService.createPost(request);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ApiResponse.onSuccess(PostConverter.toCreatePostDTO(post));
    }

    @Operation(summary = "게시글 삭제 API", description = "게시글을 삭제하는 API")
    @DeleteMapping("/{id}")
    public ApiResponse<?> deletePost(@PathVariable(name="id") Long post_id){
        postCommandService.deletePost(post_id);
        return null;

    }
}
