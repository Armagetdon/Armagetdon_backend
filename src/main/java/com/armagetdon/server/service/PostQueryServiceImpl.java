package com.armagetdon.server.service;

import com.armagetdon.server.dto.PagingResponse;
import com.armagetdon.server.dto.PostResponseDTO;
import com.armagetdon.server.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostQueryServiceImpl implements PostQueryService{

    private final PostRepository postRepository;

    @Override
    public PostResponseDTO.detailResultDTO getDetail(Long memberId, Long postId) {
        return null;
    }

    @Override
    public PagingResponse<PostResponseDTO.listResultDTO> getList(Long memberId, int page, int size) {
        return null;
    }

    @Override
    public PagingResponse<PostResponseDTO.listResultDTO> getMineList(Long memberId, int page, int size) {
        return null;
    }

    @Override
    public PostResponseDTO.popularResultDTO getPopularList() {
        return null;
    }
}
