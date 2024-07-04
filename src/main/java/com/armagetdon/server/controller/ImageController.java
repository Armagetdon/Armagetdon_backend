package com.armagetdon.server.controller;


import com.armagetdon.server.DTO.ImageRes;
import com.armagetdon.server.apiPayload.ApiResponse;
import com.armagetdon.server.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("")
    public ApiResponse<ImageRes> uploadImage(@RequestPart(value = "file", required = false) MultipartFile multipartFile){
        try {
            return ApiResponse.onSuccess(imageService.uploadImage(multipartFile));
        }catch (Exception e) {
            return ApiResponse.onFailure("UPLOAD_ERROR", "Failed to upload image", null);
        }
    }
}
