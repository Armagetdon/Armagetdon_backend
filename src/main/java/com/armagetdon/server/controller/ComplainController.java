package com.armagetdon.server.controller;

import com.armagetdon.server.apiPayload.ApiResponse;
import com.armagetdon.server.converter.ComplainConverter;
import com.armagetdon.server.domain.Complain;
import com.armagetdon.server.dto.ComplainRequestDTO;
import com.armagetdon.server.dto.ComplainResponseDTO;
import com.armagetdon.server.service.ComplainCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Tag(name="Complain", description = "게시물 신고 기능 API")
public class ComplainController {

    private final ComplainCommandService complainCommandService;

    @Operation(summary = "게시물 신고하기 API", description = "선플이 아닐 경우 신고하는 API")
    @PostMapping("/complain")
    public ApiResponse<ComplainResponseDTO.complainResultDTO> complain(@RequestBody @Valid ComplainRequestDTO.complainPostDTO request) {
        Complain complain = complainCommandService.askComplain(request);
        return ApiResponse.onSuccess(ComplainConverter.toComplainResultDTO(complain));
    }
}
