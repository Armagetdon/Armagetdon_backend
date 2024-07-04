package com.armagetdon.server.controller;

import com.armagetdon.server.apiPayload.ApiResponse;
import com.armagetdon.server.converter.ComplainConverter;
import com.armagetdon.server.domain.Complain;
import com.armagetdon.server.dto.ComplainRequestDTO;
import com.armagetdon.server.dto.ComplainResponseDTO;
import com.armagetdon.server.service.ComplainCommandService;
import com.armagetdon.server.service.RecommendCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/posts")
@Tag(name = "Complain", description = "신고 기능 관련 API")
public class ComplainController {

    private final ComplainCommandService complainCommandService;

    @Autowired
    public ComplainController(ComplainCommandService complainCommandService) {
        this.complainCommandService = complainCommandService;
    }

    @Operation(summary = "게시물 신고 기능 API", description = "악플일 경우 신고하는 API")
    @PostMapping("/complain")
    public ApiResponse<ComplainResponseDTO.complainResultDTO> complain(@RequestBody @Valid ComplainRequestDTO.complainPostDTO request) {
        Complain complain = complainCommandService.askComplain(request);
        return ApiResponse.onSuccess(ComplainConverter.toComplainResultDTO(complain));
    }
}
