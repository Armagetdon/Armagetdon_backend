package com.armagetdon.server.controller;

import com.armagetdon.server.apiPayload.ApiResponse;
import com.armagetdon.server.domain.Recommend;
import com.armagetdon.server.dto.RecommendRequestDTO;
import com.armagetdon.server.dto.RecommendResponseDTO;
import com.armagetdon.server.service.RecommendCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class RecommendController {

    private final RecommendCommandService  recommendCommandService;

    @PostMapping("/recommend")
    public ApiResponse<RecommendResponseDTO.recommendJoinDTO> recommend(RecommendRequestDTO.recommendDTO request){
        Recommend recommend = recommendCommandService.addRecommend(request);
        return null;
    }
}
