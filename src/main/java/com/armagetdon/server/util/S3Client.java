package com.armagetdon.server.util;

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

    @Value("${cloud.aws.cloudfront}")
    private String baseUrl;

    /*
    MultipartFile을 입력으로 받아서 S3에 파일을 업로드하고 해당 파일의 URL을 반환
     */
    public String upload(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename(); //업로드된 파일은 원본 파일의 이름을 그대로 사용하여 저장

        //파일의 메타데이터를 생성하고 설정
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize()); //파일의 크기
        metadata.setContentType(multipartFile.getContentType()); //콘텐츠유형

        //AmazonS3 객체의 putObject 메서드를 사용하여 S3 버킷에 파일을 업로드
        amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata); //버킷 이름, 파일 이름, 파일의 입력 스트림, 메타데이터 전달
        return baseUrl+originalFilename; //업로드된 파일의 URL을 생성하고 반환
    }

    public void delete(String imageUrl) {
        //파일 이름 추출하여 bucket에 존재하는 지 확인
        String fileName = imageUrl.substring(baseUrl.length());
        if(!amazonS3.doesObjectExist(bucket, fileName)) {
            //throw new ;
        }

        //해당 파일 이름 객체를 bucket에서 삭제
        try {
            amazonS3.deleteObject(bucket, fileName);
        } catch (Exception e) {
            //throw new ;
        }
    }
}
