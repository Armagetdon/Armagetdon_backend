package com.armagetdon.server.service;

import com.armagetdon.server.domain.Recommend;
import com.armagetdon.server.dto.RecommendRequestDTO;

public interface RecommendCommandService {

    public Recommend addRecommend(RecommendRequestDTO.recommendDTO request);
}
