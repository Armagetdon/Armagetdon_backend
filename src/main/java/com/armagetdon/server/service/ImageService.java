package com.armagetdon.server.service;

import com.armagetdon.server.apiPayload.code.status.ErrorStatus;
import com.armagetdon.server.apiPayload.exception.GeneralException;
import com.armagetdon.server.domain.PostImage;
import com.armagetdon.server.repository.ImageRepository;
import com.armagetdon.server.util.S3Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final S3Client s3Client;

    @Transactional
    public String uploadImage(MultipartFile file) throws IOException {
        if(file==null){
            throw new GeneralException(ErrorStatus._BAD_REQUEST);
        }
        String imageUrl = s3Client.upload(file);

        PostImage postImage = PostImage.builder()
                .s3url(imageUrl)
                .build();
        imageRepository.save(postImage);

        return imageUrl;
    }

    @Transactional
    public void deleteImage(Long image_id) throws IOException {
        Optional<PostImage> optionalImage = Optional.ofNullable(imageRepository.findById(image_id)
                .orElseThrow(() -> new GeneralException(ErrorStatus.IMAGE_NOT_FOUND)));
        PostImage image = optionalImage.get();
        s3Client.delete(image.getS3url());
        imageRepository.delete(image);
    }
}
