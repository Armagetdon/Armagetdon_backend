package com.armagetdon.server.controller;

import com.armagetdon.server.apiPayload.ApiResponse;
import com.armagetdon.server.apiPayload.code.status.ErrorStatus;
import com.armagetdon.server.apiPayload.code.status.SuccessStatus;
import com.armagetdon.server.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
@Tag(name="Image", description = "이미지 업로드 및 삭제 기능 API")
public class ImageController {
    private final ImageService imageService;

    @Operation(summary = "이미지 업로드 기능 API", description = "게시글 생성 - 파일 선택 시 실행하는 API")
    @PostMapping(value="",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> uploadImage(@RequestPart(value = "file") MultipartFile multipartFile){
        try {
            return ApiResponse.onSuccess(imageService.uploadImage(multipartFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "이미지 삭제 기능 API", description = "파일 선택 후 뒤로가기 버튼 터치 시 실행하는 API")
    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteImage(@PathVariable("id") Long image_id) {
        try {
            imageService.deleteImage(image_id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ApiResponse.onSuccess("Image deleted successfully");
    }
}
