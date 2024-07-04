package com.armagetdon.server.controller;

import com.armagetdon.server.apiPayload.ApiResponse;
import com.armagetdon.server.converter.RecommendConverter;
import com.armagetdon.server.domain.Recommend;
import com.armagetdon.server.dto.RecommendRequestDTO;
import com.armagetdon.server.dto.RecommendResponseDTO;
import com.armagetdon.server.service.RecommendCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/posts")
@Tag(name = "Recommend", description = "추천 기능 관련 API")
public class RecommendController {

    private final RecommendCommandService  recommendCommandService;

    @Autowired
    public RecommendController(RecommendCommandService recommendCommandService) {
        this.recommendCommandService = recommendCommandService;
    }

    @Operation(summary = "게시물 추천 기능 API", description = "선플일 경우 추천하는 API")
    @PostMapping("/recommend")
    public ApiResponse<RecommendResponseDTO.recommendJoinDTO> recommend(@RequestBody @Valid RecommendRequestDTO.recommendDTO request){
        Recommend recommend = recommendCommandService.addRecommend(request);
        return ApiResponse.onSuccess(RecommendConverter.toRecommendResultDTO(recommend));
    }

}
