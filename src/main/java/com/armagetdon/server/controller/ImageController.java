package com.armagetdon.server.controller;

import com.armagetdon.server.apiPayload.ApiResponse;
import com.armagetdon.server.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("")
    public ApiResponse<String> uploadImage(@RequestPart(value = "file", required = false) MultipartFile multipartFile){
        try {
            return ApiResponse.onSuccess(imageService.uploadImage(multipartFile));
        }catch (Exception e) {
            return ApiResponse.onFailure("IMAGE_UPLOAD_ERROR", "Failed to upload image", null);
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteImage(@PathVariable("id") Long image_id) {
        try {
            imageService.deleteImage(image_id);
            return ApiResponse.onSuccess("Image deleted successfully");
        } catch (IOException e) {
            return ApiResponse.onFailure("IMAGE_DELETE_ERROR", "Failed to delete image", null);
        }
    }
}
