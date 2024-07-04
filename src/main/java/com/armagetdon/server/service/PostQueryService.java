package com.armagetdon.server.service;

import com.armagetdon.server.domain.Post;
import com.armagetdon.server.dto.PagingResponse;
import com.armagetdon.server.dto.PostResponseDTO;

import java.util.List;

public interface PostQueryService {
    PostResponseDTO.detailResultDTO getDetail(Long memberId, Long postId);
    PagingResponse<PostResponseDTO.listResultDTO> getList(Long memberId, int page, int size);
    PagingResponse<PostResponseDTO.listResultDTO> getMineList(Long memberId, int page, int size);
    List<PostResponseDTO.popularResultDTO> getPopularList();
    Post getPost(Long postId);
}
