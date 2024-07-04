package com.armagetdon.server.controller;


import com.armagetdon.server.apiPayload.ApiResponse;
import com.armagetdon.server.converter.PostConverter;
import com.armagetdon.server.domain.Post;
import com.armagetdon.server.dto.PagingResponse;
import com.armagetdon.server.dto.PostRequestDTO;
import com.armagetdon.server.dto.PostResponseDTO;
import com.armagetdon.server.service.PostCommandService;
import com.armagetdon.server.service.PostQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostCommandService postCommandService;
    private final PostQueryService postQueryService;

    // 게시글 조회 API
    @PostMapping
    public ApiResponse<PostResponseDTO.createResultDTO> create(
            @RequestHeader("memberId") Long memberId,
            @RequestBody @Valid PostRequestDTO.createPostDTO request) {
        Post post = postCommandService.createPost(memberId, request);
        return ApiResponse.onSuccess(PostConverter.toCreatePostDTO(post));
    }

    // 게시글 삭제 API
    @DeleteMapping("/{id}")
    public ApiResponse<?> deletePost(@PathVariable(name="id") Long post_id){
        postCommandService.deletePost(post_id);
        return null;

    }

    // 게시글 상세조회 API
    @GetMapping("/{postId}")
    public ApiResponse<PostResponseDTO.detailResultDTO> getDetail(
            @RequestHeader("memberId") Long memberId,
            @PathVariable Long postId) {
        return ApiResponse.onSuccess(postQueryService.getDetail(memberId, postId));
    }

    // 전체 게시글 조회 API
    @GetMapping
    public ApiResponse<PagingResponse<PostResponseDTO.listResultDTO>> getList(
            @RequestHeader("memberId") Long memberId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return ApiResponse.onSuccess(postQueryService.getList(memberId, page, size));
    }

    // 내 게시글 조회 API
    @GetMapping("/mine")
    public ApiResponse<PagingResponse<PostResponseDTO.listResultDTO>> getMineList(
            @RequestHeader("memberId") Long memberId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return ApiResponse.onSuccess(postQueryService.getMineList(memberId, page, size));
    }

    // 인기 게시글 조회 API
    @GetMapping("/popular")
    public ApiResponse<List<PostResponseDTO.popularResultDTO>> getPopularList() {
        return ApiResponse.onSuccess(postQueryService.getPopularList());
    }
}
