package com.armagetdon.server.service;

import com.armagetdon.server.converter.RecommendConverter;
import com.armagetdon.server.domain.Recommend;
import com.armagetdon.server.dto.RecommendRequestDTO;
import com.armagetdon.server.repository.RecommendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecommendCommandServiceImpl implements RecommendCommandService {

    private final RecommendRepository recommendRepository;

    @Override
    @Transactional
    public Recommend addRecommend(RecommendRequestDTO.recommendDTO request){
        Recommend newRecommend = RecommendConverter.toRecommend(request);
        return recommendRepository.save(newRecommend);
    }
}
