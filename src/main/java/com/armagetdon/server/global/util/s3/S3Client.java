package com.armagetdon.server.global.util.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3Client {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    /*
    MultipartFile을 입력으로 받아서 S3에 파일을 업로드하고 해당 파일의 URL을 반환
     */
    public String saveFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename(); //업로드된 파일은 원본 파일의 이름을 그대로 사용하여 저장

        //파일의 메타데이터를 생성하고 설정
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize()); //파일의 크기
        metadata.setContentType(multipartFile.getContentType()); //콘텐츠유형

        //AmazonS3 객체의 putObject 메서드를 사용하여 S3 버킷에 파일을 업로드
        amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata); //버킷 이름, 파일 이름, 파일의 입력 스트림, 메타데이터 전달
        return amazonS3.getUrl(bucket, originalFilename).toString(); //업로드된 파일의 URL을 생성하고 반환
    }
}
