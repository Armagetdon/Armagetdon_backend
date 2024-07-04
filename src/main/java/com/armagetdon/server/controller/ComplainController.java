package com.armagetdon.server.controller;

import com.armagetdon.server.apiPayload.ApiResponse;
import com.armagetdon.server.converter.ComplainConverter;
import com.armagetdon.server.domain.Complain;
import com.armagetdon.server.dto.ComplainRequestDTO;
import com.armagetdon.server.dto.ComplainResponseDTO;
import com.armagetdon.server.service.ComplainCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class ComplainController {

    private final ComplainCommandService complainCommandService;

    @PostMapping("/complain")
    public ApiResponse<ComplainResponseDTO.complainResultDTO> complain(@RequestBody @Valid ComplainRequestDTO.complainPostDTO request) {
        Complain complain = complainCommandService.askComplain(request);
        return ApiResponse.onSuccess(ComplainConverter.toComplainResultDTO(complain));
    }
}
