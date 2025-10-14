package org.servlet2spring.todo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class AwsS3Config {

//  private final Environment env;
  private final Region awsRegion;

  // 설정 파일에서 직접 자격 증명을 읽어오기
  @Value("${aws.accessKeyId}")
  private String accessKey;

  @Value("${aws.secretAccessKey}")
  private String secretKey;

  public AwsS3Config(@Value("${spring.cloud.aws.region.static:ap-northeast-2}") String regionCode) {
    this.awsRegion = Region.of(regionCode);
  }

  // StaticCredentialsProvider 생성
  private StaticCredentialsProvider getCredentialsProvider() {
    return StaticCredentialsProvider.create(
            AwsBasicCredentials.create(accessKey, secretKey)
    );
  }

  @Bean
  public S3Client s3Client() {
    return S3Client.builder()
            .region(awsRegion)
            .credentialsProvider(getCredentialsProvider()) // 자격 증명 명시적 주입
            .build();
  }

  @Bean
  public S3Presigner s3Presigner() {
    return S3Presigner.builder()
            .region(awsRegion)
            .credentialsProvider(getCredentialsProvider()) // 자격 증명 명시적 주입
            .build();
  }
}