package org.servlet2spring.todo.util;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

@Log4j2
@Component
@RequiredArgsConstructor
public class S3Uploader {

  private final S3Client s3Client;
  private final S3Presigner s3Presigner; // presigned URL 생성을 위해 필요

  @Value("${spring.cloud.aws.s3.bucket}")
  private String bucket;

  // S3로 파일 업로드
  public String upload(String filePath) {
    File targetFile = new File(filePath);
    String fileName = targetFile.getName();

    putS3(targetFile, fileName);    // S3에 업로드
    removeOriginalFile(targetFile); // 로컬 파일 삭제

    return getFileUrl(fileName); // presigned URL 반환
  }

  // S3에 업로드
  private void putS3(File targetFile, String fileName) {
    PutObjectRequest putObjectRequest = PutObjectRequest.builder()
            .bucket(bucket)
            .key(fileName)
            .acl("public-read") // 공개 URL로 접근 가능하게 설정
            .build();

    s3Client.putObject(putObjectRequest, RequestBody.fromFile(targetFile));
    log.info("Uploaded {} to S3 bucket {}", fileName, bucket);
  }

  // presigned URL 생성 (다운로드 가능한 링크)
  private String getFileUrl(String fileName) {
    GetObjectRequest getObjectRequest = GetObjectRequest.builder()
            .bucket(bucket)
            .key(fileName)
            .build();

    GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
            .signatureDuration(Duration.ofMinutes(60)) // 유효 시간
            .getObjectRequest(getObjectRequest)
            .build();

    URL url = s3Presigner.presignGetObject(presignRequest).url();
    return url.toString();
  }

  // 로컬 원본 파일 삭제
  private void removeOriginalFile(File targetFile) {
    if (targetFile.exists() && targetFile.delete()) {
      log.info("Deleted local file: {}", targetFile);
    } else {
      log.warn("Failed to delete local file: {}", targetFile);
    }
  }

  // S3에 업로드된 파일 삭제
  public void removeS3File(String fileName) {
    DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
            .bucket(bucket)
            .key(fileName)
            .build();

    s3Client.deleteObject(deleteObjectRequest);
    log.info("Deleted {} from S3 bucket {}", fileName, bucket);
  }
}
