package com.armagetdon.server.service;

import com.armagetdon.server.dto.PagingResponse;
import com.armagetdon.server.dto.PostResponseDTO;

public interface PostQueryService {
    PostResponseDTO.detailResultDTO getDetail(Long memberId, Long postId);
    PagingResponse<PostResponseDTO.listResultDTO> getList(Long memberId, int page, int size);
    PagingResponse<PostResponseDTO.listResultDTO> getMineList(Long memberId, int page, int size);
    PostResponseDTO.popularResultDTO getPopularList();
}
